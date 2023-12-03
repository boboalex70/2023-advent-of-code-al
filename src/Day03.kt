import java.lang.StringBuilder

fun main() {
    fun charEqualsSymbol(input: Char): Boolean {
        return !input.isDigit() && input != '.'
    }
    fun checkAllAround(input: MutableList<List<Char>>, row: Int, startColumn: Int, endColumn: Int): Boolean {
        //check left and left corners
        if (startColumn > 0) {
            if (charEqualsSymbol(input[row][startColumn - 1])) return true
            if (row > 0) {
                if (charEqualsSymbol(input[row - 1][startColumn - 1])) return true
            }
            if (row < (input.size - 1)) {
                if (charEqualsSymbol(input[row + 1][startColumn - 1])) return true
            }
        }
        //check right and right corners
        if (endColumn < (input[0].size - 1)) {
            if (charEqualsSymbol(input[row][endColumn + 1])) return true
            if (row > 0) {
                if (charEqualsSymbol(input[row - 1][endColumn + 1])) return true
            }
            if (row < (input.size - 1)) {
                if (charEqualsSymbol(input[row + 1][endColumn + 1])) return true
            }
        }
        //check above
        if (row > 0){
            for (i in startColumn..endColumn) {
                if (charEqualsSymbol(input[row - 1][i])) return true
            }
        }
        //check below
        if (row < (input.size - 1)) {
            for (i in startColumn..endColumn) {
                if (charEqualsSymbol(input[row + 1][i])) return true
            }
        }
        return false
    }
    fun part1(input: List<String>): Int {
        val charTable = mutableListOf<List<Char>>()
        var runningTotal = 0
        for (curString in input) {
            val charString = curString.toList()
            charTable.add(charString)
        }
        for (i in 0..<charTable.size) {
            var columnIndex = 0
            var parsingDigit = false
            var startCheckDigit = 0
            while (columnIndex < charTable[i].size) {
                if (!parsingDigit && charTable[i][columnIndex].isDigit()) {
                    if (columnIndex == charTable[i].size - 1) {
                        if (checkAllAround(charTable, i, columnIndex, columnIndex)){
                            runningTotal += charTable[i][columnIndex].toString().toInt()
                            break
                        }
                    }
                    parsingDigit = true
                    startCheckDigit = columnIndex
                    columnIndex++
                    continue
                }
                if (parsingDigit && !charTable[i][columnIndex].isDigit()){
                    parsingDigit = false
                    if (checkAllAround(charTable, i, startCheckDigit, columnIndex - 1)){
                        val sb = StringBuilder()
                        for (j in startCheckDigit until columnIndex) {
                            sb.append(charTable[i][j])
                        }
                        sb.toString().println()
                        runningTotal += sb.toString().toInt()
                    }
                }
                if (parsingDigit && columnIndex == charTable[i].size - 1){
                    if (checkAllAround(charTable, i, startCheckDigit, columnIndex)){
                        val sb = StringBuilder()
                        for (j in startCheckDigit..columnIndex) {
                            sb.append(charTable[i][j])
                        }
                        sb.toString().println()
                        runningTotal += sb.toString().toInt()
                        break
                    }
                }
                columnIndex++
            }
        }
        return runningTotal
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}