package br.com.sandal.handler

import br.com.sandal.handler.ErrorMapping

interface ErrorMappingAware {

    fun getMapping():Array<ErrorMapping>?
}