package pl.rodzyn.szaweek01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebServiceConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User userAdmin = new User("Admin",
                getPasswordEncoder().encode("Admin123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        User userUser = new User("User",
                getPasswordEncoder().encode("User123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

//        User userVisitor = new User("Visitor",
//                getPasswordEncoder().encode("Visitor123"),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_VISITOR")));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
//        auth.inMemoryAuthentication().withUser(userVisitor);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getAdmin").hasRole("ADMIN")
                .antMatchers("/getUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/getVisitor").anonymous()
                .antMatchers("/getVisitor").permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/getAll");
    }


}
