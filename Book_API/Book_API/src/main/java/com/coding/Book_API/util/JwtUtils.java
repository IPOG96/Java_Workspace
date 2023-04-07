package com.coding.Book_API.util;


import com.coding.Book_API.common.AccessDeniedException;
import com.coding.Book_API.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    public static String secret = "This_is_secret";
    public static long expiryDuration = 60 * 60;

    public String generateJwt(Users users) {

        long millisecond = System.currentTimeMillis();
        long expiryTime = millisecond + expiryDuration * 1000;

        Date issuedAt = new Date(millisecond);
        Date expiredAt = new Date(expiryTime);

        //create claims AKA payload
        Claims claims = Jwts.claims()
                .setIssuer(users.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt);


        claims.put("type", users.getUserType());
        claims.put("name", users.getName());
        claims.put("emailId", users.getEmailId());


        //generate jwt using claims
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();


    }


    public Claims verify(String authorization) throws Exception {

        try {
         //   Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);


           Claims claims= Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            System.out.println("claims" +claims);
            return  claims;

        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }


    }


}
