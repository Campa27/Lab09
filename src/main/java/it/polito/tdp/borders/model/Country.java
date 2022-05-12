package it.polito.tdp.borders.model;

import java.util.Objects;

public class Country {
	
	private String stateAbb;
	private int CCode;
	private String StateNme;
	
	public Country(String stateAbb, int cCode, String stateNme) {
		this.stateAbb = stateAbb;
		CCode = cCode;
		StateNme = stateNme;
	}

	public String getStateAbb() {
		return stateAbb;
	}

	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}

	public int getCCode() {
		return CCode;
	}

	public void setCCode(int cCode) {
		CCode = cCode;
	}

	public String getStateNme() {
		return StateNme;
	}

	public void setStateNme(String stateNme) {
		StateNme = stateNme;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return CCode == other.CCode;
	}

	@Override
	public String toString() {
		return StateNme;
	}
	
}
