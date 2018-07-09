package com.samyuktha.theater;

import com.samyuktha.exception.IllegalAssignmentException;

/**
 * Defines the model for a section of Theater's layout.
 */
public class Section implements Comparable<Section>{

	public static final int NONE = -1;
    
	private final int id;
    private final int row;
    private final int capacity;
    
    private int available;
    
    public Section(int id, int row, int capacity) throws IllegalAssignmentException {
    	if(id < 0)
			throw new IllegalAssignmentException("The value of id should be non negative integer");
    	this.id = id;
    	
    	if(row < 0)
			throw new IllegalAssignmentException("The value of row should be non negative integer");
		this.row = row;
    	
    	if(capacity < 0)
			throw new IllegalAssignmentException("The value of capacity should non negative integer");
		this.capacity = capacity;
		
		this.available = capacity;
    }
    
	public int getId() {
		return id;
	}
	
	public int getRow() {
		return row;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public int getAvailable() {
		return available;
	}
	
	public void alterAvailablity(int change) {
		int value = available + change;
		if(value < 0)
			available = 0;
		else if(value > capacity)
			available = capacity;
		else
			available = value;
	}
	
	@Override
	public int compareTo(Section other) {
        int result = this.available - other.available;
        if(result == 0) {
        	result = this.row - other.row;
        }
        if(result == 0) {
        	result = this.id - other.id;
        }
        return result;        
    }
}