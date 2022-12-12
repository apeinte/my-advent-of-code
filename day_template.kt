//TODO: Don't forget to fill this constant.
const val RESULT_EXAMPLE_PART_ONE = 0
const val RESULT_EXAMPLE_PART_TWO = 0
const val VALUE_OF_THE_DAY = "Day"
const val VALUE_OF_THE_DAY_EXAMPLE = "Day_test"

fun main() {

    fun part1(input: List<String>): Int {

        return 0
    }

    fun part2(input: List<String>): Int {

        return 0
    }

    val inputRead = readInput(VALUE_OF_THE_DAY)
    val inputReadTest = readInput(VALUE_OF_THE_DAY_EXAMPLE)

    if (part1(inputReadTest) == RESULT_EXAMPLE_PART_ONE) {
        println("${part1(inputRead)}")
    }

    if (part2(inputReadTest) == RESULT_EXAMPLE_PART_TWO) {
        println("${part2(inputRead)}")
    }
}
