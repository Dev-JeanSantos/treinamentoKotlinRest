package br.com.fourtk.treinamentoKotlin.integration

import br.com.fourtk.treinamentoKotlin.configuration.DatabaseContainerConfiguration
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.model.TopicTest
import br.com.fourtk.treinamentoKotlin.repository.TopicRepository
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var topicRepository: TopicRepository
    private val pagination = PageRequest.of(0,5)
    private val topic = TopicTest.build()


    @Test
    fun `must generate a report`(){

        topicRepository.save(topic)
        val reports = topicRepository.reports()

        Assertions.assertThat(reports).isNotNull
        Assertions.assertThat(reports.first()).isExactlyInstanceOf(TopicByCategoryDTO::class.java)
    }

    @Test
    fun `must list a topic by course name`(){

        topicRepository.save(topic)
        val topic = topicRepository.findByCourseName(nameCourse = "Kotlin", pagination = pagination)
        Assertions.assertThat(topic.totalElements).isEqualTo(1)
    }
}