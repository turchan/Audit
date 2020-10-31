package com.dc.task.service.serviceImpl

import com.dc.task.model.customer.Customer
import com.dc.task.repository.CustomerRepository
import com.dc.task.service.CustomerService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun findAll(): List<Customer> {
        return customerRepository.findAll()
    }

    override fun deleteAll() {
        customerRepository.deleteAll()
    }


    override fun findOneById(id: Number): Optional<Customer> {
        return customerRepository.findOneById(id)
    }
}
