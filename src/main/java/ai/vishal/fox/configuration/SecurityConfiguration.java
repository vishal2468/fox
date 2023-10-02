package ai.vishal.fox.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    // @Autowired
    // UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsService);
    // }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //             .antMatchers("/admin").hasRole("ADMIN")
    //             .antMatchers("/user").hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/sendmessage").hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/sendmessage/*/*").hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/webhooks").permitAll()
    //             .antMatchers("/customer").hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/customer/*").hasAnyRole("USER", "ADMIN")
    //             .antMatchers("/").permitAll()
    //             .and().httpBasic().and().csrf().disable();
    // }
    // @Bean
    // public PasswordEncoder getPasswordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }
}
