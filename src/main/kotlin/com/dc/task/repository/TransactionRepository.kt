package com.dc.task.repository

import com.dc.task.model.transaction.Transaction
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : MongoRepository<Transaction, Number> {

    fun findAllByType(account_type: Number): List<Transaction>
    fun findAllByCid(customer_id: Number): List<Transaction>
    fun findAllByTypeAndCid(account_type: Number ,customer_id: Number): List<Transaction>
}
