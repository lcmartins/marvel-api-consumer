package com.luciano.marvel.marvelapiconsumer.security

import com.luciano.marvel.marvelapiconsumer.extensions.empty
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import javax.crypto.SecretKey


@Service
class JWTService(@Value("\${security.jwt.signatureKey}") private val signatureKey: String) {

    @Value("\${security.jwt.expiration}")
    private val expiration = ""

    private val ROLES = "roles"

    private var signingKey: SecretKey? = null

    init {
        val secret = Base64.getEncoder().encodeToString(signatureKey.encodeToByteArray());
        this.signingKey = Keys.hmacShaKeyFor(secret.toByteArray());
    }
    
    fun createToken(authentication: Authentication): String? {
        val username: String = authentication.name
        val authorities: Collection<GrantedAuthority> = authentication.authorities
        val claims = Jwts.claims().setSubject(username)
        if (!authorities.isEmpty()) {
            claims[ROLES] = authorities.stream().map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.joining(","))
        }
        val now = Date()
        val expirationTime = Date(Date().time.plus(expiration.toLong()))
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expirationTime)
            .signWith(this.signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication? {
        val claims: Claims = Jwts.parserBuilder()
                                    .setSigningKey(this.signingKey)
                                    .build().parseClaimsJws(token).body
        val authoritiesClaim = claims[ROLES]
        val authorities = if (authoritiesClaim == null) AuthorityUtils.NO_AUTHORITIES
                            else AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString())
        val principal = User(claims.subject, String.empty(), authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(this.signingKey).build().parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}