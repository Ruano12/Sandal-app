package br.com.sandal.account.digital.api.v1.browser.impl

import br.com.sandal.account.digital.DigitalAccount
import br.com.sandal.account.digital.DigitalAccountBrowser
import br.com.sandal.account.digital.api.v1.browser.DigitalAccountBrowserApi
import br.com.sandal.account.digital.api.v1.browser.dto.DigitalAccountResponseDto
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@Transactional
class DigitalAccountBrowserApiImpl(val digitalAccountBrowser: DigitalAccountBrowser) : DigitalAccountBrowserApi {

    override fun findById(id: UUID): HttpEntity<DigitalAccountResponseDto> {
        var digitalAccount:Optional<DigitalAccount> = digitalAccountBrowser.findById(id)

        var dto:DigitalAccountResponseDto = DigitalAccountResponseDto.Companion.fromEntity(digitalAccount.get())

        return ResponseEntity<DigitalAccountResponseDto>(dto, HttpStatus.OK)
    }

    override fun findByAccountNumber(accountNumber: String): HttpEntity<DigitalAccountResponseDto> {
        var digitalAccount:Optional<DigitalAccount>? = digitalAccountBrowser.findByAccountNumber(accountNumber)

        var dto:DigitalAccountResponseDto? =
            if(digitalAccount!!.isPresent ) DigitalAccountResponseDto.Companion.fromEntity(digitalAccount.get())
        else null

        return ResponseEntity<DigitalAccountResponseDto>(dto, HttpStatus.OK)
    }

    override fun findByPersonId(personId: UUID): HttpEntity<List<DigitalAccountResponseDto>> {
        var digitalAccounts:List<DigitalAccount>? = digitalAccountBrowser.findByPersonId(personId)

        var accounts:List<DigitalAccountResponseDto> = digitalAccounts!!.stream().map { acc ->
            DigitalAccountResponseDto.Companion.fromEntity(acc) }.collect(Collectors.toList())

        return ResponseEntity<List<DigitalAccountResponseDto>>(accounts, HttpStatus.OK)
    }
}