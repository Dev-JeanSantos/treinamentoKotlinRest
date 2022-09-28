package br.com.fourtk.treinamentoKotlin.service

import br.com.fourtk.treinamentoKotlin.mapper.TopicRequestMapper
import br.com.fourtk.treinamentoKotlin.mapper.TopicResponseMapper
import br.com.fourtk.treinamentoKotlin.model.TopicResponseDTOTest
import br.com.fourtk.treinamentoKotlin.model.TopicTest
import br.com.fourtk.treinamentoKotlin.repository.TopicRepository
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TopicServiceTest {

    val topics = PageImpl(listOf(TopicTest.build()))

    val topicRepository: TopicRepository = mockk{
        //TODO("Every -> toda vez que")
        every { findByCourseName(any(), any()) } returns topics
    }
    val pagination: Pageable = mockk()
    val topicResponseMapper: TopicResponseMapper = mockk()
    val topicRequestMapper: TopicRequestMapper = mockk()

    val topicServices = TopicServices(
            topicRepository, topicResponseMapper,topicRequestMapper
    )

    @Test
    fun `deve listar topicos a partir de um curso`(){
        every { topicResponseMapper.map(any()) } returns TopicResponseDTOTest.build()
        topicServices.listar("Kotlin Avançado", pagination )

        verify(exactly = 1) { topicRepository.findByCourseName(any(),any()) }
        verify (exactly = 1){ topicResponseMapper.map(any()) }
        verify (exactly = 0){ topicRepository.findAll(pagination) }

    }


}