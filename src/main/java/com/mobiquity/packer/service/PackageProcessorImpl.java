package com.mobiquity.packer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.packer.model.Item;
import com.mobiquity.packer.model.ProcessedPackage;

/**
 * Dynamic Programming solution of the 0-1 Knapsack problem
 * 
 * @author Richa
 *
 */
public class PackageProcessorImpl implements IPackageProcessor {

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.mobiquity.packer.service.IPackageProcessor#processor(int, List)
	 */
	@Override
	public ProcessedPackage processor(int capacity, List<Item> items) {

		/*
		 * Remove packages which weighs more than the weight limit
		 */
		items = items.stream().filter(item -> item.getWeight() <= capacity).collect(Collectors.toList());
		/*
		 * Sort the items in ascending order since you would prefer to send a package
		 * which weighs less in case there is more than one package with the same price.
		 */
		items.sort(Comparator.comparing(Item::getWeight));

		Double[] wts = items.stream().map(Item::getWeight).toArray(Double[]::new);
		Integer[] cost = items.stream().map(Item::getCost).toArray(Integer[]::new);
		Boolean visited[] = new Boolean[items.size()];
		Arrays.fill(visited, Boolean.FALSE);

		/*
		 * Compute the maximum cost , keeping track of visited items
		 */
		Integer maxCost = maximizeCost(capacity, wts, cost, items.size(), visited);

		List<Item> packedItems = new ArrayList<>();

		for (int i = 0; i < items.size(); i++) {
			if (visited[i]) {
				packedItems.add(items.get(i));
			}
		}

		/*
		 * Items included in the solution should be listed following index natural order
		 */
		packedItems.sort(Comparator.comparing(Item::getIndex));

		return new ProcessedPackage(packedItems, maxCost);
	}

	/**
	 * Java code for Dynamic Programming based solution for 0-1 Knapsack problem
	 * 
	 * @param cap     the maximum capacity of the package
	 * @param wt      array of items weights
	 * @param cost    array of items values
	 * @param N       total number of items
	 * @param visited keeping track of items which can be processed
	 * @return maximum value for the package
	 */
	private Integer maximizeCost(Integer cap, Double wt[], Integer cost[], Integer N, Boolean visited[]) {

		/*
		 * Create a matrix having N+1 rows and capacity+1 columns
		 */
		int[][] mat = new int[N + 1][cap + 1];

		for (int r = 0; r < cap + 1; r++) {
			mat[0][r] = 0;
		}
		for (int c = 0; c < N + 1; c++) {
			mat[c][0] = 0;
		}

		for (int item = 1; item <= N; item++) {
			for (int capacity = 1; capacity <= cap; capacity++) {
				int maxValWithoutCurr = mat[item - 1][capacity];
				int maxValWithCurr = 0;

				Double weightOfCurr = wt[item - 1];

				if (capacity >= weightOfCurr) {
					maxValWithCurr = cost[item - 1];

					int remainingCapacity = (int) Math.ceil(capacity - weightOfCurr);
					maxValWithCurr += mat[item - 1][remainingCapacity];
				}

				mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr);
			}
		}

		int i;
		double w;

		int res = mat[N][cap]; // stores the result of Knapsack present at the lower right corner of the matrix
		w = cap;
		for (i = N; i > 0 && res > 0; i--) {
			if (res == mat[i - 1][(int) Math.ceil(w)])
				continue;
			else {
				visited[i - 1] = true;
				// Since this cost is included its
				// value is deducted
				res = res - cost[i - 1];
				w = w - wt[i - 1];
				if (w < 0) {
					break;
				}
			}
		}

		return mat[N][cap];
	}
}
