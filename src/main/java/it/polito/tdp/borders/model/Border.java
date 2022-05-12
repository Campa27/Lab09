package it.polito.tdp.borders.model;

import java.util.Objects;

public class Border {
	
	private String state1ab;
	private String state2ab;
	private int state1no;
	private int state2no;
	private int year;
	
	public Border(String state1ab, String state2ab, int state1no, int state2no, int year) {
		this.state1ab = state1ab;
		this.state2ab = state2ab;
		this.state1no = state1no;
		this.state2no = state2no;
		this.year = year;
	}

	public String getState1ab() {
		return state1ab;
	}

	public void setState1ab(String state1ab) {
		this.state1ab = state1ab;
	}

	public String getState2ab() {
		return state2ab;
	}

	public void setState2ab(String state2ab) {
		this.state2ab = state2ab;
	}

	public int getState1no() {
		return state1no;
	}

	public void setState1no(int state1no) {
		this.state1no = state1no;
	}

	public int getState2no() {
		return state2no;
	}

	public void setState2no(int state2no) {
		this.state2no = state2no;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(state1no, state2no);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		return state1no == other.state1no && state2no == other.state2no;
	}

	@Override
	public String toString() {
		return "Confine tra " + state1ab + " e " + state2ab;
	}
	
}
