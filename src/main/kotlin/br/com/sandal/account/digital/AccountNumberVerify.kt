package br.com.sandal.account.digital

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountNumberVerify(val digitalAccountRepository: DigitalAccountRepository) {

    private val LOGGER: Logger = LoggerFactory.getLogger(AccountNumberVerify::class.java)

    fun verifyAccountNumberIfAlreadyExist(accountNumber:String){
        LOGGER.info("Verificando se o número da conta {} já existe.")
        var opt: Optional<DigitalAccount>? = digitalAccountRepository.findByAccountNumber(accountNumber)
        if(opt!!.isPresent){
            throw AccountNumberAlreadyExistException(String.format("Número da conta %s já existe.", accountNumber))
        }
    }
}