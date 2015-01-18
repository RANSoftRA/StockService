package service.servicelayer;

import org.springframework.security.core.userdetails.UserDetailsService;

import service.data.PortfolioResponse;
import service.persistence.domain.AppUser;

public interface AppUserService extends UserDetailsService {
	
	AppUser getAuthenticatedUser();
	
	PortfolioResponse getAuthenticatedUserTransactions();
	
	PortfolioResponse setUserPassword(String password);
	
	void registerUser(String username, String password);
	
}
