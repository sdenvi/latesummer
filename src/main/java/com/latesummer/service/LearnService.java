package com.latesummer.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.latesummer.common.service.BaseService;
import com.latesummer.domain.LearnResouce;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */

public interface LearnService extends BaseService<LearnResouce, Long> {
	void add(LearnResouce learnResouce);

	void update(LearnResouce learnResouce);

	void deleteByIds(String[] ids);

	LearnResouce queryLearnResouceById(Long id);

	Page<LearnResouce> learnResouceListByPage(Map<String, String> params, Pageable pageable);
}
