package com.handmade.cn.bean.product;

import java.util.Date;

import com.handmade.cn.bean.SearchBean;
import com.handmade.cn.entity.BaseEntity;

public class ProductInfoBean extends SearchBean{
    private Integer id;

    private String title;

    private String author;

    private String mobile;

    private String weixin;

    private String type;

    private String image;

    private String content;

    private String materials;

    private Double preQuality;

    private Integer greatNum;

    private String createUser;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials == null ? null : materials.trim();
    }

    public Double getPreQuality() {
        return preQuality;
    }

    public void setPreQuality(Double preQuality) {
        this.preQuality = preQuality;
    }

    public Integer getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(Integer greatNum) {
        this.greatNum = greatNum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
}