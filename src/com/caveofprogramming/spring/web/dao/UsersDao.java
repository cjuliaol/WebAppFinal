package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {
	
    //JdbcTemplate and NamedParameterJdbcTemplate
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*public UsersDao (){
		System.out.println("Sucessfully bean loaded!");
	}
	*/
	/* @Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}*/

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	//public boolean create(User user) {
	public void create(User user) {	
		// Mapping parameters 
		
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername() );
		params.addValue("password", passwordEncoder.encode(user.getPassword())  );
		params.addValue("email", user.getEmail() );
		params.addValue("name", user.getName());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());
	
		return jdbc.update("insert into users (username,name,password,email,enabled,authority) values (:username,:name, :password, :email, :enabled,:authority)", params) == 1;
		*/
		
		// Hibernate saving
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
		
		
	}

	public boolean exists(String username) {
		// Hibernate way
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult(); // Execute the query with the criteria indicated; In this case is uniqueResult cuz you're using a primary key
	
		return user != null;
		// TODO Auto-generated method stub
		//return jdbc.queryForObject("select count(*) from users where username = :username", 
			//	new MapSqlParameterSource("username",username), Integer.class) >0;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		// Hibernate query
		// The query is HQL and refers to the class User not the users table
		return  session().createQuery("from User").list();
				//return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}

	
}
