package com.samyuktha.theater;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.samyuktha.io.Request;
import com.samyuktha.io.Response;
import com.samyuktha.io.Status;
import com.samyuktha.exception.IllegalAssignmentException;

public class TheaterTest {
	@Test
	public final void verifyTheaterConstructor() {
		Theater theater = new Theater();
		Assert.assertTrue(theater.getAvailable() == 0 && theater.getRows() == 0 && theater.getSectionCount() == 0);
	}

	@Test
	public final void verifyRowAdd() {
		Theater theater = new Theater();

		try {
			String rowStr = "6 6";
			int available = 12, rows = 1, sectionCount = 2;

			Section s11 = new Section(1, 1, 6);
			Section s12 = new Section(2, 1, 6);

			theater.addRow(rowStr);
			Assert.assertTrue(theater.getAvailable() == available && theater.getRows() == rows
					&& theater.getSectionCount() == sectionCount && theater.getSection(1, 1).compareTo(s11) == 0
					&& theater.getSection(1, 2).compareTo(s12) == 0);

			rowStr = "3 5 5 3";
			available = 28;
			rows = 2;
			sectionCount = 6;
			Section s21 = new Section(1, 2, 3);
			Section s22 = new Section(2, 2, 5);
			Section s23 = new Section(3, 2, 5);
			Section s24 = new Section(4, 2, 3);

			theater.addRow(rowStr);
			Assert.assertTrue(theater.getAvailable() == available && theater.getRows() == rows
					&& theater.getSectionCount() == sectionCount && theater.getSection(2, 1).compareTo(s21) == 0
					&& theater.getSection(2, 2).compareTo(s22) == 0 && theater.getSection(2, 3).compareTo(s23) == 0
					&& theater.getSection(2, 4).compareTo(s24) == 0);

		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyProcessRequests() {
		Theater theater = new Theater();

		try {
			theater.addRow("6 6");
			theater.addRow("3 5 5 3");
			theater.addRow("4 6 6 4");
			theater.addRow("2 8 8 2");
			theater.addRow("6 6");

			List<Request> reqs = new ArrayList<Request>();
			reqs.add(new Request(1, "Smith 2"));
			reqs.add(new Request(2, "Jones 5"));
			reqs.add(new Request(3, "Davis 6"));
			reqs.add(new Request(4, "Wilson 100"));
			reqs.add(new Request(5, "Johnson 3"));
			reqs.add(new Request(6, "Williams 4"));
			reqs.add(new Request(7, "Brown 8"));
			reqs.add(new Request(8, "Miller 12"));
			reqs.add(new Request(9, "Samule 0"));

			List<Response> expRes = new ArrayList<Response>();
			expRes.add(new Response(reqs.get(0), Status.OK, theater.getSection(1, 1)));
			expRes.add(new Response(reqs.get(1), Status.OK, theater.getSection(2, 2)));
			expRes.add(new Response(reqs.get(2), Status.OK, theater.getSection(1, 2)));
			expRes.add(new Response(reqs.get(3), Status.EXCEED, null));
			expRes.add(new Response(reqs.get(4), Status.OK, theater.getSection(2, 1)));
			expRes.add(new Response(reqs.get(5), Status.OK, theater.getSection(1, 1)));
			expRes.add(new Response(reqs.get(6), Status.OK, theater.getSection(4, 2)));
			expRes.add(new Response(reqs.get(7), Status.SPLIT, null));
			expRes.add(new Response(reqs.get(8), Status.INVALID, null));

			List<Response> res = theater.process(reqs);

			Assert.assertTrue(expRes.get(0).equals(res.get(0))); // Req Id = 1
			Assert.assertTrue(expRes.get(1).equals(res.get(1))); // Req Id = 2
			Assert.assertTrue(expRes.get(2).equals(res.get(2))); // Req Id = 3
			Assert.assertTrue(expRes.get(3).equals(res.get(3))); // Req Id = 4
			Assert.assertTrue(expRes.get(4).equals(res.get(4))); // Req Id = 5
			Assert.assertTrue(expRes.get(5).equals(res.get(5))); // Req Id = 6
			Assert.assertTrue(expRes.get(6).equals(res.get(6))); // Req Id = 7
			Assert.assertTrue(expRes.get(7).equals(res.get(7))); // Req Id = 8
			Assert.assertTrue(expRes.get(8).equals(res.get(8))); // Req Id = 9

		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	
}
