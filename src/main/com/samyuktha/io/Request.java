package com.samyuktha.io;

import com.samyuktha.exception.IllegalAssignmentException;
import com.samyuktha.util.StringUtil;

/**
 * Defines the model for a tickets request.
 */
public final class Request {

	private final int id;
	private final String name;
	private final int count;
	private final boolean valid;

	public Request(int id, String name, int count) throws IllegalAssignmentException {
		if (id < 0)
			throw new IllegalAssignmentException("The value of id should be non negative integer");

		this.id = id;

		if (StringUtil.isNullOrWhitespace(name) || count <= 0 || id == 0)
			valid = false;
		else
			valid = true;

		this.name = name;
		this.count = count;
	}

	public Request(int id, String requestStr) throws IllegalAssignmentException {
		if (id < 0)
			throw new IllegalAssignmentException("The value of id should be non negative integer");

		this.id = id;

		String name = null;
		int count = 0;

		if (StringUtil.isNullOrWhitespace(requestStr)) {
			valid = false;
		} else {
			try {
				String[] data = requestStr.trim().split(" ");
				if (data.length > 0) {
					name = data[0];

					if (data.length > 1)
						count = Integer.parseInt(data[1]);
				}
			} catch (NumberFormatException e) {
			}

			if (StringUtil.isNullOrWhitespace(name) || count <= 0 || id == 0)
				valid = false;
			else
				valid = true;
		}

		this.name = name;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public boolean isValid() {
		return valid;
	}

	public boolean equals(Request request) {
		return (id == request.getId() 
				&& (name == request.getName() || name.equals(getName()))
				&& count == request.getCount()
				&& valid == request.isValid());
	}
}