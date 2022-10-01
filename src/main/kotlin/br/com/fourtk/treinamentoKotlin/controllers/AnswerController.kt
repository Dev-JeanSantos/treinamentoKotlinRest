package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.model.Answer
import br.com.fourtk.treinamentoKotlin.services.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearerAuth")
class AnswerController  (
    private val answerService: AnswerService
        ){

    @PostMapping
    @Transactional
    fun saveAnswer(@RequestBody @Valid answer: Answer) = answerService.save(answer)

}
