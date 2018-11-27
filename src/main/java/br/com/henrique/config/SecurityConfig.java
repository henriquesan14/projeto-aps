package br.com.henrique.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception{
        http.
                authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/medicos/**").hasAnyRole("ADMIN")
                .antMatchers("/especialidades/**").hasAnyRole("ADMIN")
                .antMatchers("/medicamentos/**").hasAnyRole("ADMIN")
                .antMatchers("/pacientes/**").hasAnyRole("ADMIN")
                .antMatchers("/consultas/{idConsulta}/receitas/nova").hasAnyRole("MEDICO")
                .antMatchers("/consultas/{idConsulta}/receitas/salvar").hasAnyRole("MEDICO")
                .antMatchers("/consultas/{idConsulta}/receitas//{idReceita}/remover/{idMedicamento}").hasAnyRole("MEDICO")
                .antMatchers("/consultas/{idConsulta}/receitas//{idReceita}/incluir").hasAnyRole("MEDICO")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("henrique").password("123456").roles("MEDICO");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
