package br.com.fourtk.treinamentoKotlin.model

import javax.persistence.*

@Entity
@Table(name = "tb_user")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String
    )
