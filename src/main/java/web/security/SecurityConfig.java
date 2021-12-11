package web.security;

import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import web.security.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler successHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //стандартная форма логина доступна всем и обрабатывается следующим образом
        http.formLogin().successHandler(successHandler).permitAll();
        //выключаем кроссдоменную безопасность (на этапе обучения неважна)
        http.csrf().disable();
        //ссылка для выхода из учётной записи и страца, на которую попадёт пользователь после
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
        //страницы аутентификаци доступна всем
        http.authorizeRequests().antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user/**").access("hasAnyRole('ADMIN', 'USER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')").anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
