package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();
		List<Country> countries = dao.loadAllCountries();
		List<Border> borders = dao.getCountryPairs(1900);
		System.out.println("Lista di tutte le nazioni:");
		System.out.println(countries);
		System.out.println("Lista di tutti i confini:");
		System.out.println(borders);
	}
}
