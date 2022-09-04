package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.model.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorService (
    var authors: List<User>
        ){
    init {
        val author = User(
            id = 1,
            name = "Jean Santos",
            email = "jeancbsan@gmail.com"
        )
        authors = Arrays.asList(author)
    }

    fun getById(id: Long): User{
        return authors.stream().filter { c -> c.id == id }.findFirst().get()
    }
}
