package com.latesummer.dao;

import com.latesummer.base.BaseRepository;
import com.latesummer.model.entity.LearnResouce;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
public interface LearnRepository  extends BaseRepository<LearnResouce, Long> {
	//int deleteById(Long id);
    //int deleteByIds(String[] ids);
    LearnResouce findById(Long id);
    public Page<LearnResouce> findAll(Pageable pageable);
}
