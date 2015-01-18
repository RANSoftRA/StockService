package service.persistence.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("appUser")
public class StockTransaction {

	@Id
	@GeneratedValue
	private long idTransaction;
	
	@Column(nullable=false)
	private String stock;
	
	@Column(nullable=false)
	private int amount;
	
	@Column(nullable=false)
	private Date date;
	
	@Column(nullable=false)
	private double price;
	
	@Column(nullable=false)
	private TransactionType transactionType;
	
	@ManyToOne
	@JoinColumn(name="idAppUser", nullable=false)
	private AppUser appUser;	
	
	public StockTransaction() {
		
	}
	
	public StockTransaction(String stock, int amount, Date date, double price,
			TransactionType transactionType, AppUser user) {
		super();
		this.stock = stock;
		this.amount = amount;
		this.date = date;
		this.price = price;
		this.transactionType = transactionType;
		this.appUser = user;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public long getIdTransaction() {
		return idTransaction;
	}

	public enum TransactionType {
		BUY, SELL;
	}
}
