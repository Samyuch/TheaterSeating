package com.samyuktha.theater;

import org.junit.Assert;
import org.junit.Test;

import com.samyuktha.exception.IllegalAssignmentException;

public class SectionTest {
	@Test
	public final void verifyNoExectionThrownForValidSection() {
		try {
			Section section = new Section(1, 1, 6);
			Assert.assertTrue(true);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyValidSection() {
		try {
			int id = 2;
			int row = 1;
			int capacity = 6;
			Section section = new Section(id, row, capacity);
			Assert.assertTrue(id == section.getId() && row == section.getRow()
					&& capacity == section.getCapacity() && capacity == section.getAvailable());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyExectionThrownForNegativeId() {
		try {
			Section section = new Section(-3, 2, 3);
			Assert.assertTrue(false);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public final void verifyExectionThrownForNegativeRow() {
		try {
			Section section = new Section(4, -3, 8);
			Assert.assertTrue(false);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public final void verifyExectionThrownForNegativeCapacity() {
		try {
			Section section = new Section(4, 3, -5);
			Assert.assertTrue(false);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public final void verifyAvailabilityModification() {
		try {
			int capacity = 6;
			int change = -3;
			int available = capacity + change;
			
			Section section = new Section(5, 4, capacity);
			section.alterAvailablity(change);
			
			Assert.assertTrue(available == section.getAvailable());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyCompareToEquals() {
		try {
			Section section1 = new Section(6, 4, 6);
			Section section2 = new Section(6, 4, 6);
			Assert.assertTrue(section1.compareTo(section2) == 0);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyCompareToGreaterThanZero() {
		try {
			Section section1 = new Section(6, 4, 6);
			Section section2 = new Section(6, 3, 6);
			Assert.assertTrue(section1.compareTo(section2) > 0);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public final void verifyCompareToLessThanZero() {
		try {
			Section section1 = new Section(5, 4, 6);
			Section section2 = new Section(6, 4, 6);
			Assert.assertTrue(section1.compareTo(section2) < 0);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
	}
}
