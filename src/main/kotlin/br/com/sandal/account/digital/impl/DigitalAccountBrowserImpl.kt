package br.com.sandal.account.digital.impl

import br.com.sandal.account.digital.DigitalAccount
import br.com.sandal.account.digital.DigitalAccountBrowser
import br.com.sandal.account.digital.DigitalAccountNotFoundException
import br.com.sandal.account.digital.DigitalAccountRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DigitalAccountBrowserImpl(val digitalAccountRepository: DigitalAccountRepository) : DigitalAccountBrowser {

    override fun findById(id: UUID): Optional<DigitalAccount> {
        var digitalAccount:Optional<DigitalAccount> = digitalAccountRepository.findById(id)

        if(digitalAccount.isEmpty){
            throw DigitalAccountNotFoundException(String.format("Conta digital com o id %s não encontrada", id))
        }

        return digitalAccount
    }

    override fun findByAccountNumber(accountNumber: String): Optional<DigitalAccount>? =
        digitalAccountRepository.findByAccountNumber(accountNumber)
}