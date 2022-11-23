package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<Nerc>nercList= model.getNercList()	;	
		System.out.println(nercList);

		Nerc nerc= nercList.get(3);
		System.out.println(model.getWorstCase(3, 15, nerc));
	}

}
