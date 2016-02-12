package kh.com.kshrd.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomSuccessConfiguration customSuccessConfiguration; 
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/users/signup**").permitAll()
                .antMatchers("/donates").hasAnyRole("DONOR","ADMIN", "BILLER")
                .antMatchers("/administrator/**").hasAnyRole("ADMIN", "BILLER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.usernameParameter("email")
            	.passwordParameter("password")
                .loginPage("/login")
                .permitAll()
                .successHandler(customSuccessConfiguration)
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/administrator/logout"))
            	.logoutSuccessUrl("/login")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("DONOR")
                .and()
        		.withUser("admin").password("password").roles("ADMIN");
        */
    	auth
    		.userDetailsService(this.userDetailsService)
    		.passwordEncoder(this.passwordEncoder());
    	
    	//auth.authenticationProvider();
    	//auth.userDetailsService(userDetailsService);
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/static/**");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String args[]){
		System.out.println(new BCryptPasswordEncoder().encode("password"));
	}
}
