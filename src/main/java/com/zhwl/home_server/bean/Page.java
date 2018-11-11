package com.zhwl.home_server.bean;


import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;

/**
 * 分页类
 * @author liangzhilin
 * 创建时间：2014年6月28日
 */
public class Page {

	private int size; //每页显示记录数
	private int page;	//当前页
	@ApiModelProperty(hidden = true)
	private int totalPage;		//总页数
	@ApiModelProperty(hidden = true)
	private int totalResult;	//总记录数
	@ApiModelProperty(hidden = true)
	private int currentResult;	//当前记录起始索引
	@ApiModelProperty(hidden = true)
	private Object data;//存放查询结果
	@ApiModelProperty(hidden = true)
	private HashMap pd = new HashMap();//存放查询条件

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public HashMap getPd() {
		return pd;
	}

	public void setPd(HashMap pd) {
		this.pd = pd;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public Page(){
		try {
			this.size = 20;
			this.page = 1;
		} catch (Exception e) {
			this.size = 15;
			this.page = 1;
		}
	}

	public Page(int page, int size){
		this.page = page;
		this.size = size;
	}
	
	public int getTotalPage() {
		if(totalResult%size==0)
			totalPage = totalResult/size;
		else
			totalPage = totalResult/size+1;
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getTotalResult() {
		return totalResult;
	}
	
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	
	public int getPage() {
		if(page<=0)
			page = 1;
		if(page>getTotalPage())
			page = getTotalPage();
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getCurrentResult() {
		currentResult = (getPage()-1)*getSize();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}


}
