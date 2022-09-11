package br.com.fourtk.treinamentoKotlin.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
data class Role (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String
) : GrantedAuthority{
    override fun getAuthority() =  name

}
