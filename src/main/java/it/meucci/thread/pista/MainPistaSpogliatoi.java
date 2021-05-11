package it.meucci.thread.pista;

import java.util.Map;
import java.util.TreeMap;

public class MainPistaSpogliatoi {
	
	final static int NUM_POSTI_IN_PISTA = 4;
	final static int NUM_POSTI_SPOGLIATOIO = 2;
	
	final static String[] nomiPiloti = new String[] {"Bagnaia", "Miller", "Bastianini", "Espargaro", "Marquez", "Rossi", "Petrucci", "Morbidelli"};

	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * La classifica è rappresentata da un HashMap
		 * ----------------------------
		 *  tempo1    | nome pilota
		 *  tempo2    | nome pilota
		 *  tempo3    | nome pilota
		 *  ......    | nome pilota
		 *  tempo8    | nome pilota
		 *  ---------------------------
		 * - La chiave della Hashmap rappresenta il tempo, 
		 * - Il valore rappresenta il nome del pilota
		 * In particolare estendo la TreeMap poichè ordina gli elementi per il valore della chiave
		 */		
		TreeMap<Long, String> classifica = new TreeMap<Long, String>();
		Spogliatoio spogliatoio = new Spogliatoio(NUM_POSTI_SPOGLIATOIO);
		Pista pista = new Pista(NUM_POSTI_IN_PISTA);
		
		PilotaThread[] piloti = new PilotaThread[nomiPiloti.length];
		
		// inizializzo e avvio i thread
		for (int i = 0; i < nomiPiloti.length; i++) {
			String nomePilota = nomiPiloti[i];
			piloti[i] = new PilotaThread(nomePilota, classifica, spogliatoio, pista);
			piloti[i].start();
		}
		
		// attendo la conclusione dei giri
		for (int i = 0; i < nomiPiloti.length; i++) {
			piloti[i].join();
		}
		
		
		// stampo la classifica
		System.out.println("------- CLASSIFICA -------");
		int posizione = 1;
		for (Map.Entry<Long, String> e : classifica.entrySet()) {
			System.out.println(posizione + " " + e.getValue() + " tempo: " + e.getKey() + "ms");
			posizione++;
		}

	}
	
}
