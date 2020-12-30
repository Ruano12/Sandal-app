package br.com.sandal.handler

import org.springframework.http.HttpStatus

class ErrorMapping {

    private var classException:Class<out Exception?>? = null
    private var httpsStatus:HttpStatus? = null
    private var errorCode:String? = null

    constructor(errorCode:String?, classException:Class<out Exception?>?, httpsStatus:HttpStatus?){
        this.classException = classException
        this.httpsStatus = httpsStatus
        this.errorCode = errorCode
    }

    fun getClassException(): Class<out Exception?>? = this.classException

    fun getHttpStatus(): HttpStatus? = this.httpsStatus

    fun getErrorCode(): String? = this.errorCode
}