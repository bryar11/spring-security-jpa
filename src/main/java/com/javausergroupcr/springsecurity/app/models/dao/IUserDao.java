package com.javausergroupcr.springsecurity.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.javausergroupcr.springsecurity.app.models.entity.DBUser;

public interface IUserDao extends CrudRepository<DBUser, Long> {

	public DBUser findByUsername(String username);
}
