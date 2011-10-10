package com.accommodationmatches.domain;

public class PriceRange {
	private int min;
	private int max;
	
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + max;
		result = prime * result + min;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceRange other = (PriceRange) obj;
		if (max != other.max)
			return false;
		if (min != other.min)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PriceRange [min=" + min + ", max=" + max + "]";
	}
}
