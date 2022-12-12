package day05

import readDayInput

//Don't forget to fill this constant.
private const val RESULT_EXAMPLE_PART_ONE = "[C][M][Z]"
private const val RESULT_EXAMPLE_PART_TWO = "[M][C][D]"
private const val VALUE_OF_THE_DAY = 5

fun main() {

    fun getCargoContent(inputs: List<String>): MutableMap<Int, MutableList<String>> {
        val cargoContent = mutableMapOf<Int, MutableList<String>>()
        for(line in inputs) {
            if (line.isEmpty()) {
                // next line is movement for the crane
                break
            }
            var emptyCounter = 0
            var contentCounter = 0
            var content = ""

            for(char in line.split(" ")) {

                try {
                    char.toInt()
                    break
                } catch (exception: NumberFormatException) {
                   fun resetVar() {
                        emptyCounter = 0
                        content = ""
                    }

                    if (char.isBlank()) {
                        emptyCounter++
                        if (emptyCounter == 4) {
                            contentCounter++
                            if (cargoContent[contentCounter] != null) {
                                cargoContent[contentCounter]?.add(content)
                            }
                            resetVar()
                        }
                    } else {
                        content += char
                        contentCounter++
                        if (cargoContent[contentCounter] != null) {
                            cargoContent[contentCounter]?.add(content)
                        } else {
                            cargoContent[contentCounter] = mutableListOf(content)
                        }
                        resetVar()
                    }
                }
            }
        }

        val reversedList = mutableMapOf<Int, MutableList<String>>()
        cargoContent.forEach {
            reversedList[it.key] = it.value.reversed().toMutableList()
        }


        return reversedList.toSortedMap()
    }

    fun getCargoMovements(inputs: List<String>): MutableList<String> {
        val cargoMovements = mutableListOf<String>()
        var isMovements = false
        var counterMovement = 0
        for(line in inputs) {
            if (line.isBlank()) {
                // next line is movement for the crane
                isMovements = true
            } else {
                if (isMovements) {
                    counterMovement++
                    cargoMovements.add(line.replace("move ", "").replace(" from ", "|").replace(" to ", "|"))
                }
            }
        }
        return cargoMovements
    }

    fun part1(input: List<String>): String {
        var result = ""
        var cargoContent = getCargoContent(input)
        var cargoCraneScript = getCargoMovements(input)

        cargoCraneScript.forEach { craneMovement ->
            // Parse order
            val parseMovement = craneMovement.split("|")
            val move = parseMovement[0].toInt()
            val from = parseMovement[1].toInt()
            val to = parseMovement[2].toInt()
            // Select content
            val contentToMove = mutableListOf<String>()
            var moveCounter = 0
            //from
            for (crate in cargoContent[from]!!.reversed()) {
                if (moveCounter == move) {
                    break
                } else {
                    contentToMove.add(crate)
                }
                moveCounter++
            }
            //to
            println("Move $move from $from to $to")
            contentToMove.reversed().forEach {
                println("Remove $it from container $from")
                cargoContent[from]?.apply {
                    reverse()
                    remove(it)
                    reverse()
                }
            }
            println("Add ${contentToMove.size} element in list $to")
            cargoContent[to]?.addAll(contentToMove)
        }

        cargoContent.forEach {
            if (it.value.isNotEmpty()) {
                result += it.value.last()
            }
        }

        return result
    }

    fun part2(input: List<String>): String {
        var result = ""
        var cargoContent = getCargoContent(input)
        var cargoCraneScript = getCargoMovements(input)

        cargoCraneScript.forEach { craneMovement ->
            // Parse order
            val parseMovement = craneMovement.split("|")
            val move = parseMovement[0].toInt()
            val from = parseMovement[1].toInt()
            val to = parseMovement[2].toInt()
            // Select content
            val contentToMove = mutableListOf<String>()
            var moveCounter = 0
            //from
            for (crate in cargoContent[from]!!.reversed()) {
                if (moveCounter == move) {
                    break
                } else {
                    contentToMove.add(crate)
                }
                moveCounter++
            }
            //to
            println("Move $move from $from to $to")
            contentToMove.forEach {
                println("Remove $it from container $from")
                cargoContent[from]?.apply {
                    reverse()
                    remove(it)
                    reverse()
                }
            }
            println("Add ${contentToMove.size} element in list $to")
            cargoContent[to]?.addAll(contentToMove.reversed())
        }

        cargoContent.forEach {
            if (it.value.isNotEmpty()) {
                result += it.value.last()
            }
        }

        return result
    }

    val inputRead = readDayInput(VALUE_OF_THE_DAY)
    val inputReadTest = readDayInput(VALUE_OF_THE_DAY, true)

    if (part1(inputReadTest) == RESULT_EXAMPLE_PART_ONE) {
        println("With CraneMover9000 and after the rearrangement procedure completes, what crate ends up on top of each stack?\n${part1(inputRead)}")
    }

    if (part2(inputReadTest) == RESULT_EXAMPLE_PART_TWO) {
        println("With CraneMover9001 and after the rearrangement procedure completes, what crate ends up on top of each stack?\n${part2(inputRead)}")
    }
}
