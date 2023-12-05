data class Transform(val destStart: Long, val srcStart: Long, val length: Long)

fun main() {
    fun part1(input: List<String>): Long {
        val seeds = input[0].split(":").last().split(" ").filter { it != "" }.map { it.toLong() }.toMutableList()
        val currentTransforms = mutableListOf<Transform>()
        var parseLine = 3
        while (parseLine < input.size) {
            if (input[parseLine].isEmpty() || parseLine == 242) {
                for (i in seeds.indices) {
                    for (transform in currentTransforms) {
                        val transformRange = transform.srcStart..<(transform.srcStart + transform.length)
                        if (transformRange.contains(seeds[i])) {
                            if (transform.srcStart > transform.destStart) {
                                seeds[i] = seeds[i] - (transform.srcStart - transform.destStart)
                            } else {
                                seeds[i] = seeds[i] + (transform.destStart - transform.srcStart)
                            }
                            break
                        }
                    }
                }
                parseLine+=2
                currentTransforms.clear()
                continue
            }
            if (parseLine == 240){
                println("got here")
            }
            val parseTransformLine = input[parseLine].split(" ").filter { it != "" }.map { it.toLong() }
            currentTransforms.add(Transform(parseTransformLine[0], parseTransformLine[1], parseTransformLine[2]))
            parseLine++
        }
        return seeds.min()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}