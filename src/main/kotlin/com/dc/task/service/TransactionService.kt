package com.dc.task.service

import com.dc.task.message.Response
import com.dc.task.model.transaction.Transaction

interface TransactionService {

    fun findAll(): List<Transaction>
    fun findAllByType(account_type: Number): List<Transaction>
    fun findAllByCid(customer_id: Number): List<Transaction>
    fun findAllByTypeAndCid(account_type: Number ,customer_id: Number): List<Transaction>
    fun deleteAll()
    fun findAllTransactionsWithoutParams(): List<Response>
    fun findTransactionsWithAccountTypeParam(account_type: String): List<Response>
    fun findTransactionsWithCustomerIdParam(customer_id: String): List<Response>
    fun findTransactionWithAllParams(account_type: String, customer_id: String): List<Response>
    fun mappedAndSortDataForAccountTypeParam(account_type: String): List<Response>
    fun mappedAndSortDataForCustomerIdParam(customer_id: String): List<Response>
    fun mappedAndSortDataForAllParams(account_type: String, customer_id: String): List<Response>
}
