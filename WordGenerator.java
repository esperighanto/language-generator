package com;

import java.util.Random;

public class WordGenerator {
	
	String[] dictionary;
	int dictionarySize;
	
	public int randomInBounds(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public WordGenerator(Phonology p, PhonotacticConstraints pc, int dictionarySize) {
		this.dictionarySize = dictionarySize;
		dictionary = new String[dictionarySize];
		for(int i = 0; i < dictionarySize; i++) { // Populate the dictionary by following the phonotactic constraints.
			dictionary[i] = "";
			
			for(int j = 0; j < pc.constraints.size(); j++) {//Go through each entry in pc.constraints, and if it's lower case, get a random boolean as to whether or not to place the character there, and if it's upper case, then definitely give it a random entry from the respective consonantal/vocalic inventory.
				if(pc.constraints.get(j).equals("c")) {
					if(pc.randomBoolean()) {
//						System.out.println("Adding a letter to " + dictionary[i] + " at place " + i);
						dictionary[i] += p.consonants[randomInBounds(0,p.consonants.length-1)];
					}
				} else if(pc.constraints.get(j).equals("C")) {
					dictionary[i] += p.consonants[randomInBounds(0,p.consonants.length-1)];
				} else if(pc.constraints.get(j).equals("v")) {
					if(pc.randomBoolean()) {
						dictionary[i] += p.vowels[randomInBounds(0,p.vowels.length-1)];
					}
				} else if(pc.constraints.get(j).equals("V")) {
					dictionary[i] += p.vowels[randomInBounds(0,p.vowels.length-1)];
				}
			}
		}
	}
	
	public WordGenerator(Phonology p, PhonotacticConstraints pc) {
		
	}
	
	public String dictionaryToString() {
		String toReturn = "Dictionary: ";
		
		for(int i = 0; i < dictionary.length; i++) {
			if(i < dictionary.length - 1) {
				toReturn += dictionary[i] + ", ";
			} else {
				toReturn += dictionary[i];
			}
		}
		
		return toReturn;
	}
}
