package com.mobiquity.packer.config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ConfigTest {

	 protected File getFileFromResource(String fileName) throws URISyntaxException {

	        ClassLoader classLoader = getClass().getClassLoader();
	        URL resource = classLoader.getResource(fileName);
	        if (resource == null) {
	            throw new IllegalArgumentException("file not found! " + fileName);
	        } else {
	            return new File(resource.toURI());
	        }

	    }
}
