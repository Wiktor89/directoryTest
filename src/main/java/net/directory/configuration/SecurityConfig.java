package net.directory.configuration;

import net.directory.service.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//@Configuration extends WebSecurityConfigurerAdapter
//@EnableWebSecurity
public class SecurityConfig
        {

//    @Autowired
//    @Qualifier("userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

//    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests ().antMatchers ("/admin/**")
                .access ("hasRole('ROLE_ADMIN')").and ().formLogin ()
                .loginPage ("/login").failureUrl ("/login?error").usernameParameter ("username")
                .usernameParameter ("password").and ().logout ().logoutSuccessUrl ("/logout.jsp")
                .and ().csrf ().and ().exceptionHandling ().accessDeniedPage ("/error403.jsp");

    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService (userDetailsService).passwordEncoder (passwordEncoder ());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder ();
//    }
}
