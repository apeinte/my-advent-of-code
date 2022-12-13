package day06

import readDayInput

//Don't forget to fill this constant.
const val RESULT_EXAMPLE_PART_ONE_1 = 7
const val RESULT_EXAMPLE_PART_ONE_2 = 5
const val RESULT_EXAMPLE_PART_ONE_3 = 6
const val RESULT_EXAMPLE_PART_ONE_4 = 10
const val RESULT_EXAMPLE_PART_ONE_5 = 11
const val RESULT_EXAMPLE_PART_TWO_1 = 19
const val RESULT_EXAMPLE_PART_TWO_2 = 23
const val RESULT_EXAMPLE_PART_TWO_3 = 23
const val RESULT_EXAMPLE_PART_TWO_4 = 29
const val RESULT_EXAMPLE_PART_TWO_5 = 26
const val VALUE_OF_THE_DAY = 6

fun main() {

    fun getMarker(input: List<String>, targetLine: Int = 0, markerBeacon: Int):Int {
        var tmpInput: MutableList<String> = input[targetLine].split("").toMutableList()
        var inputLineToArray = tmpInput.apply {
            // Split are generated empty character at first and last index in the list
            removeFirst()
            removeLast()
        }
        var result = 0
        var from = 0
        var to = markerBeacon
        var charCounter = 0
        fun checkList() {

            var dataPacket = inputLineToArray.subList(from, to)

            for (char in dataPacket) {
                charCounter++
                if (dataPacket.filter { it ==char }.size > 1) {
                    from++
                    to++
                    charCounter = 0
                    checkList()
                    break
                } else {
                    if (charCounter == markerBeacon) {
                        result = to
                        break
                    }
                }
            }
        }
        checkList()

        println("The start mark for $tmpInput is $result.")
        return result
    }

    fun part1(input: List<String>, targetLine: Int = 0): Int {
        return getMarker(input, targetLine, 4)
    }

    fun part2(input: List<String>, targetLine: Int = 0): Int {
        return getMarker(input, targetLine, 14)
    }

    val inputRead = readDayInput(VALUE_OF_THE_DAY)
    val inputReadTest = readDayInput(VALUE_OF_THE_DAY, true)

    fun checkIfTestArePassedForPartOne(): Boolean {
        return part1(inputReadTest, 0) == RESULT_EXAMPLE_PART_ONE_1 &&
                part1(inputReadTest,1) == RESULT_EXAMPLE_PART_ONE_2 &&
                part1(inputReadTest,2) == RESULT_EXAMPLE_PART_ONE_3 &&
                part1(inputReadTest,3) == RESULT_EXAMPLE_PART_ONE_4 &&
                part1(inputReadTest,4) == RESULT_EXAMPLE_PART_ONE_5

    }

    fun checkIfTestArePassedForPartTwo(): Boolean {
        return part2(inputReadTest, 0) == RESULT_EXAMPLE_PART_TWO_1 &&
                part2(inputReadTest,1) == RESULT_EXAMPLE_PART_TWO_2 &&
                part2(inputReadTest,2) == RESULT_EXAMPLE_PART_TWO_3 &&
                part2(inputReadTest,3) == RESULT_EXAMPLE_PART_TWO_4 &&
                part2(inputReadTest,4) == RESULT_EXAMPLE_PART_TWO_5

    }

    if (checkIfTestArePassedForPartOne()) {
        println("How many characters need to be processed before the first start-of-packet marker is detected?\n${part1(inputRead)}")
    }

    if (checkIfTestArePassedForPartTwo()) {
        println("How many characters need to be processed before the first start-of-message marker is detected?\n${part2(inputRead)}")
    }
}


