package br.com.sandal.account.digital.api.v1.browser

import br.com.sandal.account.digital.api.v1.browser.dto.DigitalAccountResponseDto
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.util.*


@RequestMapping("/v1/digital-accounts")
interface DigitalAccountBrowserApi {

    @RequestMapping(value = ["/find-by-id"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@RequestParam("id") id:UUID):HttpEntity<DigitalAccountResponseDto>

    @RequestMapping(value = ["/find-by-account-number"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByAccountNumber(@RequestParam("account-number") accountNumber:String):HttpEntity<DigitalAccountResponseDto>

    @RequestMapping(value = ["/find-by-person-id"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findByPersonId(@RequestParam("person-id") personId:UUID):HttpEntity<List<DigitalAccountResponseDto>>
}