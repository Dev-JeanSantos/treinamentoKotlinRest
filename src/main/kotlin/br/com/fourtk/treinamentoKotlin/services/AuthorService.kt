package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.User
import br.com.fourtk.treinamentoKotlin.repository.AuthorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorService (
    val authorsRepository: AuthorRepository
        ){
    fun getById(id: Long): User{
        return authorsRepository.getOne(id)
    }
}
