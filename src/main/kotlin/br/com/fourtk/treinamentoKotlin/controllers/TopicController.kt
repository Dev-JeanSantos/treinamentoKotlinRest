package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/topics")
class TopicController  (
    private val topicService: TopicServices
        ){
    @GetMapping
    fun list(
        @RequestParam(required = false) nameCourse: String?,
        @PageableDefault(size = 5, sort = ["dateCreate"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicResponseDTO> {
        return topicService.listar(nameCourse, pagination)
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

    @GetMapping("/reports")
    fun reports_topic():List<TopicByCategoryDTO>{
        return topicService.reports()
    }
}
