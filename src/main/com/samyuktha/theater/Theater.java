package com.samyuktha.theater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.samyuktha.io.Request;
import com.samyuktha.io.Response;
import com.samyuktha.io.Status;
import com.samyuktha.exception.IllegalAssignmentException;

/**
 * Defines the layout of the theater. Processes the ticket requests.
 */
public final class Theater {

	private int rows = 0;
	private int available = 0;
	private List<Section> sections = new ArrayList<>();

	public int getAvailable() {
		return available;
	}

	public int getRows() {
		return rows;
	}

	public int getSectionCount() {
		return sections.size();
	}

	public Section getSection(int index) {
		return sections.get(index);
	}

	public Section getSection(int row, int secId) {
		for (Section section : sections) {
			if (section.getRow() == row && section.getId() == secId)
				return section;
		}

		return null;
	}

	public void addRow(String row) throws IllegalAssignmentException {
		this.rows++;
		String[] data = row.trim().split(" ");
		for (int i = 0; i < data.length; i++) {
			int id = i + 1;
			int value = Integer.parseInt(data[i]);
			Section section = new Section(id, rows, value);
			this.sections.add(section);
			this.available += value;
		}
	}

	public List<Response> process(List<Request> requests) {
		Map<Integer, Response> result = new TreeMap<>();

		for (int i = 0; i < requests.size(); i++) {

			Request request = requests.get(i);
			if (result.containsKey(request.getId())) {
				continue;
			}

			if (request.isValid()) {
				if (request.getCount() > this.available) {
					Response response = new Response(request, Status.EXCEED, null);
					result.put(request.getId(), response);
				}

				for (int j = 0; j < sections.size(); j++) {
					Section section = sections.get(j);
					if (request.getCount() == section.getAvailable()) {
						section.alterAvailablity(-1 * request.getCount());
						this.available = (this.available - request.getCount());
						Response response = new Response(request, Status.OK, section);
						result.put(request.getId(), response);
						break;
					} else if (request.getCount() < section.getAvailable()) {

						int complementSize = section.getAvailable() - request.getCount();
						Set<Integer> processed = result.keySet();
						Request complement = findComplement(requests, complementSize, i, processed);

						if (complement != null) {
							addProcessed(section, request, result);
							addProcessed(section, complement, result);
							break;
						} else {
							Section availableSection = findSectionByAvailability(request.getCount());
							Section candidate = (availableSection != null) ? availableSection : section;
							addProcessed(candidate, request, result);
							break;
						}
					}
				}

				if (!result.containsKey(request.getId())) {
					Response response = new Response(request, Status.SPLIT, null);
					result.put(request.getId(), response);
				}
			} else {
				Response response = new Response(request, Status.INVALID, null);
				result.put(request.getId(), response);
			}
		}

		Collection<Response> values = result.values();
		return new ArrayList<>(values);
	}

	private void addProcessed(Section section, Request request, Map<Integer, Response> result) {
		section.alterAvailablity(-1 * request.getCount());
		this.available = (this.available - request.getCount());
		Response response = new Response(request, Status.OK, section);
		result.put(request.getId(), response);
	}

	private Request findComplement(List<Request> requests, int seats, int index, Set<Integer> processed) {

		for (int i = index + 1; i < requests.size(); i++) {
			Request request = requests.get(i);
			if (request.isValid() && request.getCount() == seats) {
				if (!processed.contains(request.getId())) {
					return request;
				}
			}
		}

		return null;
	}

	private Section findSectionByAvailability(int seats) {

		Section result = null;

		try {
			Section section = new Section(0, 0, seats);

			Collections.sort(sections);

			Comparator<Section> byAvailableComparator = new Comparator<Section>() {
				@Override
				public int compare(Section o1, Section o2) {
					return o1.getAvailable() - o2.getAvailable();
				}
			};

			int id = Collections.binarySearch(sections, section, byAvailableComparator);

			if (id > 0) {
				int i = 0;
				for (i = id - 1; i >= 0; i--) {
					Section s = sections.get(i);
					if (s.getAvailable() != available) {
						break;
					}
				}
				id = i + 1;
			}

			if (id >= 0) {
				result = sections.get(id);
			}
		} catch (IllegalAssignmentException e) {
			System.err.println("Theater.findSectionByAvailability(). Error: " + e.getMessage());
		}

		return result;
	}
}