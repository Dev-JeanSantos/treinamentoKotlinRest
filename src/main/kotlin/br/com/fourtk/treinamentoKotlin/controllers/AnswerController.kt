package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.model.Answer
import br.com.fourtk.treinamentoKotlin.requestDTO.AnswerRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.AnswerResponseDTO
import br.com.fourtk.treinamentoKotlin.services.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearerAuth")
class AnswerController(
    private val answerService: AnswerService
){
    @PostMapping
    @Transactional
    fun saveAnswer(
        @RequestBody @Valid answerRequestDTO: AnswerRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AnswerResponseDTO>{
        val answerResponseDTO = answerService.save(answerRequestDTO)
        val uri = uriBuilder.path("/answers/${answerResponseDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(answerResponseDTO)
    }

}
