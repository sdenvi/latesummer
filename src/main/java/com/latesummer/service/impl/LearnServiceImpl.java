package com.latesummer.service.impl;

import com.latesummer.dao.LearnMapper;
import com.latesummer.domain.LearnResouce;
import com.latesummer.domain.LearnResouceRepository;
import com.latesummer.domain.UserRepository;
import com.latesummer.service.ILearnService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *Cteate By Jenvi Sue On 2017年9月19日
 */
@Service
public class LearnServiceImpl implements ILearnService {

	@Autowired
    private LearnResouceRepository learnResouceRepository;
    @Override
    public void saveLearnResouce(LearnResouce learnResouce) {
        return learnResouceRepository.save(learnResouce);
    }

    @Override
    public int update(LearnResouce learnResouce) {
        return this.learnMapper.update(learnResouce);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.learnMapper.deleteByIds(ids);
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnMapper.queryLearnResouceById(id);
    }

    @Override
    public List<LearnResouce> queryLearnResouceList(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnMapper.queryLearnResouceList(params);
    }
}
