package br.com.sandal.account.digital.impl

import br.com.saar.util.Assert
import br.com.sandal.account.InvalidPersonIdException
import br.com.sandal.account.digital.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class DigitalAccountCreatorImpl(val digitalAccountRepository: DigitalAccountRepository,
                    val accountNumberVerify: AccountNumberVerify) : DigitalAccountCreator {

    override fun save(digitalAccount: DigitalAccount): DigitalAccount {
        if(!Assert.notNullOrEmpty(digitalAccount.accountNumber)){
            throw InvalidAccountNumberException("Número da conta invalido");
        }

        if(Objects.isNull( digitalAccount.personId)){
            throw InvalidPersonIdException("Person id da conta invalido")
        }

        accountNumberVerify.verifyAccountNumberIfAlreadyExist(digitalAccount.accountNumber!!)
        return digitalAccountRepository.save(digitalAccount)
    }
}