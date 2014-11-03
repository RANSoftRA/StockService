package service.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class UserSession {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String uuid;
	
	@Column(nullable=false)
	private Date expires;
	
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;


	public Date getExpires() {
		return expires;
	}


	public void setExpires(Date expires) {
		this.expires = expires;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getUuid() {
		return uuid;
	}
}
