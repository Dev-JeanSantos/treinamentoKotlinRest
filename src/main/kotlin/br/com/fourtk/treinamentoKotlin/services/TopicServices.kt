package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.Course
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicServices (
    private var topicos: List<Topic>
        ){

    init {
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
        val topic2 = Topic(
            id = 2,
            title = "Duvidas Kotlin 2",
            message = "states no Kotlin",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 3,
                name = "Marcela Santos",
                email = "marsan@gmail.com"
            )
        )
        val topic3 = Topic(
            id = 3,
            title = "construtores",
            message = "erro nos construtores do Kotlin",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Programação"
            ),
            author = User(
                id = 2,
                name = "Ingrid Almeida",
                email = "almeidaingrid@gmail.com"
            )
        )
        topicos = Arrays.asList(topic, topic2,topic3)
    }

    fun listar(): List<Topic> {
        return topicos
    }

    fun getById(id: Long): Topic {
        return topicos.stream().filter({
            t -> t.id == id
        }).findFirst().get()
    }
}