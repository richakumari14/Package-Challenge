package com.mobiquity.packer.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for {@code PackageLineItem}
 * 
 * @author Richa
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageLineItem {

	private int weightLimit;
	private List<Item> items;

}
