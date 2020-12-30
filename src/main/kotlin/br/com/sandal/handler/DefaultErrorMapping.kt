package br.com.sandal.handler

import br.com.sandal.account.InvalidPersonIdException
import br.com.sandal.account.digital.AccountNumberAlreadyExistException
import br.com.sandal.account.digital.DigitalAccountNotFoundException
import br.com.sandal.account.digital.InvalidAccountNumberException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.lang.IllegalArgumentException
import javax.validation.constraints.NotNull

@Validated
@Component
class DefaultErrorMapping : ErrorMappingAware {

    private var mapping: Array<ErrorMapping>? = null

    constructor(@NotNull(message = "Informe o ErrorMapping") mapping:Array<ErrorMapping>?){
        this.mapping = mapping
    }

    constructor(){
        this.mapping = createDefaultMapping()
    }

    private fun createDefaultMapping():Array<ErrorMapping>? {
        var mapping:Array<ErrorMapping> = arrayOf(ErrorMapping("E002", IllegalArgumentException::class.java, HttpStatus.BAD_REQUEST),
                    ErrorMapping("E003", InvalidPersonIdException::class.java, HttpStatus.BAD_REQUEST),
                    ErrorMapping("E004", DigitalAccountNotFoundException::class.java, HttpStatus.NOT_FOUND),
                    ErrorMapping("E005", InvalidAccountNumberException::class.java, HttpStatus.BAD_REQUEST),
                    ErrorMapping("E006", AccountNumberAlreadyExistException::class.java, HttpStatus.BAD_REQUEST))
        return mapping
    }

    override fun getMapping(): Array<ErrorMapping>? = this.mapping
}