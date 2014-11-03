package service.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Transaction {

	@Id
	@GeneratedValue
	private long idTransaction;
	
	@Column(nullable=false)
	private String stock;
	
	@Column(nullable=false)
	private Date date;
	
	@Column(nullable=false)
	private double price;
	
	@Column(nullable=false)
	private TransactionType transactionType;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable=false)
	private User user;
	
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getIdTransaction() {
		return idTransaction;
	}

	public enum TransactionType {
		BUY, SELL;
	}
}
