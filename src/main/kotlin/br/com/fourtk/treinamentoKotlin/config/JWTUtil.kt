package br.com.fourtk.treinamentoKotlin.config

import br.com.fourtk.treinamentoKotlin.exception.NotFoundException
import br.com.fourtk.treinamentoKotlin.services.AuthorService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val  usuarioService: AuthorService
){
    val expiration: Long = 3600000
    @Value("\${jwt.secret}")
    lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String?{
        return Jwts.builder().setSubject(username).
            claim("role",authorities).
        setExpiration(Date(System.currentTimeMillis() + expiration)).
        signWith(
                SignatureAlgorithm.HS512,
                secret.toByteArray()).compact()
    }

    fun isValid(jwt: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt)
            true
        }catch (e: NotFoundException){
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}