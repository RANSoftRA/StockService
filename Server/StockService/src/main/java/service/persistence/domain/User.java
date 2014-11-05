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
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private double balance;

	@OneToMany(mappedBy="user")
	private Set<UserSession> userSessions;
	
	@OneToMany(mappedBy="user")
	private Set<Transaction> transactions;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
