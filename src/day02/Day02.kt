package day02

import readDayInput
import readInput

const val OPPONENT_ROCK = "A"
const val OPPONENT_PAPER = "B"
const val OPPONENT_SCISSOR = "C"

const val MY_ROCK = "X"
const val MY_PAPER = "Y"
const val MY_SCISSORS = "Z"

enum class OpponentRockPaperScissors(val choice: String, val score: Int) {
    ROCK(OPPONENT_ROCK, 1),
    PAPER(OPPONENT_PAPER, 2),
    SCISSORS(OPPONENT_SCISSOR, 3);

    companion object {
        fun stringToMRPS(value: String): OpponentRockPaperScissors {
            return when (value) {
                ROCK.choice -> ROCK
                PAPER.choice -> PAPER
                else -> SCISSORS
            }
        }
    }
}

enum class MyRockPaperScissors(val choice: String, val score: Int) {
    ROCK(MY_ROCK, 1),
    PAPER(MY_PAPER, 2),
    SCISSORS(MY_SCISSORS, 3);

    companion object {
        fun stringToMRPS(value: String): MyRockPaperScissors {
            return when (value) {
                ROCK.choice -> ROCK
                PAPER.choice -> PAPER
                else -> SCISSORS
            }
        }
    }
}

enum class MyRoundRockPaperScissors(val choice: String) {
    WIN(MY_SCISSORS),
    DRAW(MY_PAPER),
    LOOSE(MY_ROCK);

    companion object {
        fun myChoiceToMyResult(choice: MyRockPaperScissors): MyRoundRockPaperScissors {
            return when(choice.choice) {
                WIN.choice -> WIN
                DRAW.choice -> DRAW
                else -> LOOSE
            }
        }
    }
}

fun main() {
    fun parseTournament(input: List<String>): List<RoundRockPaperScissors> {
        val result = mutableListOf<RoundRockPaperScissors>()
        input.forEach {
            val round = it.split(" ")
            val opponentChoice = round[0]
            val myChoice = round[1]
            result.add(
                RoundRockPaperScissors(
                    OpponentRockPaperScissors.stringToMRPS(opponentChoice),
                    MyRockPaperScissors.stringToMRPS(myChoice)
                )
            )
        }

        return result
    }

    fun parts1(input: List<RoundRockPaperScissors>): Int {
        var result = 0
        input.forEach {
            result += it.rockPaperScissors().myScore
        }

        return result
    }

    fun parts2(input: List<RoundRockPaperScissors>): Int {
        var result = 0

        input.forEach {
            println("----- New Round -----")
            when(MyRoundRockPaperScissors.myChoiceToMyResult(it.myChoice)) {
                MyRoundRockPaperScissors.WIN -> it.needToWin()
                MyRoundRockPaperScissors.DRAW -> it.needToDraw()
                MyRoundRockPaperScissors.LOOSE -> it.needToLoose()
            }
            result += it.rockPaperScissors().myScore
            println("----------------------")
        }

        return result
    }

    val parsedTournament = parseTournament(readDayInput(2))
    println(
        "What would your total score be if everything goes exactly according to your strategy guide?" +
            "\n${parts1(parsedTournament)}"
    )
    println("Following the Elf's instructions for the second column, what would your total score be if everything goes exactly according to your strategy guide?\n${parts2(parsedTournament)}")
}

class RoundRockPaperScissors(
    var opponentChoice: OpponentRockPaperScissors,
    var myChoice: MyRockPaperScissors
) {
    var myScore = 0
    var opponentScore = 0

    fun rockPaperScissors(): RoundRockPaperScissors {
        println("ROCK PAPER SCISSORS! $opponentChoice VS $myChoice")
        if (opponentChoice.name == myChoice.name) {
            itsADraw()
            return this
        }

        when (opponentChoice) {
            OpponentRockPaperScissors.ROCK -> {
                if (myChoice == MyRockPaperScissors.PAPER) {
                    iWin()
                } else {
                    iLoose()
                }
            }
            OpponentRockPaperScissors.PAPER -> {
                if (myChoice == MyRockPaperScissors.SCISSORS) {
                    iWin()
                } else {
                    iLoose()
                }
            }
            OpponentRockPaperScissors.SCISSORS -> {
                if (myChoice == MyRockPaperScissors.ROCK) {
                    iWin()
                } else {
                    iLoose()
                }
            }
        }
        return this
    }

    fun needToWin(): RoundRockPaperScissors {
        println("I need to win $opponentChoice VS $myChoice")
        myChoice = when(opponentChoice) {
            OpponentRockPaperScissors.ROCK -> {
                MyRockPaperScissors.PAPER
            }
            OpponentRockPaperScissors.PAPER -> {
                MyRockPaperScissors.SCISSORS
            }
            OpponentRockPaperScissors.SCISSORS -> {
                MyRockPaperScissors.ROCK
            }
        }
        println("change to $myChoice")

        return this
    }

    fun needToLoose(): RoundRockPaperScissors {
        println("I need to loose $opponentChoice VS $myChoice")
        myChoice = when(opponentChoice) {
            OpponentRockPaperScissors.ROCK -> {
                MyRockPaperScissors.SCISSORS
            }
            OpponentRockPaperScissors.PAPER -> {
                MyRockPaperScissors.ROCK
            }
            OpponentRockPaperScissors.SCISSORS -> {
                MyRockPaperScissors.PAPER
            }
        }
        println("change to $myChoice")
        return this
    }

    fun needToDraw() {
        println("I need to draw $opponentChoice VS $myChoice")
        myChoice = MyRockPaperScissors.valueOf(opponentChoice.name)
        println("change to $myChoice")
    }


    private fun itsADraw() {
        myScore = myChoice.score + 3
        opponentScore = opponentChoice.score + 3
        println("It's a draw and I get $myScore points.")
    }

    private fun iWin() {
        myScore = myChoice.score + 6
        opponentScore = opponentChoice.score
        println("I win and get $myScore points.")
    }

    private fun iLoose() {
        myScore = myChoice.score
        opponentScore = opponentChoice.score + 6
        println("I loose and get $myScore points.")
    }
}
