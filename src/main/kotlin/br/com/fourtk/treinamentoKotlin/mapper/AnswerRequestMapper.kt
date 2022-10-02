package br.com.fourtk.treinamentoKotlin.mapper

import br.com.fourtk.treinamentoKotlin.model.Answer
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.requestDTO.AnswerRequestDTO
import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.services.AuthorService
import br.com.fourtk.treinamentoKotlin.services.CursoService
import br.com.fourtk.treinamentoKotlin.services.TopicServices
import org.springframework.stereotype.Component

@Component
class AnswerRequestMapper(
    private val topicService: TopicServices,
    private val authorService: AuthorService): Mapper<AnswerRequestDTO, Answer>{

    override fun map(a: AnswerRequestDTO): Answer  {
        return Answer(
            message = a.message,
            solution = a.solution,
            author = authorService.getById(a.idAuthor),
            topic = topicService.getByIdAnswer(a.idTopic)
        )
    }
}
