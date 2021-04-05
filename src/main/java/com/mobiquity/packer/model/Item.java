package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Model class for {@code Item}
 * 
 * @author Richa
 *
 */
@Getter
@AllArgsConstructor
@ToString
public final class Item {

	private Integer index;
	private Double weight;
	private Integer cost;

}
