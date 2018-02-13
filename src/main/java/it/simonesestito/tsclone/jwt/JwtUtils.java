package it.simonesestito.tsclone.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    public static String getToken(String username, long expire, byte[] secret){
        Date expDate = new Date(expire);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
