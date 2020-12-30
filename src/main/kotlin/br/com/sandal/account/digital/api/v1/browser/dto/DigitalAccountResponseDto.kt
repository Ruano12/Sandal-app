package br.com.sandal.account.digital.api.v1.browser.dto

import br.com.sandal.account.digital.DigitalAccount
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class DigitalAccountResponseDto {

    @JsonProperty(value = "id")
    var id: UUID? = null

    @JsonProperty(value = "person-id")
    var personId:UUID? = null

    @JsonProperty(value = "created-at")
    var createdAt:Date? = null

    @JsonProperty(value = "updated-at")
    var updatedAt:Date? = null

    @JsonProperty(value = "account-number")
    var accountNumber:String? = null

    companion object {
        fun fromEntity(digitalAccount: DigitalAccount) :DigitalAccountResponseDto {
            var dto:DigitalAccountResponseDto =
                    DigitalAccountResponseDto()

            dto.accountNumber = digitalAccount.accountNumber
            dto.createdAt = digitalAccount.createdAt
            dto.id = digitalAccount.id
            dto.personId = digitalAccount.personId
            dto.updatedAt = digitalAccount.updatedAt

            return dto
        }
    }
}