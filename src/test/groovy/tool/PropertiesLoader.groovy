package tool

class PropertiesLoader {

    static Properties load(String path) {
        Properties properties = new Properties()
        File propertiesFile = new File(path)
        propertiesFile.withInputStream {
            properties.load(it)
        }
        return properties
    }
}
