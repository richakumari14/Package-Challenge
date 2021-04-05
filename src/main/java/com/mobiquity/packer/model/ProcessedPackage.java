package com.mobiquity.packer.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model class for {@code ProcessedPackage}
 * 
 * @author Richa
 *
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProcessedPackage {

	public List<Item> items;
	public int totalCost;

}
