package com.zhwl.home_server.mapper.customerservice;
import com.zhwl.home_server.bean.customerservice.CustomerService;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 客服管理
 * 创建人：chenguihao
 * 创建时间：2018-11-26
 */
public interface CustomerServiceMapper {

    Integer save(CustomerService customerService);

    Integer saveByList(List<CustomerService> customerServices);

    Integer updateBySelective(CustomerService customerService);

    Integer deleteOne(String id);

    List<CustomerService> selectBySelective(CustomerService customerService);

    List<CustomerService> getCustomerServiceByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
