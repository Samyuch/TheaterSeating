package com.samyuktha.io;

import org.junit.Assert;
import org.junit.Test;

import com.samyuktha.exception.IllegalAssignmentException;

public class RequestTest {
	
	@Test
    public final void verifyNoExectionThrownForValidRequest() {
		try {
			Request request = new Request(1, "Smith", 2);
			Assert.assertTrue(true);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyValidRequest() {
    	try {
    		int id = 2;
    		String name = "Jones";
    		int count = 5;
			Request request = new Request(id, name, count);
			Assert.assertTrue(id == request.getId() && name.equals(request.getName()) && count == request.getCount() && request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyExectionThrownForNegativeId() {
    	try {
			Request request = new Request(-3, "Davis", 6);
			Assert.assertTrue(false);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
    }

    @Test
    public final void verifyRequestValidityForZeroId() {
    	try {
			Request request = new Request(0, "Wilson", 100);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyRequestValidityForNegativeCount() {
    	try {
			Request request = new Request(4, "Johnson", -3);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyRequestValidityForZeroCount() {
    	try {
			Request request = new Request(5, "Williams", 0);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyRequestValidityForNullName() {
    	try {
			Request request = new Request(6, null, 4);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyRequestValidityForEmptyName() {
    	try {
			Request request = new Request(7, "", 8);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyRequestValidityForWhitespaceName() {
    	try {
			Request request = new Request(8, "\0  \n  \r", 12);
			Assert.assertTrue(!request.isValid());
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyEquals() {
    	try {
			Request request1 = new Request(8, "John", 4);
			Request request2 = new Request(8, "John", 4);
			Assert.assertTrue(request1.equals(request2));
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }
}
