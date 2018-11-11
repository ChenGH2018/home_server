package com.zhwl.home_server.util;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 打包控件，将文件写入到zip文件中
 */
public class PackUtil {
    /**
     * 参数说明：
     * List<File> file：定义文件类链表，存放多个文件。
     * String zippath：压缩包的完整路径如：D:\....\xx.zip
     *
     * */
    public static boolean  pack(List<File> file,String zippath)  {
        File zipFile = new File(zippath);//最终打包的压缩包
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        zipSource = null;//将源头文件格式化为输入流
        ZipEntry zipEntry=null;
            try {
                zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
                for(File file1:file) {
                    zipSource = new FileInputStream(file1);
                    byte[] bufferArea = new byte[1024 * 10];//读写缓冲区
                    //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                    zipEntry = new ZipEntry(file1.getName());
                    zipStream.putNextEntry(zipEntry);//定位到该压缩条目位置，开始写入文件到压缩包中
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);//输入缓冲流
                    int read = 0;
                    //在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1] 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                    while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                        zipStream.write(bufferArea, 0, read);
                    }
                }
                return true;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } finally {
                //关闭流
                try {
                    if (null != bufferStream) bufferStream.close();
                    if (null != zipStream) zipStream.close();
                    if (null != zipSource) zipSource.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
    }

}
