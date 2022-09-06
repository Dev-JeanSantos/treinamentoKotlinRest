package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicService: TopicServices
        ){

    @GetMapping
    fun list(): List<TopicResponseDTO> {

        return topicService.listar()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): TopicResponseDTO {
        return topicService.getById(id)
    }
    @PostMapping
    fun insertTopic(@RequestBody @Valid topicRequestDTO: TopicRequestDTO) {
        topicService.insertTopic(topicRequestDTO)
    }
    @PutMapping
    fun updateTopic(@RequestBody @Valid updateTopicRequestDTO: UpdateTopicRequestDTO) {
        topicService.update(updateTopicRequestDTO)
    }
    @DeleteMapping("/{id}")
    fun deleteTopic(@PathVariable id: Long) {
        topicService.delete(id)
    }
}
