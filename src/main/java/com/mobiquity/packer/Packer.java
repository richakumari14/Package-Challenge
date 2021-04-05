package com.mobiquity.packer;

import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.utils.PackageLineItemProcessor;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Packer is responsible for Packaging.
 * 
 * @author Richa
 *
 */
@Log4j2
public class Packer {

	/** Method responsible for packaging
	 * 
	 * @param filePath the absolute path to a test file as a String
	 * @return the solution as a String
	 * @throws APIException if incorrect parameters are being passed
	 */
	public static String pack(String filePath) throws APIException {

		Path path = Paths.get(filePath);

		try {
			if (Files.isReadable(path)) {
				List<String> packageLines = Files.lines(path).collect(Collectors.toList());
				StringBuilder stringBuilder = new StringBuilder();
				for (String line : packageLines) {
					stringBuilder.append(PackageLineItemProcessor.processLine(line) + "\n");
				}
				return stringBuilder.toString();
			} else {
				throw new APIException("Input file not readable!");
			}
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		return null;
	}

}
