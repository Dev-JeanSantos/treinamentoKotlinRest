package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.Course
import br.com.fourtk.treinamentoKotlin.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService (
    val courseRepository: CourseRepository
    ){
    fun getById(id: Long): Course{
        return courseRepository.getOne(id)
    }
}
