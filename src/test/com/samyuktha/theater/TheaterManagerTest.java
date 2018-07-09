package com.samyuktha.theater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.samyuktha.io.Response;
import com.samyuktha.exception.IllegalAssignmentException;

public class TheaterManagerTest {
	@Test
	public final void verifyValidInputFileParsing() {
		TheaterManager manager = new TheaterManager();

		try {
			Section s11 = new Section(1, 1, 6);
			Section s12 = new Section(2, 1, 6);
			Section s21 = new Section(1, 2, 3);
			Section s22 = new Section(2, 2, 5);
			Section s23 = new Section(3, 2, 5);
			Section s24 = new Section(4, 2, 3);
			Section s31 = new Section(1, 3, 4);
			Section s32 = new Section(2, 3, 6);
			Section s33 = new Section(3, 3, 6);
			Section s34 = new Section(4, 3, 4);
			Section s41 = new Section(1, 4, 2);
			Section s42 = new Section(2, 4, 8);
			Section s43 = new Section(3, 4, 8);
			Section s44 = new Section(4, 4, 2);
			Section s51 = new Section(1, 5, 6);
			Section s52 = new Section(2, 5, 6);

			manager.process("data/theaterseating.txt");

			Theater theater = manager.getTheater();

			Section sec = theater.getSection(1, 1);
			Assert.assertTrue(s11.getId() == sec.getId() && s11.getRow() == sec.getRow() && s11.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(1, 2);
			Assert.assertTrue(s12.getId() == sec.getId() && s12.getRow() == sec.getRow() && s12.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(2, 1);
			Assert.assertTrue(s21.getId() == sec.getId() && s21.getRow() == sec.getRow() && s21.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(2, 2);
			Assert.assertTrue(s22.getId() == sec.getId() && s22.getRow() == sec.getRow() && s22.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(2, 3);
			Assert.assertTrue(s23.getId() == sec.getId() && s23.getRow() == sec.getRow() && s23.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(2, 4);
			Assert.assertTrue(s24.getId() == sec.getId() && s24.getRow() == sec.getRow() && s24.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(3, 1);
			Assert.assertTrue(s31.getId() == sec.getId() && s31.getRow() == sec.getRow() && s31.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(3, 2);
			Assert.assertTrue(s32.getId() == sec.getId() && s32.getRow() == sec.getRow() && s32.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(3, 3);
			Assert.assertTrue(s33.getId() == sec.getId() && s33.getRow() == sec.getRow() && s33.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(3, 4);
			Assert.assertTrue(s34.getId() == sec.getId() && s34.getRow() == sec.getRow() && s34.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(4, 1);
			Assert.assertTrue(s41.getId() == sec.getId() && s41.getRow() == sec.getRow() && s41.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(4, 2);
			Assert.assertTrue(s42.getId() == sec.getId() && s42.getRow() == sec.getRow() && s42.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(4, 3);
			Assert.assertTrue(s43.getId() == sec.getId() && s43.getRow() == sec.getRow() && s43.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(4, 4);
			Assert.assertTrue(s44.getId() == sec.getId() && s44.getRow() == sec.getRow() && s44.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(5, 1);
			Assert.assertTrue(s51.getId() == sec.getId() && s51.getRow() == sec.getRow() && s51.getCapacity() == sec.getCapacity());
			
			sec = theater.getSection(5, 2);
			Assert.assertTrue(s52.getId() == sec.getId() && s52.getRow() == sec.getRow() && s52.getCapacity() == sec.getCapacity());
			
		} catch (IllegalAssignmentException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyProcessRequests() {
		TheaterManager manager = new TheaterManager();

		try {
			List<String> expRes = new ArrayList<String>();
			expRes.add("Smith - Row 1 Section 1");
			expRes.add("Jones - Row 2 Section 2");
			expRes.add("Davis - Row 1 Section 2");
			expRes.add("Wilson - Sorry, we can't handle your party.");
			expRes.add("Johnson - Row 2 Section 1");
			expRes.add("Williams - Row 1 Section 1");
			expRes.add("Brown - Row 4 Section 2");
			expRes.add("Miller - Call to split party.");
			expRes.add("Samule - Invalid request. Either name and/or requested seats have invalid entry.");

			List<Response> res = manager.process("data/theaterseating.txt");

			Assert.assertTrue(expRes.get(0).toString().equals(res.get(0).toString())); // Req Id = 1
			Assert.assertTrue(expRes.get(1).toString().equals(res.get(1).toString())); // Req Id = 2
			Assert.assertTrue(expRes.get(2).toString().equals(res.get(2).toString())); // Req Id = 3
			Assert.assertTrue(expRes.get(3).toString().equals(res.get(3).toString())); // Req Id = 4
			Assert.assertTrue(expRes.get(4).toString().equals(res.get(4).toString())); // Req Id = 5
			Assert.assertTrue(expRes.get(5).toString().equals(res.get(5).toString())); // Req Id = 6
			Assert.assertTrue(expRes.get(6).toString().equals(res.get(6).toString())); // Req Id = 7
			Assert.assertTrue(expRes.get(7).toString().equals(res.get(7).toString())); // Req Id = 8
			Assert.assertTrue(expRes.get(8).toString().equals(res.get(8).toString())); // Req Id = 9

		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		} catch (IOException e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}

