package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.Course
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicServices {

    fun listar(): List<Topic> {
        val topic = Topic(
            id = 1,
            title = "Duvidas Kotlin",
            message = "Variaveis no Kotlin",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 1,
                name = "Jean Santos",
                email = "jeancbsan@gmail.com"
            )
        )
        return Arrays.asList(topic, topic,topic)
    }
}