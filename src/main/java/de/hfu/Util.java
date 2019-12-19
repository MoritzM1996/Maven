package de.hfu;

public class Util {

	public static boolean istErstesHalbjahr(int monat){
		if ((monat < 1) || (monat > 12)) throw new IllegalArgumentException("Fehler");
		if(monat < 7) return true;
		return false;
	}

}
