package com.caveofprogramming.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offersDao")
public class OffersDao {

	// JdbcTemplate and NamedParameterJdbcTemplate

	@Autowired
	private SessionFactory sessionFactory;

	//private NamedParameterJdbcTemplate jdbc;

	public OffersDao() {
		System.out.println("Sucessfully bean loaded!");
	}

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	/*@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}*/

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {

		Criteria criteria = session().createCriteria(Offer.class);
		criteria.createAlias("user", "usr");

		criteria.add(Restrictions.eq("usr.enabled", true));
		criteria.add(Restrictions.eq("usr.username", username));

		return criteria.list();
		/*
		 * return
		 * 
		 * jdbc.query(
		 * "select * from offers,users where offers.username= users.username and users.username=:username"
		 * , new MapSqlParameterSource("username", username), new
		 * OfferRowMapper());
		 */

	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {

		Criteria crit = session().createCriteria(Offer.class);

		crit.createAlias("user", "usr").add(
				Restrictions.eq("usr.enabled", true));

		return crit.list();

		/*
		 * return jdbc .query(
		 * "select * from offers,users where offers.username= users.username ",
		 * new OfferRowMapper());
		 */

	}

	public Offer getOffer(int id) {

		Criteria criteria = session().createCriteria(Offer.class);
		
		criteria.createAlias("user", "usr");

		criteria.add(Restrictions.eq("usr.enabled", true));
		criteria.add(Restrictions.idEq(id));

		return (Offer) criteria.uniqueResult();
		/*
		 * MapSqlParameterSource params = new MapSqlParameterSource();
		 * params.addValue("id", id);
		 * 
		 * return jdbc .queryForObject(
		 * "select * from offers, users where offers.id= :id and  offers.username= users.username and users.enabled=true"
		 * , params, new OfferRowMapper());
		 */

	}

	/*
	 * public int[] create(List<Offer> offers) {
	 * 
	 * SqlParameterSource[] params = SqlParameterSourceUtils
	 * .createBatch(offers.toArray());
	 * 
	 * return jdbc.batchUpdate(
	 * "insert into offers (username,text) values (:username, :text)", params);
	 * }
	 */

	public void saveOrUpdate(Offer offer) {
		session().saveOrUpdate(offer);
	}

	public void update(Offer offer) {

		session().update(offer);
		/*
		 * BeanPropertySqlParameterSource params = new
		 * BeanPropertySqlParameterSource( offer); return
		 * jdbc.update("update offers set text= :text where id = :id", params)
		 * == 1;
		 */
	}

	public void create(Offer offer) {

		session().save(offer);
		// Mapping parameters

		// BeanPropertySqlParameterSource params = new
		// BeanPropertySqlParameterSource(offer);

		// return
		// jdbc.update("insert into offers (username,text) values (:username, :text)",
		// params) == 1;

	}

	public boolean delete(int id) {

		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		
        // executeUpdate returns numbers of rows affected . In this case one
		return query.executeUpdate() == 1;

		//MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		//return jdbc.update("delete from offers where id = :id", params) == 1;

	}
}
