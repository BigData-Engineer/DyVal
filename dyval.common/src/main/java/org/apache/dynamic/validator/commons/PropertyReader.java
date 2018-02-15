package org.apache.dynamic.validator.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.dynamic.validator.commons.exception.CommonsException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {

    final static Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    public Properties readPropertiesFile(String filePath, boolean classpath) throws CommonsException {
        if (classpath) {
            return readFromClasspath(filePath);
        } else {
            if (filePath.startsWith("hdfs")) {
                return readFromHDFS(filePath);
            } else if (filePath.startsWith("file")) {
                return readFromLocal(filePath);
            } else {
                throw new CommonsException("Unknown path URI: " + filePath);
            }
        }
    }

    public Properties readFromClasspath(String configFileName) throws CommonsException {
        logger.info("Reading properties file: {} from classpath", configFileName);
        try (InputStream is = this.getClass().getResourceAsStream(configFileName)) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } catch (FileNotFoundException e) {
            throw new CommonsException("File not found: " + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new CommonsException("Error while reading file: " + e.getLocalizedMessage());
        }
    }

    public Properties readFromLocal(String filePath) throws CommonsException {
        logger.info("Reading properties file: {} from Local", filePath);
        Properties properties = new Properties();
        try (FileInputStream fs = new FileInputStream(new File(filePath))) {
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new CommonsException("Error while loading configuration file: " + e.getMessage());
        }
    }

    public Properties readFromHDFS(String filePath) throws CommonsException {
        logger.info("Reading properties file: {} from HDFS", filePath);
        try (FileSystem filesystem = FileSystem.get(new Configuration())) {
            Path path = new Path(filePath);
            try (FSDataInputStream currencyInputStream = filesystem.open(path)) {
                Properties properties = new Properties();
                properties.load(currencyInputStream);
                return properties;
            }
        } catch (IOException e) {
            throw new CommonsException("Error while loading configuration file: " + e.getMessage());
        }
    }
}
