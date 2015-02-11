package com.caveofprogramming.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@Service("UsersService")
public class UsersService {

	private UsersDao usersDao;

	@Autowired
	public void setOffersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void create(User user) {

		usersDao.create(user);

	}

	public boolean exists(String username) {
		return usersDao.exists(username);
	}

	// Using method-level access control
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		
		return usersDao.getAllUsers();
		
	}

}
