package com.unicolombo.bibliotecApi.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unicolombo.bibliotecApi.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${api.security.secret}")
    private String SECRET_KEY;

    @Value("${api.security.expirationMs}")
    private long expirationMs;

    private Algorithm algorithm(){
        return Algorithm.HMAC256(SECRET_KEY);
    }


    public String generarToken(Usuario user){
        var expiracion = generarFechaExpiracion();
        try{
            return JWT.create()
                    .withIssuer("trust hotel api")
                    .withSubject(user.getUsername())
                    .withClaim("user", user.getNombre())
                    .withClaim("role", user.getTipo().name())
                    .withExpiresAt(expiracion)
                    .sign(algorithm());
        } catch (JWTCreationException exception){
            // invalid signing configuration // Couldn't convert claim
            throw new RuntimeException(exception.getMessage());
        }
    }

    public String extraerNombreUsuario(String token){
        return decode(token).getSubject();
    }

    public boolean validarToken(String token){
        try {
            JWT.require(algorithm()).build().verify(token);
            return true;
        } catch (JWTVerificationException exp){
            return false;
        }
    }
    private DecodedJWT decode(String token){
        return JWT.require(algorithm()).build().verify(token);
    }

    private Instant generarFechaExpiracion(){
        Date ahora = new Date();
        return new Date(ahora.getTime() + expirationMs).toInstant();
    }
}
