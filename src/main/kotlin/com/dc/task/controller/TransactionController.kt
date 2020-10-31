package com.dc.task.controller

import com.dc.task.message.Response
import com.dc.task.service.TransactionService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getTransactionsWithoutParams(): List<Response> {
        return transactionService.findAllTransactionsWithoutParams();
    }

    @GetMapping(params = ["account_type"])
    fun getTransactionsWithAccountTypeParam(@RequestParam account_type: List<String>): List<Response> {

        val res = mutableListOf<Response>()

        for(type in account_type) {
            res += transactionService.findTransactionsWithAccountTypeParam(type)
        }

        return res
    }

    @GetMapping(params = ["customer_id"])
    fun getTransactionsWithCustomerIdParam(@RequestParam customer_id: List<String>): List<Response> {

        val res = mutableListOf<Response>()

        for(id in customer_id) {
            res += transactionService.findTransactionsWithCustomerIdParam(id)
        }

        return res
    }

    @GetMapping(params = ["account_type", "customer_id"])
    fun findTransactionWithAllParams(@RequestParam account_type: List<String>,
                                  @RequestParam customer_id: List<String>): List<Response> {

        val res = mutableListOf<Response>()

        for(type in account_type) {
            for(id in customer_id) {
                res += transactionService.findTransactionWithAllParams(type, id)
            }
        }

        return res
    }
}
