package br.com.fourtk.treinamentoKotlin.requestDTO

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class TopicRequestDTO (
    @field:NotEmpty(message = "Essa porra não deve ser vazia, filha da puta!")
    @field:Size(min = 5, max = 50)
    val title: String,
    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val message: String,
    @field:NotNull
    val idCourse: Long,
    @field:NotNull
    val idAuthor: Long,
    )