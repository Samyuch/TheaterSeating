package com.samyuktha.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import com.samyuktha.exception.IllegalAssignmentException;
import com.samyuktha.io.Response;
import com.samyuktha.theater.TheaterManager;

@RunWith(Parameterized.class)
public class MainTest {
	private int id;
	private String input;
	private TheaterManager theaterManager;

	private List<String> expected;

	@Before
	public void initialize() {
		theaterManager = new TheaterManager();
	}

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in primeNumbers() method

	public MainTest(int id, String inputFile, String expectedResultFile) {
		this.id = id;
		this.input = inputFile;
		this.expected = new ArrayList<String>();

		String line = null;
		BufferedReader reader = null;
		try {
			File file = new File(expectedResultFile);
			reader = new BufferedReader(new FileReader(file));

			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0)
					expected.add(line);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Expected output file '" + expectedResultFile + "' not found");
		} catch (IOException e) {
			System.err.println(
					"Failed to read expected output file '" + expectedResultFile + "'. Error: " + e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println("Failed to close expected output file '" + expectedResultFile + "'. Error: "
							+ e.getMessage());
				}
			}
		}
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testcases() {
		return Arrays.asList(new Object[][] { { 1, "data/testcases/1.txt", "data/expected_outputs/1.txt" },
				{ 2, "data/testcases/2.txt", "data/expected_outputs/2.txt" } });
	}

	@Test
	public void test() {
		System.out.println("Test Case : " + id);

		try {
			List<Response> responses = theaterManager.process(input);
			List<String> actuals = new ArrayList<String>();

			for (Response res : responses) {
				actuals.add(res.toString());
			}

			Assert.assertArrayEquals(expected.toArray(), actuals.toArray());
		} catch (NumberFormatException | IOException | IllegalAssignmentException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}