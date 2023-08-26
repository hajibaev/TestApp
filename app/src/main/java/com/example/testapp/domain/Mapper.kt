package com.example.testapp.domain

interface Mapper<From, To> {
    fun map(from: From): To
}