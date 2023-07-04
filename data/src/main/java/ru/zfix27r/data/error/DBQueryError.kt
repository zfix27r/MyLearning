package ru.zfix27r.data.error

import java.lang.Exception

class DBQueryError(message: String) : Exception(message)