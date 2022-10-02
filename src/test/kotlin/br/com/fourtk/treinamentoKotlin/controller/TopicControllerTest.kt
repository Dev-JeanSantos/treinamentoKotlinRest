package br.com.fourtk.treinamentoKotlin.controller

import br.com.fourtk.treinamentoKotlin.config.JWTUtil
import br.com.fourtk.treinamentoKotlin.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @Autowired
    private lateinit var  webApplicationContext: WebApplicationContext
    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object{
        private const val RECURSO = "/topics/"
        private const val RECURSO_ID = RECURSO.plus("%s")
    }

    @BeforeEach
    fun setup(){
        token = gerarToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                    SecurityMockMvcConfigurers.springSecurity()).build()
    }

    @Test
    fun `Should return 400 when triggering a tokenless request`(){
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }
    @Test
    fun `Must return 200 when calling topics with token`(){
        mockMvc.get(RECURSO){
            headers { token?.let { this.setBearerAuth(it) }}
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `Must return 200 when calling topics with id and with token`(){
        mockMvc.get(RECURSO_ID.format(1)){
            headers { token?.let { this.setBearerAuth(it) }}
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun gerarToken(): String? {
        val authorities = mutableListOf(Role(1, "READ_WRITE"))
        return jwtUtil.generateToken("email@gmail.com", authorities)

    }

}