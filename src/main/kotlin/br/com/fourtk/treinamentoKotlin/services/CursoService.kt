package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.Course
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService (
    var courses: List<Course>
    ){
    init {
        val course = Course(
            id = 1,
            name = "Kotlin",
            category = "PROGRAMAÇÃO"
        )
        courses = Arrays.asList(course)
    }

    fun getById(id: Long): Course{
        return courses.stream().filter { c ->
            c.id == id
        }.findFirst().get()
    }
}
