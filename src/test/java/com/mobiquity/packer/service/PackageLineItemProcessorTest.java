package com.mobiquity.packer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mobiquity.packer.Packer;
import com.mobiquity.packer.config.ConfigTest;
import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Item;
import com.mobiquity.packer.model.PackageLineItem;
import com.mobiquity.packer.utils.PackageLineItemProcessor;


public class PackageLineItemProcessorTest extends ConfigTest {

	private static final int MAX_TOTAL_WEIGHT = 100; 

	@Test
	public void testCapacityMissing() throws URISyntaxException {
		assertThrows("Capacity is empty", APIException.class, () -> {
			Packer.pack(getFileFromResource("capacity_missing").getPath());
		});
	}

	@Test
	public void testCapacityTooBig() {
		assertThrows("weight is greater than " + MAX_TOTAL_WEIGHT, APIException.class, () -> {
			Packer.pack(getFileFromResource("capacity_too_big").getPath());
		});
	}

	@Test
	public void testCapacityInvalid() {
		assertThrows("Weight format exception ", APIException.class, () -> {
			Packer.pack(getFileFromResource("capacity_invalid").getPath());
		});
	}

	@Test
	public void testConfigurationParser() throws APIException {

		PackageLineItem expected = new PackageLineItem();

		expected.setWeightLimit(81);

		List<Item> items = new ArrayList<>();
		items.add(new Item(1, 53.38, 45));
		items.add(new Item(2, 88.62, 98));
		items.add(new Item(3, 78.48, 3));
		items.add(new Item(4, 72.30, 76));
		items.add(new Item(5, 30.18, 9));
		items.add(new Item(6, 46.34, 48));

		expected.setItems(items);

		PackageLineItem parsed = PackageLineItemProcessor
				.process("81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");

		assertNotNull(parsed);
		assertNotNull(parsed.getItems());
		assertEquals(expected.getWeightLimit(), parsed.getWeightLimit());
		assertEquals(expected.getItems().toString(), parsed.getItems().toString());
	}

}
