package kh.com.kshrd.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kh.com.kshrd.entities.User;
import kh.com.kshrd.services.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			User user = userService.findUserByEmail(username);
			if(user==null){
				throw new UsernameNotFoundException("User Not Found.");
			}
			return user;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
