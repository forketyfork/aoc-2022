class Utils {
    companion object {
        fun readTestFile(name: String): String = Utils::class.java.getResource(name)!!.readText()
    }
}

