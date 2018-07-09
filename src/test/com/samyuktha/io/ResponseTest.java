package com.samyuktha.io;

import org.junit.Assert;
import org.junit.Test;

import com.samyuktha.theater.Section;
import com.samyuktha.exception.IllegalAssignmentException;

public class ResponseTest {
	@Test
    public final void verifyNoExectionThrownForValidResponse() {
		try {
			Request request = new Request (1, "Smith", 2);
			Section section = new Section (1, 1, 2);
			
			Response response1 = new Response(request, Status.OK, section);
			Response response2 = new Response(request, section);
			
			Assert.assertTrue(true);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyValidRsponse() {
    	try {
    		Request request = new Request (2, "Jones", 5);
			Section section = new Section (1, 2, 6);
			Status status = Status.OK;
			
			Response response = new Response(request, status, section);
			
			Assert.assertTrue(request.equals(response.getRequest()) 
					&& status == response.getStatus()
					&& section.compareTo(response.getSection()) == 0);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyExectionThrownForNullRequest() {
    	try {
			Section section = new Section (1, 1, 2);			
			
			Response response = new Response(null, Status.SPLIT, section);
			
			Assert.assertTrue(false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyExectionThrownForOKStatusAndNullSection() {
    	try {
			Request request = new Request(3, "Wilson", 100);		
			
			Response response = new Response(request, Status.OK, null);
			
			Assert.assertTrue(false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(false);
		}
    }

    @Test
    public final void verifyEquals() {
    	try {
			Request request1 = new Request(3, "Wilson", 3);
			Request request2 = new Request(3, "Wilson", 3);
			
			Section section1 = new Section (1, 1, 4);
			Section section2 = new Section (1, 1, 4);
			
			Response response1 = new Response(request1, Status.OK, section1);
			Response response2 = new Response(request2, Status.OK, section2);
			
			Assert.assertTrue(response1.equals(response2));
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
    }

    @Test
    public final void verifyToString() {
    	try {
			Request request = new Request(9, "Michale", 2);
			
			Section section = new Section (2, 3, 4);
			
			Response response = new Response(request, Status.OK, section);
			
			Assert.assertTrue(response.toString().equals("Michale - Row 3 Section 2"));
		} catch (IllegalAssignmentException e) {
			Assert.assertTrue(true);
		}
    }
}
