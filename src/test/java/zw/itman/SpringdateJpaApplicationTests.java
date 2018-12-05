package zw.itman;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import zw.itman.entity.Animal;
import zw.itman.service.AnimalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdateJpaApplicationTests {

	@Autowired
	private AnimalService service;
	
	@Test
	@Ignore
	public void contextLoads() {
		String name="鲨鱼";       //批量添加数据
		String type="海洋生物";
		for(int i=0;i<=6;i++) {
			Animal aa=new Animal();
			aa.setName(name+i);
			aa.setType(type+i);
			service.insert(aa);
		}
	}
	
	@Test
	@Ignore
	public void findByNameTest() {
		Animal findByName = service.findByName("鲨鱼2");
		System.err.println(findByName.toString());
	}
	@Test
	@Ignore
	public void findOneTest() {
		Animal findOne = service.findOne(1);
		System.err.println(findOne.toString());
	}
	@Test
	@Ignore
	public void findAll() {
		List<Animal> findAll = service.findall(); //查询所有的数据
		System.err.println(findAll.toString());
	}

	
	@Test
	@Ignore
	public void findTypeTest() {
		Animal findByName = service.findtype("海洋生物1"); //查询type值为海洋生物1的数据
		System.err.println(findByName.toString());
	}
	@Test
	@Ignore
	public void findById() {
		Animal findByName = service.findById(2);  //查询id值为2 的数据
		System.err.println(findByName.toString());
	}
	
	@Test
	@Ignore
	public void updateById() {
		Animal animal = service.findById(2); //更新id值为2的字段为name的值
		animal.setName("wangwu9");
		//animal.setType("陆地生物2");
		System.err.println(service.updateById(animal));
	}
	
	@Test
	public void deleteById() {
		System.err.println(service.deleteById(7)); //通过主键删除
	}
	
	@Test
	@Ignore
	//分页查询
	public void findPage() {
		
		Map map=new HashMap<>();
		Sort sort = new Sort(Sort.Direction.DESC,"id");
        //构造PageRequest分页条件，第一页、每页10行、排序条件
        PageRequest pageRequest = new PageRequest(2,3, sort);
        
        Page<Animal> page = service.findAll(pageRequest);
        int size = page.getSize();
        int number = page.getNumber();
        List<Animal> content = page.getContent();
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        Sort sort2 = page.getSort();
        System.err.println("--------每页显示几条----------"+size);
        System.err.println("---------总页数----------"+totalPages);
        System.err.println("-----------内容-----------"+content.toString());
        System.err.println("--------排序方式---------"+sort2);
        System.err.println("--------总条数---------"+totalElements);
        System.err.println("--------当前页数---------"+number);

	}
	
	@Test
	@Ignore
	public void showPage() {
		Map map = service.showPage();
		System.err.println(map.get("animalList").toString());
		System.err.println(map.get("total"));
		
	}
}
