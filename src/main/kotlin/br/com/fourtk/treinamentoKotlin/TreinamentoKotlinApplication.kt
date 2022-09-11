package br.com.fourtk.treinamentoKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class TreinamentoKotlinApplication

fun main(args: Array<String>) {
	runApplication<TreinamentoKotlinApplication>(*args)
}
