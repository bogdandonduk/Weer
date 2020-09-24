package weer.elytrondesign.core

import java.io.File
import java.io.FileOutputStream
import java.util.*

class FileHandler {

    companion object {
        fun writeFile(dir: File, name: String, content: ByteArray, append: Boolean) : File {
            val file = File(dir, name)
                file.createNewFile()

            val fOs = FileOutputStream(file, append)
                fOs.write(content)
                fOs.flush()
                fOs.close()

            return file
        }

        fun deleteFile(file: File) {
            file.delete()
        }

        fun readFile(file: File): String {
            val scanner = Scanner(file)
            val builder = StringBuilder()

            while(scanner.hasNextLine()) {
                builder.append(scanner.nextLine())
            }

            return builder.toString()
        }
    }

}