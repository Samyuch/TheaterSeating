package com.samyuktha.theater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.samyuktha.io.Request;
import com.samyuktha.io.Response;
import com.samyuktha.exception.IllegalAssignmentException;

/**
 * Holds the Theater instance and List of Requests. Parses the input file to
 * extract theater layout & requests and processes the requests.
 */
public final class TheaterManager {

	private Theater theater = new Theater();
	private List<Request> requests = new ArrayList<>();

	public Theater getTheater() {
		return theater;
	}

	public List<Request> getRequests() {
		return requests;
	}

	/**
	 * public method that parses the theater layout and process the requests. Calls
	 * parseInput() method to parse the input files. Calls process() method of
	 * theater to process the requests.
	 *
	 * @param input
	 *            path of the file that contains theater layout and list of
	 *            requests.
	 *
	 * @return list of responses for all the requests.
	 * 
	 * @throws IOException
	 * @throws IllegalAssignmentException
	 * @throws NumberFormatException
	 */
	public List<Response> process(String input) throws IOException, NumberFormatException, IllegalAssignmentException {
		this.parseInput(input);
		return theater.process(requests);
	}

	/**
	 * method to parse theater layout into <theater> and store the requests into
	 * <requests> list.
	 *
	 * @param input
	 *            path of the file that contains theater layout and list of
	 *            requests.
	 * 
	 * @throws IOException
	 * @throws IllegalAssignmentException
	 * @throws NumberFormatException
	 */
	private void parseInput(String input) throws IOException, NumberFormatException, IllegalAssignmentException {

		int mode = 0; // 0: layout; 1: requests
		int requestId = 0;
		String line = null;

		BufferedReader reader = null;
		try {
			File file = new File(input);
			reader = new BufferedReader(new FileReader(file));

			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) {
					mode++;
					continue;
				}
				if (mode == 0) {
					theater.addRow(line);
				} else if (mode == 1) {
					try {
						requestId++;
						Request request = new Request(requestId, line);
						requests.add(request);
					} catch (IllegalAssignmentException e) {
						System.out.println("Unable to parse the request. Error: " + e.getMessage());
					}
				}
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
}
