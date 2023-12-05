import okio.FileSystem
import okio.NodeJsFileSystem
import okio.Path.Companion.toPath

internal actual val FILE_SYSTEM: FileSystem = NodeJsFileSystem
actual val INPUT_FILE = FILE_SYSTEM.canonicalize("kotlin/input.txt".toPath())
