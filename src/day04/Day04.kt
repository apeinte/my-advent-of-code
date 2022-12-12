const val RESULT_EXAMPLE_PART_ONE = 2
const val RESULT_EXAMPLE_PART_TWO = 4
const val VALUE_OF_THE_DAY = 4

fun main() {

    fun parseSectionIntoListInt(section: String): List<Int> {
        val result = mutableListOf<Int>()
        section.split("-").forEach {
            result.add(it.toInt())
        }
        return result
    }

    fun isFullyContainInTheSectionPair(sectionOne: List<Int>, sectionTwo: List<Int>): Boolean {
        return (sectionOne[0] in sectionTwo[0]..sectionTwo[1] &&
                sectionOne[1] in sectionTwo[0]..sectionTwo[1]) ||
                (sectionTwo[0] in sectionOne[0]..sectionOne[1] &&
                        sectionTwo[1] in sectionOne[0]..sectionOne[1])
    }

    fun isOverlappingTheSection(sectionNumber: Int, section: List<Int>): Boolean =
        sectionNumber in section[0]..section[1]


    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { sectionAssignment ->
            val sectionSplit = sectionAssignment.split(",")
            val sectionOne = parseSectionIntoListInt(sectionSplit[0])
            val sectionTwo = parseSectionIntoListInt(sectionSplit[1])
            if (isFullyContainInTheSectionPair(sectionOne, sectionTwo)) {
                result++
            }

        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        input.forEach { sectionAssignment ->
            val sectionSplit = sectionAssignment.split(",")
            val sectionOne = parseSectionIntoListInt(sectionSplit[0])
            val sectionTwo = parseSectionIntoListInt(sectionSplit[1])
            var counterOverlapping = 0
            sectionOne.forEach { numberOfTheSection ->
                if (isOverlappingTheSection(numberOfTheSection, sectionTwo)) {
                    counterOverlapping++
                }
            }
            sectionTwo.forEach { numberOfTheSection ->
                if (isOverlappingTheSection(numberOfTheSection, sectionOne)) {
                    counterOverlapping++
                }
            }
            if (counterOverlapping > 0) {
                result++
            }
        }
        return result
    }

    val inputRead = readDayInput(VALUE_OF_THE_DAY)
    val inputReadTest = readDayInput(VALUE_OF_THE_DAY, true)

    if (part1(inputReadTest) == RESULT_EXAMPLE_PART_ONE) {
        println(
            "In how many assignment pairs does one range fully contain the other?\n${
                part1(inputRead)
            }"
        )
    }

    if (part2(inputReadTest) == RESULT_EXAMPLE_PART_TWO) {
        println("In how many assignment pairs do the ranges overlap?\n${part2(inputRead)}")
    }
}
