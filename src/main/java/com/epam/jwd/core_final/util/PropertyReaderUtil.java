package com.epam.jwd.core_final.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {
    public static final PropertyReaderUtil READER_UTIL = new PropertyReaderUtil(); // change private to public

    private PropertyReaderUtil() {
    }

    private static final Properties properties = new Properties();

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public String loadProperties(String param) {
        String result = null;
        final String propertiesFileName = "src/main/resources/application.properties";
        try {
            properties.load(new FileInputStream(propertiesFileName));
            result = properties.getProperty(param);
        } catch (IOException e) {
            System.out.println("File Properties non exist");
            System.exit(1);
        }
        return result;
    }
}
