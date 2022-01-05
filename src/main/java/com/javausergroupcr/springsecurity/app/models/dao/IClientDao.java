package com.javausergroupcr.springsecurity.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.javausergroupcr.springsecurity.app.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long> {

}
