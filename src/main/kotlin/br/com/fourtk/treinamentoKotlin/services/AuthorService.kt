package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.User
import br.com.fourtk.treinamentoKotlin.repository.AuthorRepository
import org.springframework.stereotype.Service

@Service
class AuthorService (
    val usersRepository: AuthorRepository
        ){
    fun getById(id: Long): User{
        return usersRepository.getOne(id)
    }
}
