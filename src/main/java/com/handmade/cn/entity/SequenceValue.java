package com.handmade.cn.entity;

import java.util.Date;

public class SequenceValue {
	
	private Long value;
	private Date date;

	public SequenceValue(Long value, Date date) {
		super();
		this.value = value;
		this.date = date;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
