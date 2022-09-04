package br.com.fourtk.treinamentoKotlin.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
