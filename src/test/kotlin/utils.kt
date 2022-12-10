import org.junit.jupiter.params.provider.Arguments

class Utils {
    companion object {
        fun readTestFile(name: String): String = Utils::class.java.getResource(name)!!.readText()

        private val isCI = System.getenv("CI").toBoolean()

        fun cases(sampleCases: Array<Arguments>, personalCase: Arguments) =
            if (isCI) sampleCases else sampleCases + personalCase
    }
}

