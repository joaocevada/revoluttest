package com.app.juawcevada.revoluttest.shared

abstract class Mapper<in E, T> {
    abstract fun mapFrom(from: E): T
}