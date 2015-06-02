package com.handmade.cn.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String sort;
    
    private Integer pageNo = 1;
    
    private Integer pageSize = 10;

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
    private Map<String,String> orderExprs = new HashMap<String, String>();

	public void addOrderExpr(String property, String expr){
        this.orderExprs.put(property,expr);
    }
	
	public PageBounds toPageBounds(){
        List<Order> orders = Order.formString(sort);
        for (int i = 0; i < orders.size(); i++) {
            Order order =  orders.get(i);
            if(orderExprs.get(order.getProperty()) != null){
                orders.set(i, new Order(
                        order.getProperty(),
                        order.getDirection(),
                        orderExprs.get(order.getProperty()))
                );
            }
        }
        return new PageBounds(pageNo, pageSize, orders);
    }

}

