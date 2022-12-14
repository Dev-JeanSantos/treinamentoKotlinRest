package br.com.fourtk.treinamentoKotlin.repository

import br.com.fourtk.treinamentoKotlin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: JpaRepository<User, Long> {
    abstract fun findByEmail(username: String?): User?
}