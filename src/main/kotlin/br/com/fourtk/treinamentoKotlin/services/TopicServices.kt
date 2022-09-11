package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.exception.NotFoundException
import br.com.fourtk.treinamentoKotlin.mapper.TopicRequestMapper
import br.com.fourtk.treinamentoKotlin.mapper.TopicResponseMapper
import br.com.fourtk.treinamentoKotlin.repository.TopicRepository
import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicServices(
    private val topicRepository: TopicRepository,
    private val topicoResponseMapper: TopicResponseMapper,
    private val topicoRequestMapper: TopicRequestMapper,
    val notFoundException: String = "Topico não encontrado!"
)
{
    fun listar(
        nameCourse: String?,
        pagination: Pageable
    ): Page<TopicResponseDTO> {
        val topics = if(nameCourse == null){
            topicRepository.findAll(pagination)
        } else{
            topicRepository.findByCourseName(nameCourse, pagination)
        }
        return topics.map {
                t -> topicoResponseMapper.map(t)
        }
    }

    fun getById(id: Long): TopicResponseDTO {
        val possibleTopic =  topicRepository.findById(id).
            orElseThrow{NotFoundException(notFoundException)}
        return topicoResponseMapper.map(possibleTopic)
    }

    fun insertTopic(topicRequestDTO: TopicRequestDTO): TopicResponseDTO{
        val topic = topicoRequestMapper.map(topicRequestDTO)
        topicRepository.save(topic)
        return topicoResponseMapper.map(topic)
    }

    fun update(updateTopicRequestDTO: UpdateTopicRequestDTO): TopicResponseDTO {
        val topic = topicRepository.findById(updateTopicRequestDTO.id).
            orElseThrow{NotFoundException(notFoundException)}
        //(Update) Delete old topic and create new topic
            topic.title = updateTopicRequestDTO.title
            topic.message = updateTopicRequestDTO.message

        return topicoResponseMapper.map(topic)
    }
    fun delete(id: Long) {
        val topic = topicRepository.deleteById(id)
    }

    fun reports():List<TopicByCategoryDTO> {
      return topicRepository.reports()
    }
}