package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.mapper.AnswerRequestMapper
import br.com.fourtk.treinamentoKotlin.mapper.AnswerResponseMapper
import br.com.fourtk.treinamentoKotlin.repository.AnswerRepository
import br.com.fourtk.treinamentoKotlin.requestDTO.AnswerRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.AnswerResponseDTO
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val emailService: EmailService,
    private val answerResponseMapper: AnswerResponseMapper,
    private val answerRequestMapper: AnswerRequestMapper
){
    fun save(answerRequestDTO: AnswerRequestDTO): AnswerResponseDTO {
        val answer = answerRequestMapper.map(answerRequestDTO)
        answerRepository.save(answer)
        val emailAuthor = answer.topic.author.email
        emailService.notificar(emailAuthor)
        return answerResponseMapper.map(answer)
    }
}
