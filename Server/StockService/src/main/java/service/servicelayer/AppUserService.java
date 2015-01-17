package service.servicelayer;

import org.springframework.security.core.userdetails.UserDetailsService;

import service.data.TransactionResponse;
import service.persistence.domain.AppUser;

public interface AppUserService extends UserDetailsService {
	
	AppUser getAuthenticatedUser();
	
	TransactionResponse getAuthenticatedUserTransactions();
	
	TransactionResponse setUserPassword(String password);
	
}
