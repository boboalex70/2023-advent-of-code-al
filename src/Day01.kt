fun main() {
    //naive solution
//    fun calibrateCalibrationValue(input: String): Int {
//        var firstDigit = 0
//        var lastDigit = 0
//        var firstDigitFound = false
//
//        for (c in input) {
//            if (c.isDigit() && !firstDigitFound){
//                firstDigit = c.digitToInt()
//                firstDigitFound = true
//            }
//            if (c.isDigit()) lastDigit = c.digitToInt()
//        }
//        return (firstDigit * 10) + lastDigit
//    }

    fun testRegexWithDigit(input: String): Int {
        val regex = "\\d".toRegex()
        val matchResults = regex.findAll(input).toList().map { res -> res.value[0].digitToInt() }
        return (matchResults[0] * 10) + matchResults[matchResults.size - 1]
    }

    fun stringNumberToStringNum(input: String): Int {
        return when (input) {
            "one" -> 1
            "two" -> 2
            "three" -> 3
            "four" -> 4
            "five" -> 5
            "six" -> 6
            "seven" -> 7
            "eight" -> 8
            "nine" -> 9
            else -> input.toInt()
        }
    }

    fun incorporateStringNames(input: String): Int {
        /*
        Modified from https://github.com/valiant-code/advent-of-code/blob/main/src/main/kotlin/aoc2023/Day1.kt

        Since I was trying a new language as metioned in Valiant Code's reddit post, did not know about Kotlin Regex quirks
        Had the right idea but had to reach out for help
         */
//        val regex = "\\d|one|two|three|four|five|six|seven|eight|nine|".toRegex()
//        val matchResults = regex.findAll(input).toList().map { res -> stringNumberToStringNum(res.value) }.map { res -> res[0].digitToInt() }
//        return (matchResults[0] * 10) + matchResults[matchResults.size - 1]
        val numPattern = Regex("\\d|one|two|three|four|five|six|seven|eight|nine")
        val numPatternBackwards = Regex("enin|thgie|neves|xis|evif|ruof|eerht|owt|eno|\\d")
        val numbers = listOf(stringNumberToStringNum(numPattern.find(input)!!.value), stringNumberToStringNum(numPatternBackwards.find(input.reversed())!!.value.reversed()))
        return (numbers.first() * 10) + numbers.last()
    }



    fun part1(input: List<String>): Int {
        return input.sumOf { testRegexWithDigit(it) }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { incorporateStringNames(it) }
    }



//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
