package br.com.fourtk.treinamentoKotlin.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    val dateCreate: LocalDateTime = LocalDateTime.now(),
    var dateAlter: LocalDate? =  null,
    @ManyToOne
    val course: Course,
    @ManyToOne
    val author: User,
    @Enumerated(value = EnumType.STRING)
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    @OneToMany(mappedBy = "topic")
    val answer: List<Answer> = ArrayList()
)