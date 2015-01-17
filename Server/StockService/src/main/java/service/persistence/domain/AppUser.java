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
public class AppUser {

	@GeneratedValue
	@Id
	private long idAppUser;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private double balance;
	
	@OneToMany(mappedBy="appUser")
	private Set<StockTransaction> transactions;
	
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

	public long getIdAppUser() {
		return idAppUser;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<StockTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<StockTransaction> transactions) {
		this.transactions = transactions;
	}
	
	
}
