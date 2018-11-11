package com.zhwl.home_server.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 七牛服务端使用
 */
@Component
public class QiNiuUtil {
    private static String QI_NIU_ACCESS_KEY;
    private static String QI_NIU_SECRET_KEY;
    private static String QI_NIU_BUCKET_NAME;
    private static String QI_NIU_DOWNLOAD_URL;

    @Value("${QI_NIU_ACCESS_KEY}")
    public void setQiNiuAccessKey(String qiNiuAccessKey) {
        QI_NIU_ACCESS_KEY = qiNiuAccessKey;
    }
    @Value("${QI_NIU_SECRET_KEY}")
    public void setQiNiuSecretKey(String qiNiuSecretKey) {
        QI_NIU_SECRET_KEY = qiNiuSecretKey;
    }
    @Value("${QI_NIU_BUCKET_NAME}")
    public void setQiNiuBucketName(String qiNiuBucketName) {
        QI_NIU_BUCKET_NAME = qiNiuBucketName;
    }
    @Value("${QI_NIU_DOWNLOAD_URL}")
    public void setQiNiuDownloadUrl(String qiNiuDownloadUrl) {
        QI_NIU_DOWNLOAD_URL = qiNiuDownloadUrl;
    }

    /**
     * 获得上传凭据
     * @return
     */
    public static String getUploadKey() {
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        return auth.uploadToken(QI_NIU_BUCKET_NAME);
    }

    @Deprecated
    private String getUploadKeyWithCallback(HttpServletRequest request, String callbackAction) {
        String path = request.getContextPath();
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        long expireSeconds = 3600;
        StringMap putPolicy = new StringMap();
        putPolicy.put("callbackUrl", "http://yhdpc.free.ngrok.cc/qiniuconfig/saveCallBackContent.do");
        putPolicy.put("callbackBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize),\"fName\":$(fName)}");
        putPolicy.put("callbackBodyType", "application/json");
        return auth.uploadToken(QI_NIU_BUCKET_NAME, null, expireSeconds, putPolicy);
    }

    @Deprecated
    private String getUploadKeyWithReturnBody() {
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        StringMap putPolicy = new StringMap();
        long expireSeconds = 3600;
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"belongId\":$(x:belongId),\"fname\":$(fname)}");
        return auth.uploadToken(QI_NIU_BUCKET_NAME, null, expireSeconds, putPolicy);
    }

    /**
     * 获得下载路径
     * 传入完整的Key值获得下载路径,默认过期时间3600s
     *
     * @param fileKey
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getDownloadUrl(String fileKey) throws UnsupportedEncodingException {
        long expireInSeconds = 3600;
        return getDownloadUrl(fileKey, expireInSeconds);
    }

    public static String getDownloadPhotoUrl(String fileName,String photpSheet,long expireInSeconds) throws UnsupportedEncodingException {
        if(fileName==null||fileName.compareTo("")==0){
            return  "";
        }
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        String domainOfBucket = QI_NIU_DOWNLOAD_URL;
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        if(photpSheet!=null&&fileName.compareTo("")!=0){
            publicUrl = publicUrl+"-"+photpSheet;
        }
        return  auth.privateDownloadUrl(publicUrl,expireInSeconds);
    }

    private static String getDownloadUrl(String fileName, long expireInSeconds) throws UnsupportedEncodingException {
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        String domainOfBucket = QI_NIU_DOWNLOAD_URL;
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }

    /**
     * 删除单个七牛云上的文件
     * @param key
     */
    public static void deleteOneKey(String key){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        String accessKey = QI_NIU_ACCESS_KEY;
        String secretKey = QI_NIU_SECRET_KEY;
        String bucket = QI_NIU_BUCKET_NAME;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 删除多个七牛云上的文件
     * @param keyList
     */
    public static void deleteUseKeys(String[] keyList) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        String bucket = QI_NIU_BUCKET_NAME;
        Auth auth = Auth.create(QI_NIU_ACCESS_KEY, QI_NIU_SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            //单次批量请求的文件数量不得超过1000
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 服务器端上传文件，并返回文件的key,默认为文件的hash值
     *
     * @param file
     * @return
     */
    public static String uploadToServer(MultipartFile file, String fileName) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = QI_NIU_ACCESS_KEY;
        String secretKey = QI_NIU_SECRET_KEY;
        String bucket = QI_NIU_BUCKET_NAME;
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "";
        if (fileName != null && fileName.compareTo("") != 0) {
            key = fileName;
        } else {
            String originalFileName = file.getOriginalFilename();
            key = UuidUtil.get32UUID() + originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
        }
        try {
            byte[] uploadBytes = file.getBytes();
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet.key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 判断当前key是否存在，存在则返回文件的类型，不存在返回空字符串
     * @param key
     * @return
     */
    public String checkFileIsSave(String key) {
//构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
//...其他参数参考类注释
        String accessKey = QI_NIU_ACCESS_KEY;
        String secretKey = QI_NIU_SECRET_KEY;
        String bucket = QI_NIU_BUCKET_NAME;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            return fileInfo.mimeType;
        } catch (QiniuException ex) {
            return "";
        }
    }
}
