expect fun getLines(): List<String>

fun main() {
    var lines = getLines()
//    lines = """Time:      7  15   30
//Distance:  9  40  200""".lines()

    part1(lines)
    part2(lines)
}

fun part1(lines: List<String>) = printDuration {
    val times = lines[0].removePrefix("Time: ").trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }
    val distances = lines[1].removePrefix("Distance: ").trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }

    val res = times.zip(distances).map { (time, bestDist) ->
        // please don't int overflow...
        (0..time).count { speed ->
            val remaining = time - speed
            val dist = speed * remaining
            dist > bestDist
        }.toLong()
    }.reduce { acc, i -> acc * i }

    print("part 1: $res ")
}

fun part2(lines: List<String>) = printDuration {
    val time = lines[0].replace("\\D".toRegex(), "").toLong()
    val bestDist = lines[1].replace("\\D".toRegex(), "").toLong()

    // replace count with filter/fold in the event that we overflow
    val res = (0..time).filter { speed ->
        val remaining = time - speed
        val dist = speed * remaining
        dist > bestDist
    }.fold(0L) { acc, _ -> acc + 1}

    print("part 2: $res ")
}
