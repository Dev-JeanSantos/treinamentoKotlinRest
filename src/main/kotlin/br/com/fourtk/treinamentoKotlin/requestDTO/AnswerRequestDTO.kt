package br.com.fourtk.treinamentoKotlin.requestDTO

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class AnswerRequestDTO (
    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val message: String,
    val solution: Boolean,
    @field:NotNull
    val idAuthor: Long,
    @field:NotNull
    val idTopic: Long
    )