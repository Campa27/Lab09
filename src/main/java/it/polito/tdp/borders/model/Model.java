package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	BordersDAO dao;
	List<Country> nazioni;
	List<Border> confini;
	public Graph<Country, DefaultEdge> grafo;
	ConnectivityInspector<Country, DefaultEdge> ispettore;

	public Model() {
		dao = new BordersDAO();
		nazioni = this.dao.loadAllCountries();
	}
	
	public void createGraph(int anno) {
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		confini = this.dao.getCountryPairs(anno);
		
		Map<Integer, Country> nazioniMap = new HashMap<Integer, Country>();
		for(Country n : nazioni) {
			nazioniMap.put(n.getCCode(), n);
		}
		
		Graphs.addAllVertices(this.grafo, nazioni);
		
		//metodo 3, nessun loop
		for(Border confine : confini) {
			Country nazione1 = nazioniMap.get(confine.getState1no());
			Country nazione2 = nazioniMap.get(confine.getState2no());
			this.grafo.addEdge(nazione1, nazione2);
		}
		ispettore = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
	}

	public List<Country> getCountries() {
		return this.nazioni;
	}

	public Map<Country, Integer> getCountryCounts() {
		Map<Country, Integer> gradi = new HashMap<Country, Integer>();
		for(Country c : grafo.vertexSet()) {
			gradi.put(c, grafo.edgesOf(c).size());
		}
		return gradi;
	}

	public int getNumberOfConnectedComponents() {
		return ispettore.connectedSets().size();
	}
	
	public Set<Country> getNazioniRaggiungibili(Country start){
		Set<Country> result = new HashSet<Country>();
		BreadthFirstIterator<Country, DefaultEdge> i = new BreadthFirstIterator<Country, DefaultEdge>(grafo, start);
		
		while(i.hasNext() != false) {
			result.add(i.next());
		}
		
		result.remove(start);
		return result;
	}

}
