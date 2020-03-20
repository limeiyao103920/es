package com.bawei.domain;

import java.io.Serializable;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Document(indexName = "test_user",type = "user")
public class User implements Serializable {

	@Id
	private int id;
	//保存规则 1 是否是索引 2 是否存储 3 name分词方式 4 搜索关键词方式 5 数据类型保存
	@Field(index=true,store=true,analyzer="ik_smart",searchAnalyzer="ik_smart",type=FieldType.text)
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
