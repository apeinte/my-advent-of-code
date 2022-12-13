package day07

import readDayInput

//Don't forget to fill this constant.
const val RESULT_EXAMPLE_PART_ONE = 95437
const val RESULT_EXAMPLE_PART_TWO = 0
const val VALUE_OF_THE_DAY = 7

fun main() {

    fun part1(input: List<String>): Int {
        var result = 0
        var lastCommand = ""

        input.forEach {
            if (it.contains("$")) {
                // It's a command
                lastCommand = it
            } else {
                // It's result of command
                if (lastCommand.contains("ls")) {

                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        return result
    }

    val inputRead = readDayInput(VALUE_OF_THE_DAY)
    val inputReadTest = readDayInput(VALUE_OF_THE_DAY, true)

    if (part1(inputReadTest) == RESULT_EXAMPLE_PART_ONE) {
        println("${part1(inputRead)}")
    }

    if (part2(inputReadTest) == RESULT_EXAMPLE_PART_TWO) {
        println("${part2(inputRead)}")
    }
}
