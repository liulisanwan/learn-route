package com.liuli.cmp.bean;

import com.yomahub.liteflow.context.ContextBean;

import java.util.Date;

@ContextBean("order")
public class OrderContext {

	private String orderNo;

	private int orderType;

	private Date createTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
