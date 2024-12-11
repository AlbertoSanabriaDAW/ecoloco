package com.example.ecoloco.security;

import com.example.ecoloco.modelos.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "W285iSI4QuNgN5vqMu0Mu9pN5frGvSYBPOUxja/2AUxZrkSBUHwzZZHspiNopr+Y";

    //Extraer el username del JWT token
    public String extraerUsername(String jwtToken) {
        return extraerClaim(jwtToken, Claims::getSubject);
    }

    //Extraer el claim del JWT token
    public <T> T extraerClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    //Generar el token
    public String generarToken(UserDetails userDetails){
        return generarToken(new HashMap<>(), userDetails);
    }

    public String generarToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        //Añadir el id del usuario al token
        String userId = ((Usuario) userDetails).getId().toString();
        extraClaims.put("userId", userId);

        //Añadir el rol del usuario al token
        String rol = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Convierte a String
                .findFirst() // Obtén el primer rol
                .orElse("USER"); // En caso de que no tenga rol, asignamos un rol por defecto

        // Añadir el rol al token como un claim
        extraClaims.put("rol", rol);

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Validar el token
    public boolean tokenValido(String jwtToken, UserDetails userDetails) {
        final String username = extraerUsername(jwtToken);
        return (username.equals(userDetails.getUsername()) && !tokenExpirado(jwtToken));
    }

    private boolean tokenExpirado(String jwtToken) {
        return extraerExpiration(jwtToken).before(new Date());
    }

    private Date extraerExpiration(String jwtToken) {
        return extraerClaim(jwtToken, Claims::getExpiration);
    }

    private Claims extraerClaims(String jwtToken) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwtToken).getBody();
    }

    //Metodo para obtener la clave secreta
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
