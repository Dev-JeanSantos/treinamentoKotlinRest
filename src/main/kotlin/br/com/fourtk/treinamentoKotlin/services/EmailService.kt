package br.com.fourtk.treinamentoKotlin.services

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService (
    private val javaMailSender: JavaMailSender
)
{
    fun notificar(emailAuthor: String) {
        val message = SimpleMailMessage()

        message.setSubject("[Alura] Resposta Recebida")
        message.setText("Seu Topico foi respondido! Vamos lá conferir.")
        message.setTo(emailAuthor)
        javaMailSender.send(message)
    }
}
