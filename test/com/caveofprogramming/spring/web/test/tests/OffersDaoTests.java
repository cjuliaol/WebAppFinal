package com.caveofprogramming.spring.web.test.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class OffersDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private OffersDao offersDao;

	private User user1 = new User("pabloperez", "Pablo Perez", "hola", true,
			"ROLE_USER", "pabloperez@gmail.com");
	private User user2 = new User("josefinaperez", "Josefina Perez", "hola",
			true, "ROLE_USER", "josefinaperez@gmail.com");
	private User user3 = new User("carolinajimenez", "Carolina Jimenez", "bye",
			true, "ROLE_ADMIN", "carojimenez@hotmail.com");
	private User user4 = new User("manueljimenez", "Manuel Jimenez", "bye",
			false, "ROLE_ADMIN", "manueljimenez@hotmail.com");

	private Offer offer1 = new Offer(user1, "Accouting services 2000");
	private Offer offer2 = new Offer(user1, "Mechanical services 2000");
	private Offer offer3 = new Offer(user2, "Cleaning services 2000");
	private Offer offer4 = new Offer(user3, "Accouting services 2000");
	private Offer offer5 = new Offer(user3, "Audit and controlling services ");
	private Offer offer6 = new Offer(user3, "Electrical services 2000");
	private Offer offer7 = new Offer(user4, "Whatever services 2000");

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
		assertEquals("Dummy test baby", 1, 1);
	}

	@Test
	public void test4() {// testCreateOffer() {

		createUsers();
		createOffers();

		List<Offer> offersDao1 = offersDao.getOffers();
		assertEquals("It must be six offers from enabled users", 6,
				offersDao1.size());

	}

	@Test
	public void testGetByUsername() {

		createUsers();
		createOffers();

		List<Offer> offer1 = offersDao.getOffers(user3.getUsername());
		assertEquals("This user must have 3 offers", 3, offer1.size());

		List<Offer> offer2 = offersDao.getOffers("zzz");
		assertEquals("This user must have zero offers", 0, offer2.size());

		List<Offer> offer3 = offersDao.getOffers(user2.getUsername());
		assertEquals("This user must have one offer", 1, offer3.size());

	}

	@Test
	public void testGetOfferById() {

		createUsers();
		createOffers();

		Offer retrieved1 = offersDao.getOffer(offer3.getId());
		assertEquals("Offer retrieved must be the same as offer3", offer3,
				retrieved1);
		System.out.println(retrieved1.getText());

		Offer retrieved2 = offersDao.getOffer(offer7.getId());
		assertNull("Offer retrieved is null because user is disabled ", retrieved2);
	}

	@Test
	public void testSaveOffer() {

		createUsers();
		createOffers();

		offer1.setText("Servicios de contabilidad y auditoria financiera");
		offersDao.saveOrUpdate(offer1);

		Offer retrieved1 = offersDao.getOffer(offer1.getId());
		assertEquals("Offer retrieved must be the same as offer1", offer1,
				retrieved1);
		System.out.println(retrieved1.getText());

	}
	
	@Test
	public void testDelete() {
		createUsers();
		createOffers();
		
		Offer retrieved1 = offersDao.getOffer(offer6.getId());			   
		assertNotNull("Offer " + offer6.getId() + " should be not null", retrieved1);
    
		
		boolean deleted = offersDao.delete(offer6.getId());
		assertTrue("Deleted action must be true",deleted);
		
		Offer retrieved2 = offersDao.getOffer(offer6.getId());
		assertNull("Offer " + offer6.getId() + " should be null (deleted)", retrieved2);

		
	}

	public void createUsers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
	}

	public void createOffers() {
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

	}

}
