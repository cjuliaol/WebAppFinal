package com.caveofprogramming.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

	
	@Autowired
	private SessionFactory sessionFactory;


	public Session session() {
		return sessionFactory.getCurrentSession();
	}


	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {

		Criteria criteria = session().createCriteria(Message.class);

		criteria.add(Restrictions.eq("username", username));

		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {

		Criteria crit = session().createCriteria(Message.class);

		return crit.list();

	}

	public Message getMessage(int id) {

		Criteria criteria = session().createCriteria(Message.class);		
		criteria.add(Restrictions.idEq(id));

		return (Message) criteria.uniqueResult();

	}


	public void saveOrUpdate(Message Message) {
		session().saveOrUpdate(Message);
	}

	public void update(Message Message) {

		session().update(Message);
	}

	public void create(Message Message) {

		session().save(Message);
	}

	public boolean delete(int id) {

		Query query = session().createQuery("delete from Message where id=:id");
		query.setLong("id", id);
		
        // executeUpdate returns numbers of rows affected . In this case one
		return query.executeUpdate() == 1;

	}
}
