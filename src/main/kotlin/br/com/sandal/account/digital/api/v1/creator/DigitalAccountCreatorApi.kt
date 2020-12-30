package br.com.sandal.account.digital.api.v1.creator

import br.com.sandal.account.digital.DigitalAccount
import br.com.sandal.account.digital.api.v1.creator.dto.DigitalAccountCreatorRequestDto
import br.com.sandal.account.digital.api.v1.creator.dto.DigitalAccountCreatorResponseDto
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@RequestMapping("/v1")
interface DigitalAccountCreatorApi {

    @RequestMapping(value = ["/digital-accounts"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveDigitalAccount(@Valid @RequestBody digitalAccountDto: DigitalAccountCreatorRequestDto):HttpEntity<DigitalAccountCreatorResponseDto>
}