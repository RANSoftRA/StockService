package service.persistence.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@GeneratedValue
	@Id
	private long idUser;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;

	@OneToMany(mappedBy="user")
	private Set<UserSession> userSessions;
	
	@OneToMany(mappedBy="user")
	private Set<Transaction> transactions;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getIdUser() {
		return idUser;
	}

	public Set<UserSession> getUserSessions() {
		return userSessions;
	}

	public void setUserSessions(Set<UserSession> userSessions) {
		this.userSessions = userSessions;
	}
}
