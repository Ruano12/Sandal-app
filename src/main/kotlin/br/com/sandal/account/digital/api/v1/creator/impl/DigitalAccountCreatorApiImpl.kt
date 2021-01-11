package br.com.sandal.account.digital.api.v1.creator.impl

import br.com.sandal.account.digital.DigitalAccount
import br.com.sandal.account.digital.DigitalAccountCreator
import br.com.sandal.account.digital.api.v1.creator.DigitalAccountCreatorApi
import br.com.sandal.account.digital.api.v1.creator.dto.DigitalAccountCreatorRequestDto
import br.com.sandal.account.digital.api.v1.creator.dto.DigitalAccountCreatorResponseDto
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RestController

@RestController
@Transactional
class DigitalAccountCreatorApiImpl(val digitalAccountCreator: DigitalAccountCreator) : DigitalAccountCreatorApi {
    override fun saveDigitalAccount(digitalAccountDto: DigitalAccountCreatorRequestDto): HttpEntity<DigitalAccountCreatorResponseDto> {
        var accountPersist:DigitalAccount = digitalAccountCreator.save(digitalAccountDto.toEntity())

        var digitalAccountCreatorResponseDto:DigitalAccountCreatorResponseDto =
            DigitalAccountCreatorResponseDto()

        digitalAccountCreatorResponseDto.id = accountPersist.id

        return ResponseEntity<DigitalAccountCreatorResponseDto>(digitalAccountCreatorResponseDto!!, HttpStatus.OK)
    }
}



























