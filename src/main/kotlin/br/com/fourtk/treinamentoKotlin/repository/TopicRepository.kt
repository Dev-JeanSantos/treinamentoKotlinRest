package br.com.fourtk.treinamentoKotlin.repository

import br.com.fourtk.treinamentoKotlin.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(nameCourse: String, pagination: Pageable): Page<Topic>
}