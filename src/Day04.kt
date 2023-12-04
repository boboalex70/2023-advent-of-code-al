import kotlin.math.pow

fun main() {

    fun parseNumWinnersFromTicket(input: String): Int {
        var numWinners = 0
        val winningNumberString = input.split("|").first()
        val playNumbers = input.split("|").last()
        val winningNumberSet = HashSet(winningNumberString.split(" ").filter { it != "" }.map { it.toInt() })
        for (playNumber in playNumbers.split(" ").filter { it != "" }.map { it.toInt() }) {
            if (winningNumberSet.contains(playNumber)) {
                numWinners++
            }
        }
        return numWinners
    }

    fun parseWinnerFromTicket(input: String): Int {
        // I would refactor this function and remove it but not prod code
        var numWinners = -1
        val baseNumber = 2
        val winningNumberString = input.split("|").first()
        val playNumbers = input.split("|").last()
        val winningNumberSet = HashSet(winningNumberString.split(" ").filter { it != "" }.map { it.toInt() })
        for (playNumber in playNumbers.split(" ").filter { it != "" }.map { it.toInt() }) {
            if (winningNumberSet.contains(playNumber)) {
                numWinners++
            }
        }
        if (numWinners >= 0) return baseNumber.toDouble().pow(numWinners).toInt()
        return 0
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { parseWinnerFromTicket(it.split(":").last()) }
    }

    fun part2(input: List<String>): Int {
        var winningCards = 0
        val cardsAndCopies = input.mapIndexed { index, s ->  index to 1}.toMap().toMutableMap()
        for (gameNumber in input.indices){
            val parseNumWinners = parseNumWinnersFromTicket(input[gameNumber].split(":").last())
            if (parseNumWinners > 0) {
                winningCards += cardsAndCopies[gameNumber]!! * parseNumWinners
                for (i in 1..parseNumWinners) {
                    val nextGame = gameNumber + i
                    val incrementGameNums = cardsAndCopies[nextGame]?.plus(cardsAndCopies[gameNumber]!!)
                    cardsAndCopies.set(nextGame, incrementGameNums!!)
                }
            }
        }
        return winningCards
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}