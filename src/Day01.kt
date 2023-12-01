fun main() {

    fun calibrateCalibrationValue(input: String): Int {
        var firstDigit = 0
        var lastDigit = 0
        var firstDigitFound = false

        for (c in input) {
            if (c.isDigit() && !firstDigitFound){
                firstDigit = c.digitToInt()
                firstDigitFound = true
            }
            if (c.isDigit()) lastDigit = c.digitToInt()
        }
        return (firstDigit * 10) + lastDigit
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { calibrateCalibrationValue(it) }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }



//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
