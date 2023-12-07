fun main() {

    fun winningTimes(time: Long, recordDistance: Long): Int {
        var winningTimes = 0
        for (i in 1..<time) {
            val timeLeft = time - i
            if ((i * timeLeft) > recordDistance) winningTimes++
        }
        return winningTimes
    }

    fun winningTimes(time: Int, recordDistance: Int): Int {
        var winningTimes = 0
        for (i in 1..<time) {
            val timeLeft = time - i
            if ((i * timeLeft) > recordDistance) winningTimes++
        }
        return winningTimes
    }

    fun part1(input: List<String>): Int {
        val times = input[0].split(":").last().split(" ").filter { it != "" }.map { it.toInt() }
        val recordDistances = input[1].split(":").last().split(" ").filter { it != "" }.map { it.toInt() }
        var powerSum = 1
        for (i in times.indices) {
            powerSum *= winningTimes(times[i], recordDistances[i])
        }
        return powerSum
    }

    fun part2(input: List<String>): Int {
        val totalTIme = input[0].split(":").last().split(" ").filter { it != "" }.joinToString("") { it }.toLong()
        val totalDistance = input[1].split(":").last().split(" ").filter { it != "" }.joinToString("") { it }.toLong()
        return winningTimes(totalTIme, totalDistance)
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}