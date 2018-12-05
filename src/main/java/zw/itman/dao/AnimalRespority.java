package zw.itman.dao;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import zw.itman.entity.Animal;

/**
* @author: zhengwei E-mail:613295775@qq.com
* @version: 创建时间：2018年12月4日 上午10:42:43
* @description:
*/
@Repository
public interface AnimalRespority  extends JpaRepository<Animal, Integer>,CrudRepository<Animal, Integer>,JpaSpecificationExecutor<Animal>{
	
	Animal findByName(String name);
	
	@Query("select a from Animal a where a.type=?")
	Animal findType(String type);
	
	Animal findById(int id);
	
	
	@Modifying
	@Query(value="update Animal set name=?1,type=?2 where id=?3")
	int updateById(String name,String type,Integer id);
	
	
	@Modifying
	@Query(value="delete from Animal where id=?") //通过主键删除
	int deleteById(int id);
	

}
