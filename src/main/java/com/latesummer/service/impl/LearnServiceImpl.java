package com.latesummer.service.impl;

import com.latesummer.common.dao.BaseDao;
import com.latesummer.common.service.BaseServiceImpl;
import com.latesummer.domain.LearnResouce;
import com.latesummer.repository.LearnResouceRepository;
import com.latesummer.service.LearnService;
import com.latesummer.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Jenvi Sue
 * @Date 2017/12/11 14:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LearnServiceImpl extends BaseServiceImpl<LearnResouce, Long> implements LearnService {

    @Autowired
    private LearnResouceRepository learnResouceRepository;
    
    @Override
    public void add(LearnResouce learnResouce) {
    	learnResouceRepository.save(learnResouce);
    }

    @Override
    public void update(LearnResouce learnResouce) {
    	learnResouceRepository.save(learnResouce);
    }

    @Override
    public void deleteByIds(String[] ids) {
    	for (String id : ids) {
    		Long parseId = Long.valueOf(id);
    		learnResouceRepository.delete(parseId);
		}
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
    	return learnResouceRepository.findOne(id);
    }

    @Override
	public Page<LearnResouce> learnResouceListByPage(final Map<String, String> params,Pageable pageable){
    	Specification<LearnResouce> spec = new Specification<LearnResouce>() {  
			@Override
			public Predicate toPredicate(Root<LearnResouce> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				String name = params.get("name");
				String author= params.get("author");
			    /*if(StringUtil.isNotBlank(type)){  
			        list.add(cb.equal(root.get("type").as(Integer.class), NumberUtils.toInt(name)));  
			    }*/  
				if(StringUtil.isNotBlank(name)){  
			        list.add(cb.equal(root.get("name"), name)); 
			    }
			    if(StringUtil.isNotBlank(author)){  
			        list.add(cb.equal(root.get("author"), author));  
			    }
			    /*list.add(cb.equal(root.get("del"), Constants.DEL_NO));*/
			    Predicate[] p = new Predicate[list.size()];
			    return cb.and(list.toArray(p));  
			}  
		};  
		Page<LearnResouce> page = learnResouceRepository.findAll(spec, pageable);
		return page;
    }

	@Override
	public BaseDao<LearnResouce, Long> getDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}
