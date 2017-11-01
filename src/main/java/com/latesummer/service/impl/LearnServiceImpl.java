package com.latesummer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latesummer.dao.LearnDao;
import com.latesummer.domain.entity.LearnResouce;
import com.latesummer.service.ILearnService;
import com.latesummer.utils.Constants;
import com.latesummer.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Create By Jenvi Sue On 2017年10月25日
 */
@Service
@Transactional
public class LearnServiceImpl implements ILearnService {

    @Autowired
    private LearnDao learnDao;
    
    @Override
    public void add(LearnResouce learnResouce) {
    	learnDao.save(learnResouce);
    }

    @Override
    public void update(LearnResouce learnResouce) {
    	learnDao.save(learnResouce);
    }

    @Override
    public void deleteByIds(String[] ids) {
    	for (String id : ids) {
    		Long parseId = Long.valueOf(id);
    		learnDao.delete(parseId);
		}
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
    	return learnDao.findOne(id);
    }

    @Override
    Page<LearnResouce> learnResouceListByPage(Map<String, Object> params, PageRequest pageRequest); {
    	Specification<User> spec = new Specification<User>() {  
			@Override
			public Predicate toPredicate(Root<User> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();  
				String type = params.get("type");
				String status= params.get("status");
				String username = params.get("username");
				String name = params.get("name");
				
			    if(StringUtil.isNotBlank(type)){  
			        list.add(cb.equal(root.get("type").as(Integer.class), NumberUtils.toInt(type)));  
			    }  
			    if(StringUtil.isNotBlank(status)){  
			        list.add(cb.equal(root.get("status").as(Integer.class), NumberUtils.toInt(status)));  
			    }
			    if(StringUtil.isNotBlank(username)){  
			        list.add(cb.like(root.get("username").as(String.class), String.format("%%%s%%", username)));  
			    }  
			    if(StringUtil.isNotBlank(name)){  
			    	list.add(cb.like(root.get("name").as(String.class), String.format("%%%s%%", name)));   
			    }
			    list.add(cb.equal(root.get("del"), Constants.DEL_NO));
			    Predicate[] p = new Predicate[list.size()];  
			    return cb.and(list.toArray(p));  
				
				//in条件查询
				/*List<Integer> ids = Lists.newArrayList();
				ids.add(1);
				ids.add(2);
				In<Integer> in = cb.in(root.get("id").as(Integer.class));
				in.value(1);
				in.value(2);
			    return cb.or(in);*/
			}  
		};  
		Page<User> page = userDao.findAll(spec, pageable);
		return page;
        return learnDao.findAll(pageRequest);
    }
}
