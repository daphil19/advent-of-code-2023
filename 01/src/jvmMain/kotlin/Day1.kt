
import kotlin.io.path.Path
import kotlin.io.path.readLines

//actual val FILE_SYSTEM = FileSystem.SYSTEM

fun main() {
    val lines = Path("input.txt").readLines()
//    val lines = ("1abc2\n" +
//            "pqr3stu8vwx\n" +
//            "a1b2c3d4e5f\n" +
//            "treb7uchet").split("\n")

    println(lines.map {
        it.toCharArray().filter { it.isDigit() }
    }.map { "${it.first()}${it.last()}".toLong() }
//        .onEach { println(it) }
        .sum())


    val numbers = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

//    lines = ("two1nine\n" +
//            "eightwothree\n" +
//            "abcone2threexyz\n" +
//            "xtwone3four\n" +
//            "4nineeightseven2\n" +
//            "zoneight234\n" +
//            "7pqrstsixteen" +
////            "\neighthree" +
//            "").split("\n")

    // ugh lookaheads...
    var r = Regex("(?=(one|two|three|four|five|six|seven|eight|nine|[0-9]))")

    val parsed = lines.map {
        val matches = r.findAll(it).flatMap { it.groupValues }.filter { it.isNotBlank() }
        val first = matches.first().let { numbers[it] ?: it }
        val last = matches.last().let { numbers[it] ?: it }

        "$first$last".toLong().also { res -> println("$it -> $res")}
    }
//        .onEach { println(it) }


    println(parsed.sum())

//    println(lines
//        .map {  line ->
//            var modified = line
//            numbers.forEach { word, num ->
//                modified = modified.replace(word, num)
//            }
//            modified.also { println(it)}
//        }
////        .onEach { println(it) }
//        .map {
//        it.toCharArray().filter { it.isDigit() }
//    }.map { "${it.first()}${it.last()}".toLong() }.onEach { println(it) }.sum())

}