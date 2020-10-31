package com.dc.task.controller


import com.dc.task.message.Response
import com.dc.task.service.TransactionService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import kotlin.test.assertEquals


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
internal class TransactionControllerTest(@Autowired val transactionService: TransactionService) {

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build()
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions without param`() {

        val transactionList = transactionService.findAllTransactionsWithoutParams()

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with account_type param`() {

        val transactionList = transactionService.findTransactionsWithAccountTypeParam("1")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?account_type=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with customer_id param`() {

        val transactionList = transactionService.findTransactionsWithCustomerIdParam("1")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?customer_id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with two params`() {

        val transactionList = transactionService.findTransactionWithAllParams("1", "1")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?account_type=1&customer_id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with two ALL params`() {

        val transactionList = transactionService.findTransactionWithAllParams("ALL", "ALL")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?account_type=ALL&customer_id=ALL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with first ALL and second other params`() {

        val transactionList = transactionService.findTransactionWithAllParams("ALL", "1")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?account_type=ALL&customer_id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }

    @WithMockUser("admin")
    @Test
    fun `get transactions with second other and first other params`() {

        val transactionList = transactionService.findTransactionWithAllParams("1", "ALL")

        val json = Gson()
        val jsonTransactionList: String = json.toJson(transactionList)

        val result = mockMvc.perform(get("/transaction?account_type=1&customer_id=ALL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        assertEquals(jsonTransactionList, result.response.contentAsString)
    }
}

