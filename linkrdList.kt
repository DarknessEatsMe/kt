fun main() {
    val list = LinkedList<Int>()
    for (x in 1..10) {
        list.enqueue(x)
    }
    println(list)
    while (list.isNotEmpty) {
        val x = list.removeLast()
        println("Удален $x список $list")
    }
}