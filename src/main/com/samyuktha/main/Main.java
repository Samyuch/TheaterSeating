package com.samyuktha.main;

import java.io.IOException;
import java.util.List;

import com.samyuktha.io.Response;
import com.samyuktha.theater.TheaterManager;
import com.samyuktha.exception.IllegalAssignmentException;

/**
 * Main class that contains main() method.
 */
public final class Main {

	/**
	 * Main method.
	 *
	 * @param args
	 *            arguments (array of strings).
	 * 
	 * @throws IOException
	 * @throws IllegalAssignmentException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws IOException, NumberFormatException, IllegalAssignmentException {

		if (args.length == 0) {
			throw new IllegalArgumentException("Input file is required");
		}

		TheaterManager manager = new TheaterManager();
		String input = args[0];
		List<Response> responses = manager.process(input);

		for (Response res : responses)
			System.out.println(res.toString());
	}
}