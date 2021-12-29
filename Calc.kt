import kotlin.math.*

fun main() {
    val input = readLine().toString()
    val regex = "-?(\\d+\\.)?\\d+|\\(|\\)|\\+|\\-|\\*|\\/|\\^|(sin)".toRegex()
    val infix = regex.findAll(input).map { it.value }.toList()
    println(infix)
    val stack = mutableListOf<String>()
    val list = mutableListOf<String>()
    for (s in infix) {
        when (s) {
            "+", "-", "*", "/", "sin", "^" -> {
                if (stack.size == 0){
                    stack.add(s)
                } else {
                    while (stack.size!=0 && priority(s) <= priority(stack.last())) {
                        list.add(stack.last())
                        stack.removeLast()
                    }
                    stack.add(s)
                }
            }
            "(" -> {
                stack.add(s)
            }
            ")" -> {
                while (stack.last() != "(") {
                    list.add(stack.last())
                    stack.removeLast()
                }
                stack.removeLast()
            }
            else -> {
                list.add(s)
            }
        }
    }
    while (stack.size != 0) {
        list.add(stack.last())
        stack.removeLast()
    }
    println(list)
    println(calcPost(list))
}

fun calcPost(list: MutableList<String>): MutableList<Float> {
    val stack = mutableListOf<Float>()
    var result: Float
    var buffer: Float

    for (s in list) {
        when(s) {
            "+" -> {
                result = stack.removeLast()
                result += stack.removeLast()
                stack.add(result)
            }

            "-" -> {
                buffer = stack.removeLast()
                result = stack.removeLast() - buffer
                stack.add(result)
            }

            "*" -> {
                result = stack.removeLast()
                result *= stack.removeLast()
                stack.add(result)
            }

            "/" -> {
                buffer = stack.removeLast()
                result = stack.removeLast() / buffer
                stack.add(result)
            }

            "^" -> {
                buffer = stack.removeLast()
                result = stack.removeLast().pow(buffer)
                stack.add(result)
            }

            "sin" -> {
                result = sin(stack.removeLast())
                stack.add(result)
            }

            else -> {
                stack.add(s.toFloat())
            }
        }
    }

    println(stack[0])

    return stack
}

fun priority(s: String): Int {
    return when(s) {
        "+", "-" -> {
            1
        }
        "*", "/" -> {
            2
        }
        "sin", "^" -> {
            3
        }
        else -> {
            0
        }

    }
}