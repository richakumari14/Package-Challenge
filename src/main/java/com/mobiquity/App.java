package com.mobiquity;

import com.mobiquity.packer.Packer;
import com.mobiquity.packer.exception.APIException;

import lombok.extern.log4j.Log4j2;

/**
 * Starting point of Application.
 * 
 * @author Richa
 *
 */
@Log4j2
public class App {

	public static void main(String[] args) {

		if (args.length > 0) {
			try {
				System.out.println(Packer.pack(args[0]));
			} catch (APIException e) {
				log.error(e.getMessage());
			}
		} else {
			log.error("Please enter a valid absolute filepath.");
		}
	}

}
