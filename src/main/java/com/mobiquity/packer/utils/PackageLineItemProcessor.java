package com.mobiquity.packer.utils;

import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Item;
import com.mobiquity.packer.model.PackageLineItem;
import com.mobiquity.packer.model.ProcessedPackage;
import com.mobiquity.packer.service.IPackageProcessor;
import com.mobiquity.packer.service.PackageProcessorImpl;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * An utility class to read and process input data into PackageLineItem
 * 
 * @author Richa
 *
 */
@Log4j2
public class PackageLineItemProcessor {

	private static final IPackageProcessor processor = new PackageProcessorImpl();

	private static final int MAX_TOTAL_WEIGHT = 100; // Max weight that a package can take

	/**
	 *
	 * @param line contains weight limit and package items
	 * @return indexes of package to maximize the cost within given weight limit
	 * @throws APIException if something goes wrong while processing
	 */
	public static String processLine(String line) throws APIException {

		PackageLineItem packageLineItem = process(line);

		if (packageLineItem == null) {
			throw new APIException("Invalid input file!");
		}

		ProcessedPackage processedPackage = processor.processor(packageLineItem.getWeightLimit(),
				packageLineItem.getItems());

		String solutionItemsList;

		if (processedPackage.getItems() != null && !processedPackage.getItems().isEmpty()) {
			solutionItemsList = processedPackage.getItems().stream().map(i -> i.getIndex().toString())
					.collect(Collectors.joining(","));
		} else {
			solutionItemsList = "-";
		}

		return solutionItemsList;
	}

	/**
	 * @param line contains raw input data
	 * @return a PackageLineItem contains structured processed packaged details with
	 *         list of package items and weight limit
	 * @throws APIException
	 */
	public static PackageLineItem process(String line) throws APIException {

		PackageLineItem packageLineItem = null;

		String[] configurationParts = line.split(" : ");

		try {

			if (configurationParts.length == 2) {
				if (configurationParts[0] == null) {
					log.error("warn: weight is null");
				}

				Integer capacity = getMaxWeight(configurationParts[0]);

				String[] itemsParts = configurationParts[1].split("\\s+");

				if (itemsParts.length > 0) {

					List<Item> itemsList = new ArrayList<>();

					for (String itemString : itemsParts) {

						String[] itemParts = itemString.replace("(", "").replace(")", "").split(",");
						if (itemParts.length == 3) {

							itemsList.add(new Item(Integer.parseInt(itemParts[0]), Double.parseDouble(itemParts[1]),
									Integer.parseInt(itemParts[2].replace("€", ""))));

						} else {
							itemsList.clear();
							break;
						}
					}

					if (!itemsList.isEmpty()) {
						packageLineItem = new PackageLineItem(capacity, itemsList);
					}

				}

			}
		} catch (NumberFormatException e) {
			packageLineItem = null;
		}

		return packageLineItem;
	}

	/**
	 * Retrieve the maximum weight.
	 * 
	 * @param weightString string containing the maximum weight.
	 * @return an integer containing the maximum weight.
	 * @throws APIException
	 */
	private static Integer getMaxWeight(String weightString) throws APIException {
		weightString = weightString.trim();
		if (StringUtils.isEmpty(weightString)) {
			String message = "Capacity is empty";
			log.error(message);
			throw new APIException(message);
		}
		int weight;
		try {
			weight = Integer.parseInt(weightString);
		} catch (NumberFormatException exception) {
			log.error("Weight format exception " + exception.getMessage());
			throw new APIException("Weight format exception " + exception.getMessage());
		}
		if (weight > MAX_TOTAL_WEIGHT) {
			log.error("weight is greater than {}", MAX_TOTAL_WEIGHT);
			throw new APIException("weight is greater than " + MAX_TOTAL_WEIGHT);
		}
		return weight;

	}

}
