package br.com.saar.util

import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class Assert {

    companion object {
        fun notNullOrEmpty(obj:String?):Boolean {
            if(Objects.isNull(obj)){
                return false
            }

            if(obj!!.trim().equals("")){
                return false
            }

            return true
        }
    }
}