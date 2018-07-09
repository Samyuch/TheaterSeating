package com.samyuktha.io;

import com.samyuktha.theater.Section;

/**
 * Defines the model for the response to processed request.
 */
public class Response {

	private static final String SPACE = " ";

	private Request request;
	private Status status;
	private Section section;

	public Response(Request request, Status status, Section section) throws NullPointerException {
		if (request == null)
			throw new NullPointerException("The request object cannot be null");
		this.request = request;

		this.status = status == null ? Status.OK : status;

		if (this.status == Status.OK && section == null)
			throw new NullPointerException("The section object cannot be null for status=OK");

		this.section = section;
	}

	public Response(Request request, Section section) throws NullPointerException {
		this(request, Status.OK, section);
	}

	public Request getRequest() {
		return request;
	}

	public Status getStatus() {
		return status;
	}

	public Section getSection() {
		return section;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(request.getName());
		buffer.append(SPACE);
		buffer.append("-");
		buffer.append(SPACE);

		if (section != null) {
			buffer.append("Row");
			buffer.append(SPACE);
			buffer.append(section.getRow());
			buffer.append(SPACE);
			buffer.append("Section");
			buffer.append(SPACE);
			buffer.append(section.getId());
		} else {
			buffer.append(status.getDescription());
		}

		return buffer.toString();
	}
	
	public boolean equals(Response response) {
		return (request.equals(response.getRequest()) 
				&& status == response.getStatus()
				&& (section == response.getSection() || section.compareTo(response.getSection()) == 0));
	}
}