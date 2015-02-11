package com.caveofprogramming.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

import com.caveofprogramming.spring.web.dao.Offer;
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
public class UsersDaoTests {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private OffersDao offersDao;
	
	
	private User user1 = new User("pabloperez","Pablo Perez","hola",true,"ROLE_USER","pabloperez@gmail.com");
	private User user2 = new User("josefinaperez","Josefina Perez","hola",true,"ROLE_USER","josefinaperez@gmail.com");
	private User user3 = new User("carolinajimenez","Carolina Jimenez","bye",true,"ROLE_ADMIN","carojimenez@hotmail.com");

	
	@Before
	public void init() {
		
	
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
    
	@Test
	public void test1() { 
		// testDummy() {
		assertEquals("Dummy test baby",1,1);
	}
	

	@Test
	public void test2() { //testCreateUser() {
		
		usersDao.create(user1);
		
		List<User> users = usersDao.getAllUsers();
		assertEquals("Number of users must be 1 ", 1, users.size());
		
		usersDao.create(user2);		
		usersDao.create(user3);
		
		List<User> usert = usersDao.getAllUsers();
		assertEquals("Number of users must be 3 ", 3, usert.size());
		
		assertEquals("User2 must the same retrieved", user2, usert.get(1) );
		
	}
	
	@Test
	public void test3() { //testExists() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User3 must exists",usersDao.exists(user3.getUsername()) );
		assertFalse("User xxx must not exists",usersDao.exists("xxx"));		
	}
	

	

}
