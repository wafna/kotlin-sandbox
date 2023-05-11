package contexts

// The services.

interface Herp {
    fun herp()
}

interface Derp {
    fun derp()
}

// Consumers of the individual services.

context(Herp)
fun withHerp() {
    herp()
}

context(Derp)
fun withDerp() {
    derp()
}

// Consumer of the combined services.
// By making the union of the types, we can use methods that only rely on a single element of the union.
// This only works with interfaces. If we had used classes, we would have to delegate each method individually.
class HerpDerp(val herp: Herp, val derp: Derp) :
    Herp by herp,
    Derp by derp

context(Herp, Derp)
fun withHerpDerp1() {
    herp()
    derp()
}

context(HerpDerp)
fun withHerpDerp2() {
    herp()
    derp()
}

fun main() {
    val herp = object : Herp {
        override fun herp() {
            println("herp")
        }
    }
    val derp = object : Derp {
        override fun derp() {
            println("derp")
        }
    }
    with(HerpDerp(herp, derp)) {
        println("-- herp")
        withHerp()
        println("-- derp")
        withDerp()
        println("-- herp derp 1")
        withHerpDerp1()
        println("-- herp derp 2")
        withHerpDerp2()
    }
}
