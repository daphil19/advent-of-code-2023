
import okio.FileSystem
import okio.Path
import okio.WasiFileSystem

internal actual val FILE_SYSTEM: FileSystem
    get() = WasiFileSystem
actual val INPUT_FILE: Path
    get() = TODO("Not yet implemented")
