package com.example.ecoloco.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private final   UserDetailsService userDetailsService;

    //Este metodo se ejecuta cada vez que se hace una petición a la API
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //Obtenemos el token del header si existe
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String username;
        //Si no existe el token en el header, continuamos con la petición
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        //Si existe el token en el header, lo extraemos
        token = authHeader.substring(7);
        //Extraemos el username del JWT token
        username = jwtService.extraerUsername(token);
        //Si el username no es nulo y no hay una autenticación en el contexto de seguridad
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtService.tokenValido(token, userDetails)){
                //Extraemos el rol desde los claims del token
                String rol = jwtService.extraerClaim(token, claims -> claims.get("rol", String.class));
                //Si el token es válido, autenticamos al usuario
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                // Si tienes el rol en el JWT, lo puedes agregar como una autoridad adicional
                if (rol != null) {
                    // Crear una autoridad basada en el rol y agregarla
                    authToken.getAuthorities().add(new SimpleGrantedAuthority("ROLE_" + rol));
                }
                //Añadimos los detalles de la autenticación
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //Añadimos la autenticación al contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //Continuamos con la petición
        filterChain.doFilter(request, response);
    }
}
