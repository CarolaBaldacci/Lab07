package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	private List<PowerOutages> eventList;
	private List<PowerOutages> soluzione;
	private List<Nerc> nercList;
	
	private int maxAffectedPeople;
	
	public Model() {
		podao = new PowerOutageDAO();
		nercList=podao.getNercList();
	}
	
	public List<Nerc> getNercList() {
		return this.nercList;
	}

	
	public List<PowerOutages> getWorstCase(int maxNumberOfYear, int maxHourOfOutages, Nerc nerc){
		//inizializzazione
		soluzione=new ArrayList<>();
		maxAffectedPeople=0;
		eventList = podao.getPowerOutagesByNerc(nerc);
		//Collections.sort(eventList);
		//Ricorsione
		search(new ArrayList<PowerOutages>(),maxNumberOfYear, maxHourOfOutages);
		return soluzione;
		
	}
	
	public void search(ArrayList<PowerOutages> parziale, int maxNumberOfYear, int maxHourOfOutages) {
		//caso terminale
		if(sumAffectedPeople(parziale)>maxAffectedPeople) {
			maxAffectedPeople=sumAffectedPeople(parziale);
			soluzione=new ArrayList<PowerOutages>(parziale);
		}else {
			for(PowerOutages event: eventList) {
				if(!parziale.contains(event)) {
					parziale.add(event);
					if(maxYear(parziale,maxNumberOfYear) && 
							maxHour(parziale,maxHourOfOutages)) {
						search(parziale, maxNumberOfYear, maxHourOfOutages);
						
					}
					parziale.remove(event);
				}
			}
		}
	}
	
	public boolean maxYear(ArrayList<PowerOutages> parziale, int maxNumberOfYear) {
		if(parziale.size()>=2) {
			//differenza tra gli anni dell'evento più recente e
			//quello più vecchio <maxNumberOfYear
			int y1= parziale.get(0).getYear();
			int y2= parziale.get(parziale.size()-1).getYear();
			if((y2-y1)>maxNumberOfYear)
				return false;
		}
		return true;
	}
	
    public boolean maxHour(ArrayList<PowerOutages> parziale, int maxHourOfOutages) {
		int sum= sumOutageHours(parziale);
		if(sum>maxHourOfOutages) {
			return false;
		}
		return true;
	}
    
    public int sumAffectedPeople(ArrayList<PowerOutages> parziale) {
    	int sum=0;
    	for(PowerOutages event: parziale)
    		sum+= event.getCustomers_affected();
    	return sum;
    }
    
    public int sumOutageHours(List<PowerOutages> parziale) {
    	int sum=0;
    	for(PowerOutages event: parziale)
    		sum+= event.getOutageDuration();
    	return sum;
    }
	

}
