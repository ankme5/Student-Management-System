package com.techeazy.backend.Filters;

import com.techeazy.backend.Entity.CustomResponse;
import com.techeazy.backend.utils.JWTHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);

            try{
                username = jwtHelper.extractUsername(token);
            }catch (IllegalArgumentException e) {
                logger.info("illegalArgument exception");
            } catch (MalformedJwtException e) {
                logger.info("some changes are done in JWT. INVALID TOKEN !!");
            }
            catch (ExpiredJwtException e){
                logger.info("JWT TOKEN EXPIRE !!");
            }catch (Exception e){
                logger.info("AuthenticationFilter Default exception");
            }
        }else{
            logger.info("Invalid Authorization Header");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);
            if(jwtHelper.validateToken(token,userDetails.getUsername())){
                UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }

        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path= request.getRequestURI();
        return path.startsWith("/auth/v1/");
    }
}
