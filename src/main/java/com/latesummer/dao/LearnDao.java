package com.latesummer.dao;

import com.latesummer.common.dao.BaseDao;
import com.latesummer.domain.entity.LearnResouce;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
public interface LearnDao  extends BaseDao<LearnResouce, Long> {
    LearnResouce findById(Long id);
}
