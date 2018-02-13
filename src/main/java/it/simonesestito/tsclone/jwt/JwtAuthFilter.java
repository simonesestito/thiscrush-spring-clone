package it.simonesestito.tsclone.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends BasicAuthenticationFilter {

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.signing-key}")
    private String signingKey;

    public JwtAuthFilter(AuthenticationManager authManager){
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        boolean execFinally = true;
        try {
            String token = request.getHeader(header);
            Authentication auth = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (ExpiredJwtException e) {
            declareExpired(response);
            execFinally = false;
        } catch (RuntimeException e) {
            //e.printStackTrace();
        } finally {
            if (execFinally)
                chain.doFilter(request, response);
        }
    }

    private Authentication getAuthentication(String token) {
        String user = Jwts.parser()
                .setSigningKey(signingKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        return null;
    }

    private void declareExpired(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        JSONObject json = new JSONObject();
        json.put("error", "jwt_expired");
        json.put("message", "Token expired. Please log in again");
        response.getOutputStream().println(json.toString());
    }
}
