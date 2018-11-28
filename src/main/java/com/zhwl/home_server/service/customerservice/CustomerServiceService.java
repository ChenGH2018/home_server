package com.zhwl.home_server.service.customerservice;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.customerservice.CustomerService;
import com.zhwl.home_server.exception.BaseException;

import java.util.List;

/**
 * 说明： 客服管理
 * 创建人：chenguihao
 * 创建时间：2018-11-26
 */
public interface CustomerServiceService {


    /**
     * 新增
     *
     * @param customerService
     * @throws BaseException
     */
    Integer save(CustomerService customerService) throws BaseException;

    /**
     * 单个删除
     *
     * @param id
     * @throws BaseException
     */
    Integer deleteOne(String id) throws BaseException;

    /**
     * 修改不为null的字段
     * 主键不能为null
     *
     * @param customerService
     */
    Integer updateBySelective(CustomerService customerService) throws BaseException;

    CustomerService selectById(String customerServiceId) throws BaseException;

    /**
     * 查询不为null的字段
     *
     * @param customerService
     * @return
     */
    List<CustomerService> selectBySelective(CustomerService customerService) throws BaseException;

    /**
     * 列表,分页查询
     *
     * @param pg
     * @throws BaseException
     */
    List<CustomerService> getCustomerServiceByPage(Page pg) throws BaseException;

    /**
     * 批量添加
     *
     * @param customerService
     * @throws BaseException
     */
    Integer saveByList(List<CustomerService> customerServices) throws BaseException;

    /**
     * 批量物理删除
     *
     * @param ids
     * @throws BaseException
     */
    Integer physicsDeleteArray(String[] ids) throws BaseException;

    boolean addCustomerService(CustomerService customerService);


    Integer softDeleteArray(String[] ids) throws BaseException;
}

