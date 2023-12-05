
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath


internal expect val FILE_SYSTEM: FileSystem
expect val INPUT_FILE: Path

fun getLines(path: String) = getLines(path.toPath())
fun getLines(path: Path) = FILE_SYSTEM.read(path) {
    readUtf8()
}.split("\n")
