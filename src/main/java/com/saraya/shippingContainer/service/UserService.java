package com.saraya.shippingContainer.service;

import java.time.LocalDateTime; 
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.saraya.shippingContainer.model.User;
import com.saraya.shippingContainer.model.UserRole;
import com.saraya.shippingContainer.repository.UserRepository;
import com.saraya.shippingContainer.token.ConfirmationToken;
import com.saraya.shippingContainer.token.ConfirmationTokenService;

@Service("userService")
public class UserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
	@Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	

	
	  
	  
	  
	  public String signUpUser(User user) {
	        boolean userExists = userRepository
	                .findByEmail(user.getEmail())
	                .isPresent();

	        if (userExists) {
	            

	            throw new IllegalStateException("email already taken");
	        }

		        String encodedPassword = bCryptPasswordEncoder
		                .encode(user.getPassword());
		        
		        
	
		        user.setPassword(encodedPassword);
		        user.setUserRole(UserRole.USERS);

	            userRepository.save(user);
	        
	            String token = UUID.randomUUID().toString();

	            ConfirmationToken confirmationToken = new ConfirmationToken(
	                    token,
	                    LocalDateTime.now(),
	                    LocalDateTime.now().plusMinutes(15),
	                    user);
	            
	            confirmationTokenService.saveConfirmationToken(confirmationToken);

	            return token;
	            
	        
	  }




	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
	
	}
	
	public int enableUser(String email) { 
        return userRepository.enableUser(email);
    }
	
}
