fun main() {

    fun validateCubes(input: String): Boolean {
        val showings = input.split(";")
        for (showing in showings) {
            val cubes = showing.split(",")
            for (cube in cubes) {
                val numberOfColor = cube.filter { it.isDigit() }.toInt()
                when {
                    cube.contains("green") -> if (numberOfColor > 13) return false
                    cube.contains("red") -> if (numberOfColor > 12) return false
                    cube.contains("blue") -> if (numberOfColor > 14) return false
                }
            }
        }
        return true
    }

    fun valueOfCubes(input: String): Int {
        var numberOfRed = 1
        var numberOfBlue = 1
        var numberOfGreen = 1
        val showings = input.split(";")
        for (showing in showings) {
            val cubes = showing.split(",")
            for (cube in cubes) {
                val numberOfColor = cube.filter { it.isDigit() }.toInt()
                when {
                    cube.contains("green") -> if (numberOfColor > numberOfGreen) numberOfGreen = numberOfColor
                    cube.contains("red") -> if (numberOfColor > numberOfRed) numberOfRed = numberOfColor
                    cube.contains("blue") -> if (numberOfColor > numberOfBlue) numberOfBlue = numberOfColor
                }
            }
        }
        return numberOfBlue * numberOfRed * numberOfGreen
    }

    fun part1(input: List<String>): Int {
        var possibleGames = 0
        for ((index, value) in input.withIndex()){
            if (validateCubes(value.split(":").last())) possibleGames += (index + 1)
        }
        return possibleGames
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { valueOfCubes(it.split(":").last()) }
    }

//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}