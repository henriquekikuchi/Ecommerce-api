package br.com.kikuchi.henrique.ecommerceapi.filter;

import br.com.kikuchi.henrique.ecommerceapi.service.UserServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.kikuchi.henrique.ecommerceapi.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserServiceImpl userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);

        if (token != null){
            final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET.getBytes(StandardCharsets.UTF_8));
            final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
            final Claims claims = claimsJws.getBody();

            String username = jwtParser.parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody().getSubject();

            if (username != null) {

                UserDetails user = userService.loadUserByUsername(username);

                final Collection<? extends GrantedAuthority> authorities =
                        ((List<String>) claims.get(AUTHORITIES_KEY)).stream()
                                .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                                .collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
            return null;
        }
        return null;
    }
}
