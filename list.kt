class LinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override fun toString(): String = if(head == null) "empty list" else head.toString()
    /** Добавление элемента в голову связного списка. */
    fun push(value: T) {
        val node = Node(value)
        head?.prev = node
        node.next = head
        head = node
        node.prev = null
        if (tail == null) {
            tail = node
        }
    }

    /** Удаление элемента из головы связного списка. */
    fun pop(): T? {
        val value = head?.value
        head = head?.next
        head?.prev = null
        if (head == null) {
            tail = null
        }
        return value
    }

    fun dequeue(): T? = pop()

    fun enqueue(value: T) {
        if(tail == null) {
            push(value)
        } else {
            val node = Node(value)
            node.prev = tail
            tail?.next = node // можно написать вместо ? - !!, это значит выполнить команду в любом случае (не стоит так делать)
            tail = node
        }
    }
    fun peek(): T? {
        val value = head?.value
        return value
    }

    fun removeLast(): T? {
        val value = tail?.value
        tail = tail?.prev
        tail?.next = null
        if (tail == null) {
            head = null
        }
        return value
    }

    val isEmpty: Boolean get() = peek() == null

    val isNotEmpty: Boolean get() = !isEmpty
}