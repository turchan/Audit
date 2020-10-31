package com.dc.task.repository

import com.dc.task.model.account.Account
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : MongoRepository<Account, Number> {

    fun findOneByType(account_type: Number): Optional<Account>

}
