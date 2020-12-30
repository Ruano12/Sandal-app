package br.com.sandal.account.digital.api.v1.creator.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class DigitalAccountCreatorResponseDto {

    @JsonProperty(value = "id")
    var id:UUID? = null
}