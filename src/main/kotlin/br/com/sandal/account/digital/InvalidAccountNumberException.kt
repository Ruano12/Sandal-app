package br.com.sandal.account.digital

class InvalidAccountNumberException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(cause: Throwable?) : super(cause) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(
        message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(message, cause, enableSuppression, writableStackTrace) {
    }
}