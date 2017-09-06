package net.directory.service;

import net.directory.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);
	private UserService userService;
	
	@Autowired
	@Qualifier("userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userService.getUser(login);
		Set<GrantedAuthority> roles = new HashSet();
		roles.add(new SimpleGrantedAuthority(user.getRole().getTitle()));
		LOGGER.info("Users role "+user);
		UserDetails userDetails =
				new org.springframework.security.core.userdetails.User(user.getLogin().trim(),
						user.getPassword().trim(),
						roles);
		
		return userDetails;
	}

}
