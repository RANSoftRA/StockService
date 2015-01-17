package service.servicelayer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import service.data.PortfolioStock;
import service.data.Stock;
import service.data.TransactionResponse;
import service.persistence.AppUserDao;
import service.persistence.TransactionDao;
import service.persistence.domain.AppUser;
import service.persistence.domain.StockTransaction;
import service.servicelayer.AppUserService;
import service.servicelayer.YQLService;

@Service
public class AppUserServiceImpl implements AppUserService {

	@Autowired
	private AppUserDao appUserDao;

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private YQLService yql;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		AppUser appUser = appUserDao.getUserByName(username);
		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_LOGGEDIN"));
		return new User(appUser.getUsername(), appUser.getPassword(),
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser getAuthenticatedUser() {
		return appUserDao.getUserByName(SecurityContextHolder.getContext()
				.getAuthentication().getName());
	}

	@Override
	@Transactional(readOnly = true)
	public TransactionResponse getAuthenticatedUserTransactions() {

		List<StockTransaction> transactions = transactionDao
				.getTransactions(getAuthenticatedUser());

		List<String> stocksInPortfolio = new ArrayList<String>();

		Map<String, Integer> stockSum = new HashMap<>();

		// Get all the Stocks of the User
		for (StockTransaction transaction : transactions) {
			if (!stocksInPortfolio.contains(transaction.getStock())) {
				stocksInPortfolio.add(transaction.getStock());
			}
		}

		// Get the amounts of the Stocks
		for (String stockInPortfolio : stocksInPortfolio) {
			stockSum.put(stockInPortfolio, 0);
			for (StockTransaction transaction : transactions) {
				if (stockInPortfolio.equals(transaction.getStock())) {
					int before = stockSum.get(stockInPortfolio);
					stockSum.remove(stockInPortfolio);
					stockSum.put(stockInPortfolio, transaction.getAmount()
							+ before);
				}
			}
		}

		List<PortfolioStock> portfolioStocks = new ArrayList<PortfolioStock>();

		for (Map.Entry<String, Integer> entry : stockSum.entrySet()) {

			Stock stock = yql.getStock(entry.getKey());
			
			
			PortfolioStock portfolioStock = new PortfolioStock(entry.getKey(),
					stock != null ? stock.getName() : "", entry.getValue(),
					entry.getValue() * yql.getCurrentPrice(entry.getKey()));
			
			portfolioStocks.add(portfolioStock);

		}

		return new TransactionResponse(transactions, portfolioStocks);
	}

	@Override
	@Transactional(readOnly = false)
	public TransactionResponse setUserPassword(String password) {
		
		AppUser appUser = getAuthenticatedUser();
		appUser.setPassword(encoder.encode(password));
		
		return getAuthenticatedUserTransactions();
	}
}
