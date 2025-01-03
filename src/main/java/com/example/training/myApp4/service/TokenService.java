package com.example.training.myApp4.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.training.myApp4.entity.User;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${spring.datasource.password}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(User user){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,60);
        Date expiresAt = calendar.getTime();

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId().toString())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public DecodedJWT verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);
        }catch (Exception e){
            return null;
        }

    }

    private Algorithm algorithmx(){
        return Algorithm.HMAC256(secret);
    }

}
