package br.com.sandal.account.digital

import java.util.*

interface DigitalAccountBrowser {
    fun findById(id:UUID):Optional<DigitalAccount>
    fun findByAccountNumber(accountNumber:String):Optional<DigitalAccount>?
    fun findByPersonId(personId:UUID):List<DigitalAccount>?
}