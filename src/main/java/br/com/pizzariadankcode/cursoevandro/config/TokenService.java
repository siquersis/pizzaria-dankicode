package br.com.pizzariadankcode.cursoevandro.config;

import br.com.pizzariadankcode.cursoevandro.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String criarToken(Usuario usuario) {

        try {
            Algorithm algoritmo = Algorithm.HMAC256("123456");
            LocalDateTime dataExpiracao = LocalDateTime.now().plusHours(2);

            return JWT.create()
                    .withIssuer("Pizzaria Danki Code")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao.toInstant(ZoneOffset.of("-03:00")))
                    .sign(algoritmo);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao criar token: ", ex);
        }
    }

    public String buscaUsuarioToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256("123456");

            return JWT.require(algoritmo)
                    .withIssuer("Pizzaria Danki Code")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Token incorreto!", ex);
        }
    }
}
