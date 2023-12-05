
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.WasiFileSystem

internal actual val FILE_SYSTEM: FileSystem = WasiFileSystem
actual val INPUT_FILE = FILE_SYSTEM.canonicalize("kotlin/input.txt".toPath())
