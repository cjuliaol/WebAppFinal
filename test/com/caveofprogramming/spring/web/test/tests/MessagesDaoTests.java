package com.caveofprogramming.spring.web.test.tests;


import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.MessagesDao;
import com.caveofprogramming.spring.web.dao.OffersDao;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/caveofprogramming/spring/web/config/dao-context.xml",
		"classpath:com/caveofprogramming/spring/web/config/security-context.xml",
		"classpath:com/caveofprogramming/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessagesDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private OffersDao offersDao;
	
	@Autowired
	private MessagesDao messagesDao;

	private User user1 = new User("pabloperez", "Pablo Perez", "hola", true,
			"ROLE_USER", "pabloperez@gmail.com");
	private User user2 = new User("josefinaperez", "Josefina Perez", "hola",
			true, "ROLE_USER", "josefinaperez@gmail.com");
	private User user3 = new User("carolinajimenez", "Carolina Jimenez", "bye",
			true, "ROLE_ADMIN", "carojimenez@hotmail.com");
	private User user4 = new User("manueljimenez", "Manuel Jimenez", "bye",
			false, "ROLE_ADMIN", "manueljimenez@hotmail.com");


	@Before
	public void init() {

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSave() {
	    createUsers();   
		Message message1 = new Message("Test subject 1", "Test Content 1", "Peter Petrelli", "peterpetrelli@yahoo.com", user2.getUsername());
		messagesDao.saveOrUpdate(message1);
		
	}

	public void createUsers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}
	
}
