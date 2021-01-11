package br.com.sandal.account.digital

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface DigitalAccountRepository : JpaRepository<DigitalAccount, UUID> {

    @Query("select a from DigitalAccount a where a.accountNumber = :accountNumber")
    fun findByAccountNumber(@Param("accountNumber") accountNumber:String):Optional<DigitalAccount>?

    @Query("select a from DigitalAccount a where a.personId = :personId")
    fun findByPersonId(@Param("personId") personId:UUID):List<DigitalAccount>?
}