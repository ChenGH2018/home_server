package com.zhwl.home_server.service.serviceinfo.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.serviceinfo.ServiceInfo;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.serviceinfo.ServiceInfoMapper;
import com.zhwl.home_server.service.serviceinfo.ServiceInfoService;
import com.zhwl.home_server.util.QiNiuUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 说明： 服务信息
 * 创建人：chenguihao
 * 创建时间：2018-11-23
 */

@Service
@Transactional
@Log4j
public class ServiceInfoServiceImpl implements ServiceInfoService {

    @Autowired
    ServiceInfoMapper serviceInfoMapper;

    @Override
    public Integer save(ServiceInfo serviceInfo) throws BaseException {
        serviceInfo.setIsDelete(0);
        if (serviceInfo.getIsOnline() == null) serviceInfo.setIsOnline(0);
        return serviceInfoMapper.save(serviceInfo);
    }

    @Override
    public Integer deleteOne(String serviceInfoId) throws BaseException {
        return serviceInfoMapper.deleteOne(serviceInfoId);
    }

    @Override
    public Integer updateBySelective(ServiceInfo serviceInfo) throws BaseException {
        if (Strings.isNullOrEmpty(serviceInfo.getId())) throw new RuntimeException("要修改的ID不能为空");
        return serviceInfoMapper.updateBySelective(serviceInfo);
    }


    @Override
    public ServiceInfo selectById(String id) throws BaseException {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setId(id);
        List<ServiceInfo> serviceInfos = serviceInfoMapper.selectBySelective(serviceInfo);
        return serviceInfos != null && serviceInfos.size() == 1 ? serviceInfos.get(0) : null;
    }

    @Override
    public List<ServiceInfo> selectBySelective(ServiceInfo serviceInfo) throws BaseException {
        List<ServiceInfo> serviceInfos = serviceInfoMapper.selectBySelective(serviceInfo);
        analysisKey(serviceInfos);
        return serviceInfos;

    }

    @Override
    public List<ServiceInfo> getServiceInfoByPage(Page pg) throws BaseException {
        List<ServiceInfo> serviceInfoByPage = serviceInfoMapper.getServiceInfoByPage(pg);
        analysisKey(serviceInfoByPage);
        return serviceInfoByPage;
    }

    @Override
    public Integer saveByList(List<ServiceInfo> serviceInfos) throws BaseException {
        return serviceInfoMapper.saveByList(serviceInfos);
    }

    /**
     * 批量物理删除
     *
     * @param ids
     * @throws BaseException
     */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException {
        return serviceInfoMapper.physicsDeleteArray(ids);
    }

    /**
     * 批量软删除
     *
     * @param ids
     * @throws BaseException
     */
    public Integer softDeleteArray(String[] ids) throws BaseException {
        return serviceInfoMapper.softDeleteArray(ids);
    }

    /**
     * 解析private空间的图片key，生成访问的url
     */
    public Boolean analysisKey(List<ServiceInfo> ServiceInfos) {
        ServiceInfos.stream().parallel().forEach(it -> {
            if (!Strings.isNullOrEmpty(it.getServiceImgKey())) {
                try {
                    String[] serviceImgKeys = it.getServiceImgKey().split(",");
                    it.setServiceImg("");
                    for (int i = 0; i < serviceImgKeys.length; i++) {
                        it.setServiceImg(it.getServiceImg() + QiNiuUtil.getDownloadUrl(serviceImgKeys[i]) + (i == serviceImgKeys.length - 1 ? "" : ","));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }
}

