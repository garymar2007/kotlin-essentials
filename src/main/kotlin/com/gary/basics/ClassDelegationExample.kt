package com.gary.com.gary.basics

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print("This is Base implementation: $x") }
}

class Derived(b: Base) : Base by b

// Why using class delegation?
// 1. Avoid code duplication
// 2. Encapsulate implementation
// 3. Be able to override only a subset of the behavior
// 4. Alternative to inheritance

interface Repository {
    fun getAll(): List<String>
    fun getById(id: Int): String?
}

class DatabaseRepository : Repository {
    override fun getAll(): List<String> {
        return listOf("Item 1", "Item 2", "Item 3")
    }
    override fun getById(id: Int): String? = if (id == 1) "Item 1" else null
}

class CachedRepository(private val repository: Repository) : Repository by repository {
    private val cache = mutableMapOf<Int, String?>()

    override fun getById(id: Int): String? {
        return cache.getOrPut(id) {
            repository.getById(id)
        }
    }
}


fun main() {
    val b = BaseImpl(10)
    Derived(b).print()

    val dbRepo = DatabaseRepository()
    val cachedRepo = CachedRepository(dbRepo)

    println(cachedRepo.getAll()) // Delegated to DatabaseRepository
    println(cachedRepo.getById(1)) // Uses cache + delegation
}