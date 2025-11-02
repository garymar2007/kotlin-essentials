package com.gary.com.gary.dsl

fun main() {

    // Lambda with Receiver
    val regularLambda: (StringBuilder) -> Unit = {
        it.append("Hello, ")
        it.append("World!")
    }
    val lambdaWithReceivers: StringBuilder.() -> Unit = {
        append("Hello, ")
        append("World!")
    }

    val sb = StringBuilder()
    sb.lambdaWithReceivers()
    println(sb.toString())

    // usage for dsl building blocks
    val myHtml = html {
        header()
        body()
        p {
            h1 { }
            h2 { }
        }
    }

    // usage for html dsl builder
    val myHtml1 = html1 {
        tag("head") {
            tag("title") {

            }
        }
        tag("body") {
            attr("class", "main")
            tag("h1") {
                attr("style", "color: blue")
            }
            tag("p") {
                attr("id", "paragraph1")
            }
        }
    }
    println(myHtml1)

    // usage of sql query dsl
    val myQuery = query {
        select("name", "age")
        from("users")
        where("age > 20")
        limit(10)
    }
    println(myQuery)
}

// Basic DSL Building blocks
class HTML {
    fun body() { println("Body.") }
    fun header() { println("Header.") }
    fun p(init: () -> Unit) {
        println("Paragraph.")
        init()
    }
    fun h1(init: () -> Unit) {
        println("Heading 1.")
        init()
    }
    fun h2(init: () -> Unit) {
        println("Heading 2.")
        init()
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

fun html1(init: Tag.() -> Unit): Tag {
    val tag = Tag("html")
    tag.init()
    return tag
}

// HTML DSL Builder
class Tag(val name: String) {
    private val children = mutableListOf<Tag>()
    private val attributes = mutableMapOf<String, String>()

    fun tag(name: String, init: Tag.() -> Unit) {
        val tag = Tag(name)
        tag.init()
        children.add(tag)
    }

    fun attr(key: String, value: String) {
        attributes[key] = value
    }

    override fun toString(): String {
       val attributesStr = if (attributes.isNotEmpty()) {
           " " + attributes.entries.joinToString(" ") { "${it.key}=\"${it.value}\"" }
       } else ""

       return if (children.isEmpty()) {
           "<$name$attributesStr />"
       } else {
           "<$name$attributesStr>${children.joinToString("")}</$name>"
       }
    }
}

// SQL query dsl
class QueryBuilder{
    private var selectColumns = mutableListOf<String>()
    private var fromTable: String? = null
    private var whereClause: String? = null
    private var limitValue: Int? = null

    fun select(vararg columns: String){
        selectColumns.addAll(columns)
    }

    fun from(table: String){
        fromTable = table
    }

    fun where(clause: String){
        whereClause = clause
    }

    fun limit(value: Int){
        limitValue = value
    }

    fun build(): String {
        val select = if (selectColumns.isEmpty()) "SELECT *"
            else "SELECT ${selectColumns.joinToString(", ")}"

        require(fromTable != null) { "Table must be specified" }

        val from = "FROM $fromTable"
        val where = whereClause?.let { "WHERE $whereClause" } ?: ""
        val limit = limitValue?.let { "LIMIT $limitValue" } ?: ""

        return listOf(select, from, where, limit).filter { it.isNotEmpty() }.joinToString(" ")
    }
}

fun query(init: QueryBuilder.() -> Unit): String {
    val builder = QueryBuilder()
    builder.init()
    return builder.build()
}