package br.com.kikuchi.henrique.ecommerceapi.security;

import br.com.kikuchi.henrique.ecommerceapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserPermission.*;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserServiceImpl userService){
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/*/users/**").hasAuthority(USER_READ.getPermissao())
//                .antMatchers(HttpMethod.POST, "/api/*/users/**").hasAuthority(USER_WRITE.getPermissao())
//                .antMatchers(HttpMethod.PUT, "/api/*/users/**").hasAuthority(USER_WRITE.getPermissao())
//                .antMatchers(HttpMethod.DELETE, "/api/*/users/**").hasAuthority(USER_WRITE.getPermissao())
//                .antMatchers(HttpMethod.GET, "/api/*/categorias/**").hasAuthority(CATEGORIA_READ.getPermissao())
//                .antMatchers(HttpMethod.POST, "/api/*/categorias/**").hasAuthority(CATEGORIA_WRITE.getPermissao())
//                .antMatchers(HttpMethod.PUT, "/api/*/categorias/**").hasAuthority(CATEGORIA_WRITE.getPermissao())
//                .antMatchers(HttpMethod.DELETE, "/api/*/categorias/**").hasAuthority(CATEGORIA_WRITE.getPermissao())
//                .antMatchers(HttpMethod.GET, "/api/*/fornecedores/**").hasAuthority(FORNECEDOR_READ.getPermissao())
//                .antMatchers(HttpMethod.POST, "/api/*/fornecedores/**").hasAuthority(FORNECEDOR_WRITE.getPermissao())
//                .antMatchers(HttpMethod.PUT, "/api/*/fornecedores/**").hasAuthority(FORNECEDOR_WRITE.getPermissao())
//                .antMatchers(HttpMethod.DELETE, "/api/*/fornecedores/**").hasAuthority(FORNECEDOR_WRITE.getPermissao())
//                .antMatchers(HttpMethod.GET, "/api/*/produtos/**").hasAuthority(PRODUTO_READ.getPermissao())
//                .antMatchers(HttpMethod.POST, "/api/*/produtos/**").hasAuthority(PRODUTO_WRITE.getPermissao())
//                .antMatchers(HttpMethod.PUT, "/api/*/produtos/**").hasAuthority(PRODUTO_WRITE.getPermissao())
//                .antMatchers(HttpMethod.DELETE, "/api/*/produtos/**").hasAuthority(PRODUTO_WRITE.getPermissao())
//                .antMatchers(HttpMethod.GET, "/api/*/carrinho-de-compra/**").hasAuthority(PEDIDO_READ.getPermissao())
//                .antMatchers(HttpMethod.POST, "/api/*/carrinho-de-compra/**").hasAuthority(PEDIDO_WRITE.getPermissao())
//                .antMatchers(HttpMethod.PUT, "/api/*/carrinho-de-compra/**").hasAuthority(PEDIDO_WRITE.getPermissao())
//                .antMatchers(HttpMethod.DELETE, "/api/*/carrinho-de-compra/**").hasAuthority(PEDIDO_WRITE.getPermissao())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
