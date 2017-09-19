package com.latesummer.service;

import com.latesummer.domain.LearnResouce;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Jenvi Sue
 *
 */

public interface ILearnService {
    /*int add(LearnResouce learnResouce);
    int update(LearnResouce learnResouce);
    int deleteByIds(String[] ids);
    LearnResouce queryLearnResouceById(Long learnResouce);
    List<LearnResouce> queryLearnResouceList(Map<String, Object> params);*/
    
    
    
	public List<LearnResouce> findAll();

    public void saveLearnResouce(LearnResouce book);
   
    public LearnResouce findOne(long id);

    public void delete(long id);

    public List<LearnResouce> findByName(String name);
}
