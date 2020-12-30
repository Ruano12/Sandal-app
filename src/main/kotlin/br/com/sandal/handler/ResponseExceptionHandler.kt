package br.com.sandal.handler

import br.com.saar.util.ExceptionUtils
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.util.WebUtils
import java.lang.Exception
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@Order(100)
@RestControllerAdvice
@Component
class ResponseExceptionHandler : ResponseEntityExceptionHandler {

    private val DEFAULT_ERROR_CODE:String = "E001"

    private val LOGGER: Logger = LoggerFactory.getLogger(ResponseExceptionHandler::class.java)

    private var mapping:Array<ErrorMapping>? = null

    constructor(mappingAware: ErrorMappingAware){
        this.mapping = mappingAware.getMapping()
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        LOGGER.error("Erro no processamento da API", ex)
        var newStatus:HttpStatus = status

        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST)
        }

        var response:ResponseEntity<Any>? = null;
        var responseError: ResponseError? = null

        if(Objects.isNull(body)){

            if(ex is ExceptionWithErrorCode){
                var exErr: ExceptionWithErrorCode = ex as ExceptionWithErrorCode
                responseError = createResponseError(exErr.getErrorCode(), ex.message)
                newStatus = exErr.getHttpStatus()
            }
            if(ex.javaClass.name.equals("org.springframework.orm.jpa.JpaSystemException")){
                var rootCause:Throwable = org.apache.commons.lang.exception.ExceptionUtils.getRootCause(ex)
                var hintMessage:String? = ExceptionUtils.getHintMessage(rootCause)

                responseError = createResponseError("E001", hintMessage)
            } else {
                var int:Int = this.mapping!!.size
                for(errMap: ErrorMapping in this.mapping!!){
                    if(errMap.getClassException()!!.isInstance(ex)){
                        responseError = createResponseError(errMap.getErrorCode(), ex.message)
                        newStatus = errMap.getHttpStatus()!!
                        LOGGER.debug(String.format("Error code encontrado %s.", errMap.getErrorCode()))
                        break
                    }
                }

                if(Objects.isNull(responseError)){
                    responseError = createResponseError(DEFAULT_ERROR_CODE, ex.message)
                }
            }

            response = super.handleExceptionInternal(ex, responseError, headers, newStatus, request)
        } else {
            response = ResponseEntity(body, headers, status)
        }

        return response
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        LOGGER.error("Erro no procssamento da API. handleHttpMessageNotReadable ")
        var optional:Optional<ErrorMapping>  = this.getErrorMapping(ex)

        var message:String = if (ex.cause is UnrecognizedPropertyException)
            String.format("Campo %s inválido", (ex.cause as UnrecognizedPropertyException).propertyName)
            else ex.message!!.split(":")[0]

        if(optional.isEmpty){
            return ResponseEntity(
                createResponseError(DEFAULT_ERROR_CODE, message), headers, status)
        }

        var errorMapping: ErrorMapping = optional.get()

        return ResponseEntity(this.createResponseError(errorMapping.getErrorCode(), message),
                null,
                errorMapping.getHttpStatus()!!)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        LOGGER.error("Erro no procssamento da API. handleMethodArgumentNotValid ")
        var optional:Optional<ErrorMapping>  = this.getErrorMapping(ex)

        var message:String = ex.bindingResult
                               .fieldErrors
                               .stream()
                               .map(FieldError::getDefaultMessage)
                               .collect(Collectors.joining(", "))

        if(optional.isEmpty){
            return ResponseEntity(
                createResponseError(DEFAULT_ERROR_CODE, message), headers, status)
        }

        var errorMapping: ErrorMapping = optional.get()
        return ResponseEntity(this.createResponseError(errorMapping.getErrorCode(), message),
            null,
            errorMapping.getHttpStatus()!!)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    protected fun handleConstraintViolationException(ex: ConstraintViolationException,
                                                     headers:HttpHeaders, status:HttpStatus, request:WebRequest):ResponseEntity<Any>{

        var optional:Optional<ErrorMapping> = this.getErrorMapping(ex)

        if(optional.isEmpty){
            return ResponseEntity(
                createResponseError(DEFAULT_ERROR_CODE, ex.message), headers, status)
        }

        var errorMapping: ErrorMapping = optional.get()
        var message:String = ex.constraintViolations
                               .stream()
                               .map(ConstraintViolation<*>::getMessage)
                               .findFirst().orElse("")

        return ResponseEntity(this.createResponseError(errorMapping.getErrorCode(), message),
            null,
            errorMapping.getHttpStatus()!!)
    }

    @ExceptionHandler(value = [RuntimeException::class])
    protected fun handleGeneric(ex:RuntimeException, request:WebRequest, servletRequest:HttpServletRequest): ResponseEntity<Any> {
        return this.handleExceptionInternal(ex, null, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request)
    }

    private fun getErrorMapping(exception:Any ): Optional<ErrorMapping> =
        this.mapping!!.toList().stream().filter(Predicate {e ->
            e.getClassException()!!.isInstance(exception)})
            .findFirst()


    private fun createResponseError(errorCode:String?, description:String?): ResponseError =
        ResponseError(errorCode, description)

}