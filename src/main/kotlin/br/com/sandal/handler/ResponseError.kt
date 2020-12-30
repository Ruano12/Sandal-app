package br.com.sandal.handler

import com.fasterxml.jackson.annotation.JsonProperty

class ResponseError {

    @JsonProperty(value = "error-code")
    private var errorCode:String? = null

    @JsonProperty(value = "error-description")
    private var errorDescription:String? = null

    constructor(errorCode: String?, errorDescription:String?){
        this.errorCode = errorCode
        this.errorDescription =errorDescription
    }

    fun getErrorCode():String? = this.errorCode

    fun getErrorDescription():String? = this.errorDescription
}