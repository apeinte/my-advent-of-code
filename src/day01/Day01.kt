//Don't forget to fill this constant.
const val RESULT_EXAMPLE_PART_ONE = 0
const val RESULT_EXAMPLE_PART_TWO = 0
const val VALUE_OF_THE_DAY = 0

fun main() {

    fun part1(input: List<String>): Int {
        var result = 0

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
