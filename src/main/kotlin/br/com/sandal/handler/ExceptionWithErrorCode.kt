package br.com.sandal.handler

import org.springframework.http.HttpStatus

interface ExceptionWithErrorCode {
    fun getErrorCode():String
    fun getHttpStatus():HttpStatus
}