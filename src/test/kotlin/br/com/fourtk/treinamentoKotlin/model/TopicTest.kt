package br.com.fourtk.treinamentoKotlin.model

object TopicTest {
    fun build() = Topic(
            id = 1,
            title = "Kotlin basico",
            message = "Implementando com Kotlin",
            course = CourseTest.build(),
            author = UserTest.build()
    )
}