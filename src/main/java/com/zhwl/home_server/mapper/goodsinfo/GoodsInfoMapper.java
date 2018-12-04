package com.zhwl.home_server.mapper.goodsinfo;
import com.zhwl.home_server.bean.goodsinfo.GoodsInfo;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 商品信息管理
 * 创建人：chenguihao
 * 创建时间：2018-12-03
 */
public interface GoodsInfoMapper {

    Integer save(GoodsInfo goodsInfo);

    Integer saveByList(List<GoodsInfo> goodsInfos);

    Integer updateBySelective(GoodsInfo goodsInfo);

    Integer deleteOne(String id);

    List<GoodsInfo> selectBySelective(GoodsInfo goodsInfo);

    List<GoodsInfo> getGoodsInfoByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
