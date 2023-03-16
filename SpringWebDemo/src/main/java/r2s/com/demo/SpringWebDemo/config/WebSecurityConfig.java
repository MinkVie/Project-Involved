package r2s.com.demo.SpringWebDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests()

                .antMatchers("/register").permitAll()

                .antMatchers("/authenticate").permitAll()

                .antMatchers(HttpMethod.GET,"/user/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/user/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/address/**").permitAll()
                .antMatchers(HttpMethod.POST,"/address/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/address/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/address/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/cart/**").permitAll()
                .antMatchers(HttpMethod.POST,"/cart/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/cart/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/cart/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/product/**").permitAll()
                .antMatchers(HttpMethod.POST,"/product/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/product/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/product/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/order/**").permitAll()
                .antMatchers(HttpMethod.POST,"/order/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/order/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/order/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET,"/category/**").permitAll()
                .antMatchers(HttpMethod.POST,"/category/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/category/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/category/**").hasAuthority("ADMIN")


                .antMatchers("/role/**").hasAuthority("ADMIN")
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // httpSecurity.csrf().disable().authorizeRequests().antMatchers("/user").access("hasRole('USER')");

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}