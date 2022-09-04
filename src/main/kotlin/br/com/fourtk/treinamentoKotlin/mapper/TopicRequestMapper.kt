package br.com.fourtk.treinamentoKotlin.mapper

import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.services.AuthorService
import br.com.fourtk.treinamentoKotlin.services.CursoService
import org.springframework.stereotype.Component

@Component
class TopicRequestMapper(
    private val cursoService: CursoService,
    private val authorService: AuthorService): Mapper<TopicRequestDTO, Topic>{
    override fun map(t: TopicRequestDTO): Topic  {
        return Topic(
            title = t.title,
            message = t.message,
            course = cursoService.getById(t.idCourse),
            author = authorService.getById(t.idAuthor)
        )
    }
}
