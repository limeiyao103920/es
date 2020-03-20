package com.bawei;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.dao.UserRespository;
import com.bawei.domain.User;
import com.bawei.util.HLUtils;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:es.xml")
public class UserTest {

	@Autowired
	UserRespository respository;
	
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	
	
	//添加和修改（已经存在的id就修改  不存在则添加）
	@Test
	public void testSave() {
		User user = new User();
		user.setId(1);
		user.setName("无聊没事干点啥，没事玩狗子!!");
		
		respository.save(user);
		System.out.println("保存成功");
	}
	//删除
	@Test
	public void del() {
		//指定id就可以
		respository.deleteById(2);
	}
	//查询
	@Test
	public void testFind() {
		//查找所有
		Iterable<User> findAll = respository.findAll();
		/*
		 * for (User user : findAll) { System.out.println(user); }
		 */
		
		List<User> byName = respository.findByName("欺负");
		System.out.println(byName);
	}
	//高亮显示
	@Test
	public void testHighLight() {
		//1.搜索模板 2. 指定实体类类型  3.当前页  4.每页显示个数 5.string类型数组  存放的是根据关键词搜索 的字段  （与实体类字段一致）6.指定排序的字段 7.关键字
		PageInfo<?> info = HLUtils.findByHighLight(elasticsearchTemplate, User.class, 1, 5, new String[] {"name"}, "id", "欺负");
		List<?> list = info.getList();
		for (Object object : list) {
			System.out.println(object);
		}
	}
}
