package net.directory.service;

import net.directory.models.User;
import net.directory.models.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		logger.info("Login user "+ login);
		User user = userService.getUser(login);
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(Role.USER.name()));
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getLogin(),
				user.getPassword(),
				roles);
		return userDetails;
	}
}
