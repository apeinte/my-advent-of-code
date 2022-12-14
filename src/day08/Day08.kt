package day08

import readDayInput

//Don't forget to fill this constant.
const val RESULT_EXAMPLE_PART_ONE = 21
const val RESULT_EXAMPLE_PART_TWO = 8
const val VALUE_OF_THE_DAY = 8

fun main() {

    fun getNumberOfTreeVisibleLeftOrRight(
        treesOnTheDirection: List<Char>,
        treeHeight: Int,
        includedTallerTree: Boolean = false
    ): Int {
        var counterTreeVisibleBeforeTallerTree = 0
        for ((i:Int, treeOnTheDirection)in treesOnTheDirection.withIndex()) {
            if (treeOnTheDirection.toString().toInt() >= treeHeight) {
                break
            } else {
                if (i + 1 == treesOnTheDirection.size) {
                    break
                }
                counterTreeVisibleBeforeTallerTree++
            }
        }

        if (includedTallerTree) {
            counterTreeVisibleBeforeTallerTree++
        }

        return counterTreeVisibleBeforeTallerTree
    }

    fun getNumberOfTreeVisibleTopOrBottom(
        treesOnTheDirection: List<String>,
        treePosition: Int,
        treeHeight: Int,
        includedTallerTree: Boolean = false
    ): Int {
        var counterTreeVisibleBeforeTallerTree = 0
        for ((i: Int, treeOnTop) in treesOnTheDirection.withIndex()) {
            val treeOnTopOnTheSameColumn = treeOnTop.toList()[treePosition].toString().toInt()
            if (treeOnTopOnTheSameColumn >= treeHeight) {
                break
            } else {
                if (i + 1 == treesOnTheDirection.size) {
                    break
                }
                counterTreeVisibleBeforeTallerTree++
            }
        }

        if (includedTallerTree) {
            counterTreeVisibleBeforeTallerTree++
        }

        return counterTreeVisibleBeforeTallerTree
    }

    fun countLeft(lineOfTrees: String, treePosition: Int, treeHeight: Int): Int {
        val treesOnLeft = lineOfTrees.toList().subList(0, treePosition)
        return getNumberOfTreeVisibleLeftOrRight(treesOnLeft.reversed(), treeHeight, true)
    }


    fun countRight(lineOfTrees: String, treePosition: Int, treeHeight: Int): Int {
        val treesOnRight = lineOfTrees.toList().subList(treePosition + 1, lineOfTrees.lastIndex + 1)
        return getNumberOfTreeVisibleLeftOrRight(treesOnRight, treeHeight, true)
    }

    fun countTop(input: List<String>, currentIndex: Int, treePosition: Int, treeHeight: Int): Int {
        val treesOnTop = input.subList(0, currentIndex)
        return getNumberOfTreeVisibleTopOrBottom(treesOnTop.reversed(), treePosition, treeHeight, true)
    }

    fun countBottom(
        input: List<String>,
        currentIndex: Int,
        treePosition: Int,
        treeHeight: Int
    ): Int {
        val treesOnBottom = input.subList(currentIndex + 1, input.lastIndex + 1)
        return getNumberOfTreeVisibleTopOrBottom(treesOnBottom, treePosition, treeHeight, true)
    }

    fun checkLeft(lineOfTrees: String, treePosition: Int, treeHeight: Int): Boolean {
        val treesOnLeft = lineOfTrees.toList().subList(0, treePosition)
        return getNumberOfTreeVisibleLeftOrRight(treesOnLeft, treeHeight) == treesOnLeft.size
    }

    fun checkRight(lineOfTrees: String, treePosition: Int, treeHeight: Int): Boolean {
        val treesOnRight = lineOfTrees.toList().subList(treePosition + 1, lineOfTrees.lastIndex + 1)
        return getNumberOfTreeVisibleLeftOrRight(treesOnRight, treeHeight) == treesOnRight.size
    }

    fun checkTop(
        input: List<String>,
        currentIndex: Int,
        treePosition: Int,
        treeHeight: Int
    ): Boolean {
        val treesOnTop = input.subList(0, currentIndex)
        return getNumberOfTreeVisibleTopOrBottom(
            treesOnTop,
            treePosition,
            treeHeight
        ) == treesOnTop.size
    }

    fun checkBottom(
        input: List<String>,
        currentIndex: Int,
        treePosition: Int,
        treeHeight: Int
    ): Boolean {
        val treesOnBottom = input.subList(currentIndex + 1, input.lastIndex + 1)
        return getNumberOfTreeVisibleTopOrBottom(
            treesOnBottom,
            treePosition,
            treeHeight
        ) == treesOnBottom.size
    }


    fun thisTreeIsVisibleOnTheColumn(
        input: List<String>,
        currentIndex: Int,
        treePosition: Int,
        treeHeight: Int
    ): Boolean {
        return checkTop(input, currentIndex, treePosition, treeHeight) ||
                checkBottom(input, currentIndex, treePosition, treeHeight)
    }

    fun thisTreeIsVisibleOnTheRaw(
        lineOfTrees: String,
        treePosition: Int,
        treeHeight: Int
    ): Boolean {
        return checkLeft(lineOfTrees, treePosition, treeHeight) ||
                checkRight(lineOfTrees, treePosition, treeHeight)
    }

    fun part1(input: List<String>): Int {
        var result = 0
        input.forEachIndexed { index, lineOfTrees ->
            when (index) {
                0 -> {
                    result += lineOfTrees.length
                }
                input.size -> {
                    result += lineOfTrees.length
                }
                else -> {
                    lineOfTrees.forEachIndexed { treePosition, tree ->
                        val treeHeight = tree.toString().toInt()
                        when (treePosition) {
                            0 -> {
                                result++
                            }
                            lineOfTrees.length - 1 -> {
                                result++
                            }
                            else -> {
                                if (thisTreeIsVisibleOnTheRaw(
                                        lineOfTrees,
                                        treePosition,
                                        treeHeight
                                    ) ||
                                    thisTreeIsVisibleOnTheColumn(
                                        input,
                                        index,
                                        treePosition,
                                        treeHeight
                                    )
                                ) {
                                    result++
                                }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        input.forEachIndexed { index, lineOfTrees ->
            lineOfTrees.forEachIndexed { treePosition, tree ->
                val treeHeight = tree.toString().toInt()

                val resultLeft = if (treePosition == 0) {
                    0
                } else {
                    countLeft(lineOfTrees, treePosition, treeHeight)
                }
                val resultRight = if (treePosition == lineOfTrees.length - 1)  {
                    0
                } else {
                    countRight(lineOfTrees, treePosition, treeHeight)
                }
                val resultBottom = if (index == input.lastIndex) {
                    0
                } else {
                    countBottom(input, index, treePosition, treeHeight)
                }
                val resultTop = if (index == 0) {
                    0
                } else {
                    countTop(input, index, treePosition, treeHeight)
                }

                val calculatedTreeSpot = resultTop * resultLeft * resultBottom * resultRight

                if (calculatedTreeSpot > result) {
                    result = calculatedTreeSpot
                }
            }
        }
        return result
    }

    val inputRead = readDayInput(VALUE_OF_THE_DAY)
    val inputReadTest = readDayInput(VALUE_OF_THE_DAY, true)
    var testCheck = part1(inputReadTest)
    if (testCheck == RESULT_EXAMPLE_PART_ONE) {
        println("how many trees are visible from outside the grid?\n${part1(inputRead)}")
    } else {
        println("Test failed!\n- Required: $RESULT_EXAMPLE_PART_ONE\n- Found: $testCheck")
    }

    testCheck = part2(inputReadTest)
    if (testCheck == RESULT_EXAMPLE_PART_TWO) {
        println("What is the highest scenic score possible for any tree?\n${part2(inputRead)}")
    } else {
        println("Test failed!\n- Required: $RESULT_EXAMPLE_PART_TWO\n- Found: $testCheck")
    }
}
