package com.handmade.cn.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.handmade.cn.entity.Sequence;
import com.handmade.cn.entity.SequenceValue;
import com.handmade.cn.mapper.SequenceMapper;


@Transactional
@Service
public class SequenceService {

	@Autowired
	private SequenceMapper sequenceMapper;
	
	/**
	 *  针对并发,获取不同值 SeqValue, UpdateTime 相同
	 * @param code
	 * @param reset
	 * @return
	 */
	@Transactional
	public SequenceValue nextSequence(String code, boolean reset) {

		Sequence seq = sequenceMapper.selectByPrimaryKey(code);
		if (seq != null) {
			Date now = new Date();
			Long ret = seq.getSeqValue();
			ret++;
			if (reset) {
				if (seq.getUpdateTime() == null)
					ret = 1l;
				else {
					Calendar c = Calendar.getInstance();
					c.setTime(now);
					int today = c.get(Calendar.DATE);
					c.setTime(seq.getUpdateTime());
					if (c.get(Calendar.DATE) != today)
						ret = 1l;
				}
			}
			seq.setSeqValue(ret);
			seq.setUpdateTime(now);
			sequenceMapper.updateByPrimaryKeySelective(seq);
			return new SequenceValue(ret, now);
		}

		return null;
	}
}
