package com.latesummer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latesummer.dao.LearnRepository;
import com.latesummer.model.entity.LearnResouce;
import com.latesummer.service.ILearnService;

import java.util.List;
import java.util.Map;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
@Service
@Transactional
public class LearnServiceImpl implements ILearnService {

    @Autowired
    private LearnRepository learnRepository;
    
    @Override
    public void add(LearnResouce learnResouce) {
        learnRepository.save(learnResouce);
    }

    @Override
    public void update(LearnResouce learnResouce) {
    	learnRepository.save(learnResouce);
    }

    @Override
    public void deleteByIds(String[] ids) {
    	for (String id : ids) {
    		Long parseId = Long.valueOf(id);
    		learnRepository.delete(parseId);
		}
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
    	return learnRepository.findOne(id);
    }

    @Override
    public Page<LearnResouce> queryLearnResouceList(Pageable pageable) {
        return learnRepository.findAll(pageable);
    }
}
