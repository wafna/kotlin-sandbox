package higher_order_contexts

interface MyContext {
    fun boom()
}

// Now, it becomes clear that the context for f has absolutely nothing to do with this function.
fun contextualizedHigherOrderFunction(f: () -> Unit) {
    f()
}

context(MyContext)
fun thingy() {
    boom()
}

fun main() {
    val myContext = object : MyContext {
        override fun boom() {
            println("boom")
        }
    }
    with(myContext) {
        contextualizedHigherOrderFunction { thingy() }
        println("out go the lights")
    }
}
