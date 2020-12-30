package br.com.sandal.account.digital.api.v1.creator.dto

import br.com.sandal.account.digital.DigitalAccount
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class DigitalAccountCreatorRequestDto {

    @JsonProperty(value = "account-number")
    val accountNumber:String? = null

    @JsonProperty(value = "person-id")
    val personId:UUID? = null

    fun toEntity(): DigitalAccount{
        var account:DigitalAccount = DigitalAccount()

        account.accountNumber = this.accountNumber
        account.personId = this.personId

        return account
    }
}