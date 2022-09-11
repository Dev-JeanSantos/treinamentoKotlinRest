package br.com.fourtk.treinamentoKotlin.repository

import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByCourseName(nameCourse: String, pagination: Pageable): Page<Topic>
    @Query("SELECT NEW br.com.fourtk.treinamentoKotlin.responseDTO.TopicByCategoryDTO(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun reports():List<TopicByCategoryDTO>
}