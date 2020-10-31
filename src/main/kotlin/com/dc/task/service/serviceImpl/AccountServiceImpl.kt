package com.dc.task.service.serviceImpl

import com.dc.task.model.account.Account
import com.dc.task.repository.AccountRepository
import com.dc.task.service.AccountService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {

    override fun findAll(): List<Account> {
        return accountRepository.findAll();
    }

    override fun deleteAll() {
        accountRepository.deleteAll()
    }

    override fun findOneByType(account_type: Number): Optional<Account> {
        return accountRepository.findOneByType(account_type)
    }
}
