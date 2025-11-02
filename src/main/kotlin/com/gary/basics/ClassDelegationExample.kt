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

// Decorator Pattern
interface TextProcessor {
    fun process(text: String): String
}

class BasicTextProcessor : TextProcessor {
    override fun process(text: String): String = text.trim()
}

class CapitalizingProcessor(private val textProcessor: TextProcessor) : TextProcessor by textProcessor {
    override fun process(text: String): String = textProcessor.process(text).uppercase()
}

class ReversingProcessor(private val textProcessor: TextProcessor) : TextProcessor by textProcessor {
    override fun process(text: String): String = textProcessor.process(text).reversed()
}

// Property delegation with custom logic
interface UserService {
    fun getUser(id: Int): String?
    fun saveUser(id: Int, user: String)
}

class DefaultUserService : UserService {
    private val users = mutableMapOf<Int, String>()
    override fun getUser(id: Int): String? = users[id]
    override fun saveUser(id: Int, name: String) {
        users[id] = name
        println("User Id:$id saved as $name")
    }
}

class LoggingUserService(private val userService: UserService) : UserService by userService {
    override fun getUser(id: Int): String? {
        println("Getting user $id")
        val result = userService.getUser(id)
        println("Result $result")
        return result
    }

    override fun saveUser(id: Int, name: String) {
        println("Saving user $id")
        userService.saveUser(id, name)
        println("Saved user $id")
    }
}

// Combining multiple delegates
interface Reader {
    fun read(): String
}

interface Writer {
    fun write(text: String)
}

class FileReader : Reader {
    override fun read(): String = "File content"
}

class ConsoleWriter : Writer {
    override fun write(text: String) = println("Writing: $text in console")
}

class ReadWriterService(reader: Reader, writer: Writer) : Reader by reader, Writer by writer {
    fun copy() {
        val content = read()
        write(content)
    }
}

// Delegate with custom scope
interface DataSource {
    fun fetchData(): List<String>
}

class NetworkDataSource : DataSource {
    override fun fetchData(): List<String> {
        println("Fetching data from network...")
        return listOf("Network Item 1", "Network Item 2", "Network Item 3")
    }
}

class CachedDataSource(private val dataSource: DataSource) : DataSource by dataSource {
    private var cachedData: List<String>? = null

    override fun fetchData(): List<String> {
        if (cachedData == null) {
            println("Cached data is empty, fetching data from network...")
            return dataSource.fetchData()
        }

        return cachedData!!
    }

    fun clearCache() {
        cachedData = null
        println("Cache cleared")
    }
}

// Delegate with lazy initialization
 class LazyCachedDataSource(dataSource: () -> DataSource) : DataSource by lazy(dataSource).value


fun main() {
    val b = BaseImpl(10)
    Derived(b).print()

    val dbRepo = DatabaseRepository()
    val cachedRepo = CachedRepository(dbRepo)

    println(cachedRepo.getAll()) // Delegated to DatabaseRepository
    println(cachedRepo.getById(1)) // Uses cache + delegation

    val basicTextProcessor = BasicTextProcessor()
    println(CapitalizingProcessor(basicTextProcessor).process("  hello  "))
    println(ReversingProcessor(basicTextProcessor).process("  hello  "))

    val userService = DefaultUserService()
    val loggingUserService = LoggingUserService(userService)
    loggingUserService.saveUser(1, "Gary")
    loggingUserService.getUser(1)
    loggingUserService.saveUser(2, "Carol")

    val reader = FileReader()
    val writer = ConsoleWriter()
    ReadWriterService(reader, writer).copy()

}