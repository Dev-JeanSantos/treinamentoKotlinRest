package br.com.fourtk.treinamentoKotlin.service

import br.com.fourtk.treinamentoKotlin.exception.NotFoundException
import br.com.fourtk.treinamentoKotlin.mapper.TopicRequestMapper
import br.com.fourtk.treinamentoKotlin.mapper.TopicResponseMapper
import br.com.fourtk.treinamentoKotlin.model.TopicResponseDTOTest
import br.com.fourtk.treinamentoKotlin.model.TopicTest
import br.com.fourtk.treinamentoKotlin.repository.TopicRepository
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {

    val topics = PageImpl(listOf(TopicTest.build()))
    val pagination: Pageable = mockk()

    val topicRepository: TopicRepository = mockk{
        //TODO("Every -> toda vez que")
        every { findByCourseName(any(), any()) } returns topics
        every { findAll(pagination) } returns topics
    }

    val topicResponseMapper: TopicResponseMapper = mockk{
        every { map(any()) } returns TopicResponseDTOTest.build()
    }
    val topicRequestMapper: TopicRequestMapper = mockk()

    val topicServices = TopicServices(
            topicRepository, topicResponseMapper,topicRequestMapper
    )

    @Test
    fun `deve listar topicos a partir de um curso`(){

        topicServices.listar("Kotlin Avançado", pagination )

        verify(exactly = 1) { topicRepository.findByCourseName(any(),any()) }
        verify (exactly = 1){ topicResponseMapper.map(any()) }
        verify (exactly = 0){ topicRepository.findAll(pagination) }

    }
    @Test
    fun `deve listar topicos quando o nome do curso for nulo`(){

        topicServices.listar(null, pagination )

        verify(exactly = 0) { topicRepository.findByCourseName(any(),any()) }
        verify (exactly = 1){ topicResponseMapper.map(any()) }
        verify (exactly = 1){ topicRepository.findAll(pagination) }

    }
    @Test
    fun `deve listar not found exception quando topico nao for encontrado`(){

        every { topicRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicServices.getById(1)
        }

        Assertions.assertThat(atual.message).isEqualTo("Topic not found!")

    }



}