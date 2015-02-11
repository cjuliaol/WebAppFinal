package com.caveofprogramming.spring.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="offers")
public class Offer {

	public Offer() {
		this.user = new User();
	}
    
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Offer(int id, User user, String text) {

		this.id = id;
		this.user = user;
		this.text = text;
	}

	public Offer(User user, String text) {

		this.user = user;
		this.text = text;
	}

	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;

	@NotNull()
	@Size(min = 20, max = 255, message = "Text must be between 20 and 255 char",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Column(name="text")
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [user=" + user + ", id=" + id + ", text=" + text + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public String getUsername() {
		return user.getUsername();
	}
}
