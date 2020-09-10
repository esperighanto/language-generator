package com;

import java.util.ArrayList;
import java.util.Random;

public class PhonotacticConstraints {
	ArrayList<String> constraints = new ArrayList<String>(); //Phonotactic constraints will be contained as an ArrayList<String> called constraints, containing:
		//c, C: Lower case is optional consonant, upper case is required consonant.
		//v, V: Lower case is optional cowel, upper case is required vowel.
	//Take amount of bi-clusters to make, amount of tri-clusters to make, 
	
	public PhonotacticConstraints(Phonology p, ArrayList<String> constraint) {
		//
	}
	
	public boolean randomBoolean() {
		Random r = new Random();
		if(r.nextInt(2) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public String constraintsToString() {
		String toReturn = "";
		
		for(int i = 0; i < constraints.size(); i++) {
			toReturn += constraints.get(i);
		}
		
		return toReturn;
	}
	
	public PhonotacticConstraints(Phonology p, int amtOfConstraints) {
//		int amtOfConstraints = 5; // This is the amount of random constraints to create.
		int lowerCaseCount = 0;
		int upperCaseCount = 0;
		
		for(int i = 0; i < amtOfConstraints; i++) {
			if(randomBoolean()) { // Consonant.
				if(randomBoolean()) { // Upper case.
					constraints.add("C");
				} else { // Lower case.
					constraints.add("c");
				}
			} else { // Vowel.
				if(randomBoolean()) { // Upper case.
					constraints.add("V");
				} else { // Lower case.
					constraints.add("v");
				}
			}
		}
		
		//Check if all entries are either consonant or vowel, if so then diverge a random number between 0 and the floor of half of constraints.size()-1.
			//Would ceiling be better here? Would it really make a difference honestly? Hell I might even randomly select whether or not to floor or ceiling it, haha.
		for(int i = 0; i < constraints.size(); i++) {
			if(Character.isLowerCase(constraints.get(i).charAt(0))) {
				lowerCaseCount += 1;
			} else {
				upperCaseCount += 1;
			}
		}
		
		//System.out.println("Lower case count is " + lowerCaseCount);
		//System.out.println("Upper case count is " + upperCaseCount);
		
		if(lowerCaseCount == 0 | upperCaseCount == 0) { //Check if all entries in constraints are lowercase. If so, then select a random number from 0 to constraints.size(), and capitalize it.
			Random r = new Random(); //All were lower or upper case.
			int entryToUpper = r.nextInt((constraints.size() - 0) + 1);
			constraints.set(entryToUpper, constraints.get(entryToUpper).toUpperCase());
			//System.out.println("All were optional, so entry " + entryToUpper + " has been made mandatory.");
		}
		
		boolean vowelCheck = false;
		for(int i = 0; i < constraints.size(); i++) {//Check the phonotactic constraints to make sure that there is at least one mandatory vowel in it.
			if(constraints.get(i).equals("V")) {
				vowelCheck = true;
			}
		}
		
		if(vowelCheck == false) { //If there is no mandatory vowel, then 
			if(randomBoolean()) {
				constraints.add((int) Math.ceil(constraints.size()/2), "V");
			} else {
				constraints.add((int) Math.floor(constraints.size()/2), "V");
			}
		}
	}
}
