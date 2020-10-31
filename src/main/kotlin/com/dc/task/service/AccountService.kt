package com.dc.task.service

import com.dc.task.model.account.Account
import java.util.*

interface AccountService {
    fun findAll(): List<Account>
    fun deleteAll()
    fun findOneByType(account_type: Number): Optional<Account>
}
