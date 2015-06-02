package com.handmade.cn.entity.user;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.handmade.cn.entity.BaseEntity;

@SuppressWarnings("serial")
public class UserInfo extends BaseEntity{
    private Integer id;

    private String contestantName;

    private String openid;

    private String mobile;
    
    private String weixin;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date firstTime;

    private String firstAp;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date secondTime;

    private String secondAp;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String more1;

    private String more2;

    private String more3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName == null ? null : contestantName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public String getFirstAp() {
        return firstAp;
    }

    public void setFirstAp(String firstAp) {
        this.firstAp = firstAp == null ? null : firstAp.trim();
    }

    public Date getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(Date secondTime) {
        this.secondTime = secondTime;
    }

    public String getSecondAp() {
        return secondAp;
    }

    public void setSecondAp(String secondAp) {
        this.secondAp = secondAp == null ? null : secondAp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMore1() {
        return more1;
    }

    public void setMore1(String more1) {
        this.more1 = more1 == null ? null : more1.trim();
    }

    public String getMore2() {
        return more2;
    }

    public void setMore2(String more2) {
        this.more2 = more2 == null ? null : more2.trim();
    }

    public String getMore3() {
        return more3;
    }

    public void setMore3(String more3) {
        this.more3 = more3 == null ? null : more3.trim();
    }

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
    
    
}