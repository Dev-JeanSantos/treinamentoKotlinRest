package br.com.fourtk.treinamentoKotlin.repository

import br.com.fourtk.treinamentoKotlin.model.Course
import br.com.fourtk.treinamentoKotlin.model.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {
}