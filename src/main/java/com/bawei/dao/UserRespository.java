package com.bawei.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bawei.domain.User;

public interface UserRespository extends ElasticsearchRepository<User, Integer> {

	List<User> findByName(String name);
}
