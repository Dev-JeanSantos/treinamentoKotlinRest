package br.com.fourtk.treinamentoKotlin.controllers

import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.hibernate.boot.model.relational.Loggable
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.logging.Logger
import javax.validation.Valid

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicService: TopicServices
        ){
    @GetMapping
    @Cacheable("Topicos")
    fun list(
        @RequestParam(required = false) nameCourse: String?,
        @PageableDefault(size = 5, sort = ["dateCreate"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<TopicResponseDTO> {
        //logger.info("passei aqui1")
        return topicService.listar(nameCourse, pagination)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): TopicResponseDTO {
        return topicService.getById(id)
    }
    @PostMapping
    @Transactional
    @CacheEvict(value = ["Topicos"], allEntries = true)
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
    @CacheEvict(value = ["Topicos"], allEntries = true)
    fun updateTopic(@RequestBody @Valid updateTopicRequestDTO: UpdateTopicRequestDTO)
    : ResponseEntity<TopicResponseDTO>  {
        val topicResponseDTO = topicService.update(updateTopicRequestDTO)
        return  ResponseEntity.ok().body(topicResponseDTO)
    }
    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(value = ["Topicos"], allEntries = true)
    fun deleteTopic(@PathVariable id: Long) {
        topicService.delete(id)
    }

    @GetMapping("/reports")
    fun reports_topic():List<TopicByCategoryDTO>{
        return topicService.reports()
    }
}
