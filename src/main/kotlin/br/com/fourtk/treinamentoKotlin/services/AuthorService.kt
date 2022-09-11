package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.User
import br.com.fourtk.treinamentoKotlin.repository.AuthorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorService (
    val usersRepository: AuthorRepository
        ): UserDetailsService{
    fun getById(id: Long): User{
        return usersRepository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = usersRepository.findByEmail(username) ?: throw RuntimeException()
        return UserDetail(user)
    }
}
