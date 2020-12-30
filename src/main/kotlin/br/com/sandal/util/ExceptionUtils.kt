package br.com.saar.util

import java.util.regex.Matcher
import java.util.regex.Pattern


class ExceptionUtils {

    companion object {
        fun getHintMessage(rootCause:Throwable):String? {
            val pattern:Pattern = Pattern.compile("\\Hint:(.*?\\n)", Pattern.DOTALL)
            val matcher:Matcher = pattern.matcher(rootCause.message)
            if(matcher.find()){
                return matcher.group(1).strip()
            }
            return rootCause.message
        }
    }

}