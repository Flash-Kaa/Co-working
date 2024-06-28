package com.na.coworking.data

import com.na.coworking.domain.interfaces.Repository

class RepositoryImpl: Repository {
    override fun get(): List<Int> {
        return listOf(1, 2, 3)
    }
}