package TA_B_SYN_65.rumahSehat.security;

import TA_B_SYN_65.rumahSehat.security.jwt.JwtAuthEntryPoint;
import TA_B_SYN_65.rumahSehat.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @Order(1)
    public class RestApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.csrf().disable()
                    .antMatcher("/api/**").cors().and()
                    .authorizeRequests().antMatchers("/api/mobile/signin").permitAll()
                    .antMatchers("/api/mobile/profile/pasien").permitAll()
                    .antMatchers("/api/mobile/signup").permitAll()
                    .antMatchers("/api/mobile/signupAdmin").permitAll()
                    .antMatchers("/api/mobile/signupPasien").permitAll()
                    .antMatchers("/api/mobile/appointment/create").permitAll()
                    .antMatchers("/api/v1/list-tagihan").permitAll()
                    .antMatchers("/api/v1/tagihan/pay").permitAll()
                    .antMatchers("/api/mobile/**").hasAuthority("PASIEN").and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(2)
    public class UILoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable().authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login-sso", "/validate-ticket").permitAll()
                    .antMatchers("/").hasAnyAuthority("ADMIN", "APOTEKER","DOKTER")
                    .antMatchers("/obat/ubahStok/**").hasAuthority("APOTEKER")
                    .antMatchers("/obat/barChartObat").hasAuthority("ADMIN")
                    .antMatchers("/user/manajemenUser").hasAuthority("ADMIN")
                    .antMatchers("/user/view/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll().and()
                    .sessionManagement().sessionFixation().newSession().maximumSessions(1);
        }
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}
