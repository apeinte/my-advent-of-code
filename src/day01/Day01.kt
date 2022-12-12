package day01
import readDayInput
import readInput
import kotlin.math.max

fun main() {
    /**
     * In case the Elves get hungry and need extra snacks, they need to know which Elf to ask: they'd like to know how many Calories are being carried by the Elf carrying the most Calories. In the example above, this is 24000 (carried by the fourth Elf).
     * Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
     *@param input the list of elves
     */
    fun part1(input: MutableMap<Int, List<Int>>): Int {
        var maxCarryingCalories = 0

        input.forEach {
            val maxCalorie = it.value.sum()
            maxCarryingCalories = max(maxCarryingCalories, maxCalorie)
        }

        return maxCarryingCalories
    }

    /**
     * By the time you calculate the answer to the Elves' question, they've already realized that the Elf carrying the most Calories of food might eventually run out of snacks.
     * To avoid this unacceptable situation, the Elves would instead like to know the total Calories carried by the top three Elves carrying the most Calories. That way, even if one of those Elves runs out of snacks, they still have two backups.
     * In the example above, the top three Elves are the fourth Elf (with 24000 Calories), then the third Elf (with 11000 Calories), then the fifth Elf (with 10000 Calories). The sum of the Calories carried by these three elves is 45000.
     * Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
     * @param targetTop set your top that you want to know (ex: top 10)
     * @param input the list of elves
     */
    fun part2(targetTop: Int, input: MutableMap<Int, List<Int>>): Int {
        val rankElves = input.toList().sortedBy {
            it.second.sum()
        }.reversed()

        var topThreeCalories = 0

        for (i in 1..targetTop) {
            topThreeCalories += rankElves[i - 1].second.sum()
        }

        return topThreeCalories
    }

    val input = readDayInput(1)
    val tabElves: MutableMap<Int, List<Int>> = parseInput(input)
    println("How many total Calories is that Elf carrying?\n${part1(tabElves)}")
    println("Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?\n${part2(3, tabElves)}")
}

fun parseInput(input: List<String>): MutableMap<Int, List<Int>> {
    val result = mutableMapOf<Int, List<Int>>()
    // Input = split elves
    // Need to know how many elf they are
    // Need to know who carried the most and how many

    var nbElf = 1
    var currentCaloriesCarried = mutableListOf<Int>()

    input.forEach {
        try {
            currentCaloriesCarried.add(it.toInt())
        } catch (exception: NumberFormatException) {
            // If it's not a number that's mean it's a space character so it's a new elf
            result[nbElf] = currentCaloriesCarried
            currentCaloriesCarried = mutableListOf()
            nbElf++
        }
    }
    println("They is $nbElf in the jungle.")
    return result
}
