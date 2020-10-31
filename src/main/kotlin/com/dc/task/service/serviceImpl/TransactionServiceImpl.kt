package com.dc.task.service.serviceImpl

import com.dc.task.message.Response
import com.dc.task.model.transaction.Transaction
import com.dc.task.repository.AccountRepository
import com.dc.task.repository.TransactionRepository
import com.dc.task.service.CustomerService
import com.dc.task.service.TransactionService
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(private val transactionRepository: TransactionRepository,
                             private val accountRepository: AccountRepository,
                             private val customerRepository: CustomerService) : TransactionService {

    override fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    override fun findAllByType(account_type: Number): List<Transaction> {
        return transactionRepository.findAllByType(account_type)
    }

    override fun findAllByCid(customer_id: Number): List<Transaction> {
        return transactionRepository.findAllByCid(customer_id)
    }

    override fun findAllByTypeAndCid(account_type: Number, customer_id: Number): List<Transaction> {
        return transactionRepository.findAllByTypeAndCid(account_type, customer_id)
    }

    override fun deleteAll() {
        transactionRepository.deleteAll()
    }

    override fun findAllTransactionsWithoutParams(): List<Response> {

        val res = mutableListOf<Response>()

        for (transaction in transactionRepository.findAll()) {

            val account = accountRepository.findOneByType(transaction.type.toInt())
            val customer = customerRepository.findOneById(transaction.cid.toInt())

            val response = Response.Builder()
                    .transaction_date(transaction.transaction_date)
                    .transaction_amount(transaction.amount)
                    .transaction_id(transaction.transaction_id)
                    .name(account.get().name)
                    .first_name(customer.get().first_name)
                    .last_name(customer.get().last_name)
                    .build()

            res.add(response)
        }

        res.sortWith(compareBy { it.transaction_amount?.replace(",", ".")?.toFloatOrNull() } )

        return res
    }

    override fun findTransactionsWithAccountTypeParam(account_type: String): List<Response> {

        return mappedAndSortDataForAccountTypeParam(account_type)
    }

    override fun findTransactionsWithCustomerIdParam(customer_id: String): List<Response> {

        return mappedAndSortDataForCustomerIdParam(customer_id)
    }

    override fun findTransactionWithAllParams(account_type: String, customer_id: String): List<Response> {

        val res: List<Response>

        if(account_type != "ALL" && customer_id != "ALL") {

            res = mappedAndSortDataForAllParams(account_type, customer_id)

        } else if(account_type == "ALL" && customer_id != "ALL") {

            res = mappedAndSortDataForCustomerIdParam(customer_id)

        } else if(account_type != "ALL" && customer_id == "ALL") {

            res = mappedAndSortDataForAccountTypeParam(account_type)

        } else {

            res = findAllTransactionsWithoutParams()
        }

        return res
    }

    override fun mappedAndSortDataForAccountTypeParam(account_type: String): List<Response> {

        val res = mutableListOf<Response>()

        val account = accountRepository.findOneByType(account_type.toInt())

        for (transaction in transactionRepository.findAllByType(account_type.toInt())) {

            val customer = customerRepository.findOneById(transaction.cid.toInt())

            val response = Response.Builder()
                    .transaction_date(transaction.transaction_date)
                    .transaction_amount(transaction.amount)
                    .transaction_id(transaction.transaction_id)
                    .name(account.get().name)
                    .first_name(customer.get().first_name)
                    .last_name(customer.get().last_name)
                    .build()

            res.add(response)
        }

        res.sortWith(compareBy { it.transaction_amount?.replace(",", ".")?.toFloatOrNull() } )

        return res
    }

    override fun mappedAndSortDataForCustomerIdParam(customer_id: String): List<Response> {

        val res = mutableListOf<Response>()

        val customer = customerRepository.findOneById(customer_id.toInt())

        for (transaction in transactionRepository.findAllByCid(customer_id.toInt())) {

            val account = accountRepository.findOneByType(transaction.type.toInt())

            val response = Response.Builder()
                    .transaction_date(transaction.transaction_date)
                    .transaction_amount(transaction.amount)
                    .transaction_id(transaction.transaction_id)
                    .name(account.get().name)
                    .first_name(customer.get().first_name)
                    .last_name(customer.get().last_name)
                    .build()

            res.add(response)
        }

        res.sortWith(compareBy { it.transaction_amount?.replace(",", ".")?.toFloatOrNull() } )

        return res
    }

    override fun mappedAndSortDataForAllParams(account_type: String, customer_id: String): List<Response> {

        val res = mutableListOf<Response>()

        val account = accountRepository.findOneByType(account_type.toInt())
        val customer = customerRepository.findOneById(customer_id.toInt())

        for (transaction in transactionRepository.findAllByTypeAndCid(account_type.toInt(), customer_id.toInt())) {

            val response = Response.Builder()
                    .transaction_date(transaction.transaction_date)
                    .transaction_amount(transaction.amount)
                    .transaction_id(transaction.transaction_id)
                    .name(account.get().name)
                    .first_name(customer.get().first_name)
                    .last_name(customer.get().last_name)
                    .build()

            res.add(response)
        }

        res.sortWith(compareBy { it.transaction_amount?.replace(",", ".")?.toFloatOrNull() })

        return res
    }
}
