package com.zhwl.home_server.mapper.goodscategory;
import com.zhwl.home_server.bean.goodscategory.GoodsCategory;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 商品类别管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */
public interface GoodsCategoryMapper {

    Integer save(GoodsCategory goodsCategory);

    Integer saveByList(List<GoodsCategory> goodsCategorys);

    Integer updateBySelective(GoodsCategory goodsCategory);

    Integer deleteOne(String id);

    List<GoodsCategory> selectBySelective(GoodsCategory goodsCategory);

    List<GoodsCategory> getGoodsCategoryByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);

    List<GoodsCategory> selectByIds(String[] ids);
}
