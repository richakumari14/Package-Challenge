package com.mobiquity.packer.service;

import com.mobiquity.packer.model.Item;
import com.mobiquity.packer.model.ProcessedPackage;

import java.util.List;

/**
 * IPackageProcessor service interface
 * 
 * @author Richa
 *
 */

public interface IPackageProcessor {

	/**
	 * 
	 * @param capacity the maximum weight a package can hold
	 * @param items List<{@link Item}>
	 * @return {@link ProcessedPackage}
	 */
    ProcessedPackage processor(int capacity, List<Item> items);
}
