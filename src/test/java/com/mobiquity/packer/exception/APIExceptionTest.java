package com.mobiquity.packer.exception;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class APIExceptionTest {

	@Test
	public void testAPIException() {
		final APIException apiException = new APIException("Invalid input file!");
		assertTrue(apiException.getMessage().contains("Invalid input file!"));
	}
}
