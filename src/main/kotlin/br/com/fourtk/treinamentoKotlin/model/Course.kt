package br.com.fourtk.treinamentoKotlin.model

import javax.persistence.*

@Entity
data class Course (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val category: String
    )
