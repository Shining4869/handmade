package com.handmade.cn.mapper;

import com.handmade.cn.entity.Sequence;

public interface SequenceMapper {
    int deleteByPrimaryKey(String seqCode);

    int insert(Sequence record);

    int insertSelective(Sequence record);

    Sequence selectByPrimaryKey(String seqCode);

    int updateByPrimaryKeySelective(Sequence record);

    int updateByPrimaryKey(Sequence record);
}