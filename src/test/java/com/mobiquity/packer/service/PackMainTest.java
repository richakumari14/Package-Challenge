package com.mobiquity.packer.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mobiquity.packer.Packer;
import com.mobiquity.packer.config.ConfigTest;
import com.mobiquity.packer.exception.APIException;

public class PackMainTest extends ConfigTest{

    private File input;
    private File output;

    @Before
    public void setup() throws URISyntaxException {
        input = getFileFromResource("example_input");
        output = getFileFromResource("example_output");
    }

    @After
    public void teardown() {
        input = null;
        output = null;
    }

    @Test
    public void testMain(){

        Exception exception = assertThrows(APIException.class, () -> {
            Packer.pack("main/testException");
        });
        String expectedMessage = "Input file not readable!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testPacker() throws APIException, IOException {

        String result = Packer.pack(input.getPath());
        List<String> resultList = result.lines().collect(Collectors.toList());
        List<String> packageLines = Files.lines(output.toPath()).collect(Collectors.toList());

        assertNotNull(result);
        assertEquals(packageLines.size(), resultList.size());

        for (int i = 0; i < packageLines.size(); i++) {
            assertEquals(packageLines.get(i), resultList.get(i));
        }
    }

   
}
