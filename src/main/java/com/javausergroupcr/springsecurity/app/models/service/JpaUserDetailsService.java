package com.javausergroupcr.springsecurity.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javausergroupcr.springsecurity.app.models.dao.IUserDao;
import com.javausergroupcr.springsecurity.app.models.entity.DBAuthority;
import com.javausergroupcr.springsecurity.app.models.entity.DBUser;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DBUser dbUser = userDao.findByUsername(username);

		if (null == dbUser) {
			throw new UsernameNotFoundException("Usuario: " + username + " no existe en el sistema");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (DBAuthority dbAuthority : dbUser.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(dbAuthority.getAuthority()));
		}

		if (authorities.isEmpty()) {
			throw new UsernameNotFoundException("Usuario '" + username + "' no tiene roles asignados");
		}

		return new User(dbUser.getUsername(), dbUser.getPassword(), dbUser.getEnabled(), true, true, true, authorities);
	}

}
