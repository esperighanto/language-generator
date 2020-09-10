package com;
import java.util.Random;
import java.util.ArrayList;

public class Phonology {
	//I need an inventory that holds two arrays, one consonants and one vowels.
	//This inventory will be an array that holds two other arrays.
	//For now these will be individual characters.
	
	String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "s", "t", "v", "w", "x", "z"};
	String[] vowels = {"a", "e", "i", "o", "u", "y"};
	ArrayList<String> consonantInv = new ArrayList<String>();
	ArrayList<String> vowelInv = new ArrayList<String>();
	ArrayList<Integer> usedConsonants = new ArrayList<Integer>();
	ArrayList<Integer> usedVowels= new ArrayList<Integer>();
	int constAmt, vowAmt;
	
	public Phonology(int constAmt, int vowAmt) {
		
		//If constAmt or vowAmt are larger than their respective inventory arrays, then throw an error.
		if(constAmt > consonants.length) {
			throw new IllegalArgumentException("Consonant amount is larger than consonantal inventory.");
		}
		
		if(vowAmt > vowels.length) {
			throw new IllegalArgumentException("Vowel amount is larger than vocalic inventory.");
		}

		ArrayList<Integer> consonantsToMap = randomNumberArray(constAmt, consonants.length);
		ArrayList<Integer> vowelsToMap = randomNumberArray(vowAmt, vowels.length);
		
		for(int i = 0; i < constAmt; i++) {
			consonantInv.add(i, consonants[consonantsToMap.get(i)]);
//			System.out.println("Adding the consonant " + consonantInv.get(i) + " on step " + i + " from index " + consonantsToMap.get(i));
		}
		
		for(int i = 0; i < vowAmt; i++) {
			vowelInv.add(i, vowels[vowelsToMap.get(i)]);
//			System.out.println("Adding the vowel " + vowelInv.get(i) + " on step " + i + " from index " + vowelsToMap.get(i));
		}
		
	}

	public static ArrayList<Integer> randomNumberArray(int amtToGenerate, int maximumNumber) {
		int generatedNums = 0;
		ArrayList<Integer> usedNums = new ArrayList<Integer>();
		ArrayList<Integer> numbersList = new ArrayList<Integer>();
		Random r = new Random();
		while(generatedNums < amtToGenerate) {
			int potentialNum = r.nextInt(maximumNumber - 1);
			boolean isTaken = false;
//			System.out.println("usedNums.size() is " + usedNums.size());
			for(int i = 0; i < usedNums.size(); i++) {
				if(potentialNum == usedNums.get(i)) {
					isTaken = true;
				}
			}
			if(isTaken == true) {
				continue;
			} else {
				numbersList.add(potentialNum);
				usedNums.add(potentialNum);
				generatedNums += 1;
			}
		}
		
		return numbersList;
	}
	
	@Override
	public String toString() {
		String toReturn = "Consonant inventory: ";

		if(consonantInv.size() > 0) {
			for(int i = 0; i < consonantInv.size(); i++) {
				if(i < consonantInv.size()-1) {
					toReturn = toReturn + consonantInv.get(i) + ", ";
				} else {
					toReturn = toReturn + consonantInv.get(i);
				}
			}
		} else {
			toReturn += "NULL";
		}
		
		
		toReturn = toReturn + ".\nVocalic inventory: ";
		
		if(vowelInv.size() > 0) {
			for(int i = 0; i < vowelInv.size(); i++) {
				if(i < vowelInv.size()-1) {
					toReturn = toReturn + vowelInv.get(i) + ", ";
				} else {
					toReturn = toReturn + vowelInv.get(i);
				}
			}
		} else {
			toReturn += "NULL";
		}
		
		toReturn = toReturn + ".";
		
		return toReturn;
	}
	
	static public void main(String[] args) {
		Phonology p = new Phonology(10, 5);
		System.out.println(p.toString());
		PhonotacticConstraints pc = new PhonotacticConstraints(p, 5);
		System.out.println("The phonotactic constriants are: " + pc.constraintsToString() + ".");
		WordGenerator wg = new WordGenerator(p, pc, 9);
		System.out.println(wg.dictionaryToString() + " with " + wg.dictionarySize + " words.");
	}
}