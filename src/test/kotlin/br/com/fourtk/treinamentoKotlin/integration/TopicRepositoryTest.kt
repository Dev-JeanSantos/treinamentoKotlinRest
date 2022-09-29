package br.com.fourtk.treinamentoKotlin.integration

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

@DataJpaTest
@Testcontainers
//Não quero fazer o replace por esse banco de Teste
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val topic = TopicTest.build()

    //Classe estatica (static do Java)
    companion object{
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("testedb")
            withUsername("jean")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry){
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.name", mysqlContainer::getUsername)
        }

    }

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
        val topic = topicRepository.findByCourseName(topic.course.name, PageRequest.of(0, 5))

        Assertions.assertThat(topic).isNotNull
        Assertions.assertThat(topic.first()).isExactlyInstanceOf(Topic::class.java)
    }

}