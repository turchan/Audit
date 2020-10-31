package com.dc.task.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@PropertySource("classpath:application.properties")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${user.user_name}")
    lateinit var user_name: String
    @Value("\${user.user_pass}")
    lateinit var user_pass: String

    @Value("\${user.user_role}")
    lateinit var user_role: String

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/transaction/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser(user_name)
                .password(user_pass)
                .roles(user_role)
    }
}
