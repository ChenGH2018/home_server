package com.zhwl.home_server.service.goodsinfo.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.goodsinfo.GoodsInfo;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.goodsinfo.GoodsInfoMapper;
import com.zhwl.home_server.service.goodsinfo.GoodsInfoService;
import com.zhwl.home_server.system.WhetherEnum;
import com.zhwl.home_server.util.QiNiuUtil;
import com.zhwl.home_server.util.SysUserUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明： 商品信息管理
 * 创建人：chenguihao
 * 创建时间：2018-12-03
 */

@Service
@Transactional
@Log4j
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public Integer save(GoodsInfo goodsInfo) throws BaseException {
        if(null == SysUserUtil.getCurrentShopBasic())
            throw new BaseException("拒绝操作：非商家用户");
        validateGoodsInfo(goodsInfo);
        goodsInfo.setIsDelete(WhetherEnum.NO.ordinal());
        //-----未设置商品编号
        goodsInfo.setShopBasicId(SysUserUtil.getCurrentShopBasicId());
        return goodsInfoMapper.save(goodsInfo);
    }

    private void validateGoodsInfo(GoodsInfo goodsInfo) {
        if(Strings.isNullOrEmpty(goodsInfo.getGoodsName()))
            throw new RuntimeException("商品名称不能为空");
//        if(null == goodsInfo.getGoodsSpec() || goodsInfo.getGoodsSpec().size() <= 0)
//            throw new RuntimeException("商品规格不能为空");
        if(null == goodsInfo.getIsAdded())
            throw new BaseException("是否上架不能为空");
        if(null == goodsInfo.getImgKeys())
            throw new BaseException("商品主图不能为空");
        if(null == goodsInfo.getGoodsCategoryId())
            throw new BaseException("商品类别不能为空");
    }

    @Override
    public Integer deleteOne(String goodsInfoId) throws BaseException {
        return goodsInfoMapper.deleteOne(goodsInfoId);
    }

    @Override
    public Integer updateBySelective(GoodsInfo goodsInfo) throws BaseException {
        if(Strings.isNullOrEmpty(goodsInfo.getId())) throw new RuntimeException("要修改的ID不能为空");
        return goodsInfoMapper.updateBySelective(goodsInfo);
    }


    @Override
    public GoodsInfo selectById(String id) throws BaseException{
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setId(id);
        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectBySelective(goodsInfo);
        return goodsInfos != null && goodsInfos.size() == 1 ? goodsInfos.get(0) : null;
    }

    @Override
    public List<GoodsInfo> selectBySelective(GoodsInfo goodsInfo) throws BaseException{
        List<GoodsInfo> goodsInfos = goodsInfoMapper.selectBySelective(goodsInfo);
        analysisKey(goodsInfos);
        return goodsInfos;

    }

    @Override
    public List<GoodsInfo> getGoodsInfoByPage(Page pg) throws BaseException{
        List<GoodsInfo> goodsInfoByPage = goodsInfoMapper.getGoodsInfoByPage(pg);
        analysisKey(goodsInfoByPage);
        return goodsInfoByPage;
    }

    @Override
    public Integer saveByList(List<GoodsInfo> goodsInfos) throws BaseException{
        goodsInfos.stream().parallel().forEach(it ->{
            validateGoodsInfo(it);
            it.setIsDelete(WhetherEnum.NO.ordinal());
            it.setShopBasicId(SysUserUtil.getCurrentShopBasicId());
        });
        return goodsInfoMapper.saveByList(goodsInfos);
    }

    /**
    * 批量物理删除
    * @param ids
    * @throws BaseException
    */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException{
        return goodsInfoMapper.physicsDeleteArray(ids);
    }
    /**
    * 批量软删除
    * @param ids
    * @throws BaseException
    */
    public Integer softDeleteArray(String[] ids) throws BaseException{
        return goodsInfoMapper.softDeleteArray(ids);
    }
    /**
     * 解析private空间的图片key，生成访问的url
     */
    private Boolean analysisKey(List<GoodsInfo> goodsInfos) {
        goodsInfos.stream().parallel().forEach(it -> {
            it.setImgUrls(QiNiuUtil.keysToUrls(it.getImgKeys()));
        });
        return true;
    }
}

