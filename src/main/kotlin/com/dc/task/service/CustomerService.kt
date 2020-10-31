package com.dc.task.service

import com.dc.task.model.customer.Customer
import java.util.*

interface CustomerService {
    fun findAll(): List<Customer>
    fun deleteAll()
    fun findOneById(id: Number): Optional<Customer>
}
