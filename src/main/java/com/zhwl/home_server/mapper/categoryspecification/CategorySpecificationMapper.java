package com.zhwl.home_server.mapper.categoryspecification;
import com.zhwl.home_server.bean.categoryspecification.CategorySpecification;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 类别规格管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */
public interface CategorySpecificationMapper {

    Integer save(CategorySpecification categorySpecification);

    Integer saveByList(List<CategorySpecification> categorySpecifications);

    Integer updateBySelective(CategorySpecification categorySpecification);

    Integer deleteOne(String id);

    List<CategorySpecification> selectBySelective(CategorySpecification categorySpecification);

    List<CategorySpecification> getCategorySpecificationByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
