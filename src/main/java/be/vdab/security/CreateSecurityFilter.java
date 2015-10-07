package be.vdab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

// enkele imports
@Configuration
@EnableWebSecurity
public class CreateSecurityFilter extends WebSecurityConfigurerAdapter {
    private static final String MANAGER = "manager";
    private static final String HELPDESKMEDEWERKER = "helpdeskmedewerker";
    private static final String MAGAZIJNIER = "magazijnier";
    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/images/**")
                .antMatchers("/styles/**")
                .antMatchers("/scripts/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/")
                .and().authorizeRequests()
                .antMatchers("/filialen/toevoegen", "/filialen/*/wijzigen",
                        "/filialen/*/verwijderen").hasAuthority(MANAGER)
                .antMatchers(HttpMethod.POST, "/filialen").hasAuthority(MANAGER)
                .antMatchers("/werknemers")
                .hasAnyAuthority(MAGAZIJNIER, HELPDESKMEDEWERKER)
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/**").authenticated()
                .and().exceptionHandling().accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");

    }
}