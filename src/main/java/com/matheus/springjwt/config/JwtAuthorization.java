package com.matheus.springjwt.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;

public class JwtAuthorization extends BasicAuthenticationFilter  {
    public static final String HEADER_ATRIBUTO = "Authorization";
    public static final String ATRIBUTO_PREFIXO = "Bearer ";
    public static final String TOKEN_SENHA = "463408a1-54c9-4307-bb1c-6cced559f5a7";

    public JwtAuthorization(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String atributo = request.getHeader(HEADER_ATRIBUTO);

        if (atributo == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!atributo.startsWith(ATRIBUTO_PREFIXO)) {
            chain.doFilter(request, response);
            return;
        }

        String token = atributo.replace(ATRIBUTO_PREFIXO, "");
        UsernamePasswordAuthenticationToken authenticationToken;

        try {
            authenticationToken = getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            responseJson("{ \"error:\" \"Expired token\" }", 401, response);
        } catch (JWTDecodeException e) {
            responseJson("{ \"error:\" \"Invalid token\" }", 400, response);
        }
    }

    private void responseJson(String msg, int sc, HttpServletResponse response) throws IOException {
        response.setStatus(sc);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(msg);
        response.getWriter().flush();
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) throws TokenExpiredException, JWTDecodeException {

        try {
            String usuario = JWT.require(Algorithm.HMAC512(TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();

            if (usuario == null) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(usuario,null, new ArrayList<>());
        } catch(TokenExpiredException ex) {
            throw new TokenExpiredException(ex.getMessage());
        } catch(JWTDecodeException ex) {
            throw new JWTDecodeException(ex.getMessage());
        }

    }
}
