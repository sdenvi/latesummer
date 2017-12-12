package com.latesummer.repository;

import com.latesummer.common.dao.BaseDao;
import com.latesummer.domain.LearnResouce;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
public interface LearnResouceRepository  extends BaseDao<LearnResouce, Long> {
    LearnResouce findById(Long id);
}
