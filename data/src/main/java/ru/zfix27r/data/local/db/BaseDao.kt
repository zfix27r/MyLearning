package ru.zfix27r.data.local.db

import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    fun insert(vararg obj: T)
}