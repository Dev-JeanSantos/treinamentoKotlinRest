package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.model.Course
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.model.User
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Arrays

@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicService: TopicServices
        ){

    @GetMapping
    fun list(): List<Topic> {

        return topicService.listar()
    }
}
