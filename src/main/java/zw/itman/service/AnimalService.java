package zw.itman.service;
/**
* @author: zhengwei E-mail:613295775@qq.com
* @version: 创建时间：2018年12月4日 上午10:48:39
* @description:
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import zw.itman.dao.AnimalRespority;
import zw.itman.entity.Animal;
@Service
public class AnimalService {
	
	@Autowired
	private AnimalRespority animalRespority;
	
	@Transactional
	public Animal insert(Animal animal) {
		return animalRespority.save(animal);
	}
	
	public Animal findOne(int id) {
		
		return animalRespority.findOne(id);
	}
	
	public Animal findByName(String name) {
		return animalRespority.findByName(name);
	}
	public List<Animal> findall() {
		return animalRespority.findAll();
	}
	public Animal findtype(String type) {
		return animalRespority.findType(type);
	}
	
	public Animal findById(int id) {
		return animalRespority.findById(id);
	}
	
	@Transactional
	public Animal updateById(Animal animal) {
//		String name=animal.getName();
//		int id=animal.getId();
		return animalRespority.saveAndFlush(animal);
	}
	
	@Transactional
	public int deleteById(int id) {
//		String name=animal.getName();
//		int id=animal.getId();
		return animalRespority.deleteById(id);
	}
	
	
	public Page<Animal> findAll(PageRequest pageRequest) {
		 Page<Animal> list = animalRespority.findAll(pageRequest);
		 return list;
	}
	
	public Map showPage() {
		Map map=new HashMap<>();
		Sort sort = new Sort(Sort.Direction.DESC,"id");
        //构造PageRequest分页条件，第一页、每页10行、排序条件
		
        PageRequest pageRequest = new PageRequest(0,3, sort);
        Page<Animal> page;
        Specification<Animal> spec = new Specification<Animal>() {
			public Predicate toPredicate(Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				list.add(cb.between(root.<Integer>get("id"), 2, 6));
				list.add(cb.like(root.<String>get("name"), "wang%"));
				return cb.and(list.toArray(new Predicate[0]));
			}
		};
		page=animalRespority.findAll(spec, pageRequest);
		long totalElements = page.getTotalElements();
		List<Animal> list = page.getContent();
		map.put("total", totalElements);
		map.put("animalList", list);
		return map;
		
		
	}
}
