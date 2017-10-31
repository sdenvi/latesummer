package com.latesummer.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.latesummer.domain.entity.LearnResouce;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */

public interface ILearnService {
    void add(LearnResouce learnResouce);
    void update(LearnResouce learnResouce);
    void deleteByIds(String[] ids);
    LearnResouce queryLearnResouceById(Long learnResouce);
	Page<LearnResouce> queryLearnResouceList(Pageable pageable);
}
