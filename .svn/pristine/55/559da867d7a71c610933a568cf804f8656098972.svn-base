package com.handmade.cn.mapper.user;

import com.handmade.cn.entity.user.UserInfo;
import com.handmade.cn.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper<UserInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo findByOpenId(String openid);
    
    int selectByFirstTime(UserInfo userInfo);
    
    int selectBySecondTime(UserInfo userInfo);
}