package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
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
    @Transactional
    fun insertTopic(
        @RequestBody @Valid topicRequestDTO: TopicRequestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicResponseDTO> {
        val topicResponseDTO = topicService.insertTopic(topicRequestDTO)
        val uri = uriBuilder.path("/topics/${topicResponseDTO.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicResponseDTO)
    }
    @PutMapping
    @Transactional
    fun updateTopic(@RequestBody @Valid updateTopicRequestDTO: UpdateTopicRequestDTO)
    : ResponseEntity<TopicResponseDTO>  {
        val topicResponseDTO = topicService.update(updateTopicRequestDTO)
        return  ResponseEntity.ok().body(topicResponseDTO)
    }
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTopic(@PathVariable id: Long) {
        topicService.delete(id)
    }
}
