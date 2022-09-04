package br.com.fourtk.treinamentoKotlin.model

import java.time.LocalDateTime

data class Answer (
    val id: Long? = null,
    val message: String,
    val dateCreate: LocalDateTime = LocalDateTime.now(),
    val user: User,
    val topic: Topic,
    val solution: Boolean
    )
