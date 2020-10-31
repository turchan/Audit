package com.dc.task.repository

import com.dc.task.model.customer.Customer
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : MongoRepository<Customer, Number> {

    fun findOneById(id: Number): Optional<Customer>
}
