package com.latesummer.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.latesummer.common.service.BaseService;
import com.latesummer.domain.entity.LearnResouce;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */

public interface ILearnService extends BaseService<LearnResouce, Integer>{
    void add(LearnResouce learnResouce);
    void update(LearnResouce learnResouce);
    void deleteByIds(String[] ids);
    LearnResouce queryLearnResouceById(Long learnResouce);
	Page<LearnResouce> queryLearnResouceList(Map<String, Object> params, PageRequest pageRequest);
}
