//package com.matheus.springjwt.config;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//@Component
//public class JwtAuthorization extends OncePerRequestFilter {
//    public static final String PREFIX = "Bearer ";
//
//    @Value("${jwt.token-password}")
//    private String TOKEN_PASS;
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//
//        String TOKEN = request.getHeader("Authorization");
//
//        if (TOKEN == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if (!TOKEN.startsWith(PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String token = TOKEN.replace(PREFIX, "");
//        UsernamePasswordAuthenticationToken authenticationToken;
//
//        try {
//            authenticationToken = getAuthenticationToken(token);
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            chain.doFilter(request, response);
//        } catch (TokenExpiredException e) {
//            responseJson(mapper.writeValueAsString(new errorMessage("Expired token")), 401, response);
//        } catch (JWTDecodeException e) {
//            responseJson(mapper.writeValueAsString(new errorMessage("Invalid token")), 400, response);
//        }
//    }
//
//    private void responseJson(String msg, int sc, HttpServletResponse response) throws IOException {
//        response.setStatus(sc);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().print(msg);
//        response.getWriter().flush();
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) throws TokenExpiredException, JWTDecodeException {
//
//        try {
//            String user = JWT.require(Algorithm.HMAC512(TOKEN_PASS))
//                .build()
//                .verify(token)
//                .getSubject();
//
//            if (user == null) {
//                return null;
//            }
//
//            return new UsernamePasswordAuthenticationToken(user,null, new ArrayList<>());
//        } catch(TokenExpiredException ex) {
//            throw new TokenExpiredException(ex.getMessage());
//        } catch(JWTDecodeException ex) {
//            throw new JWTDecodeException(ex.getMessage());
//        }
//    }
//
//    record errorMessage(String error) {}
//}
