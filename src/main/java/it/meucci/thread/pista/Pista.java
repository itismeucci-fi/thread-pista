package it.meucci.thread.pista;

import java.util.concurrent.Semaphore;

/**
 * Rappresenta la pista
 * @author 
 *
 */
public class Pista {
	
	/**
	 * Semaforo per regolare l'accesso alla pista
	 */
	private Semaphore accessoPista;
	
	/**
	 * Il costruttore accetta in input il numero di piloti che possono gareggiare contemporaneamente
	 * @param numPosti
	 */
	public Pista(int numPosti) {
		/* 
		 * inizializzo il semaforo con il numero di posti disponibili, 
		 * il secondo parametro true garantisce che la coda dei piloti in attesa venga smaltita in ordine
		 */
		accessoPista = new Semaphore(numPosti, true);
	}
	
	/**
	 * 
	 * @param nomePilota
	 * @param numGiri
	 * @return il tempo con il quale il pilot ha percorso il numero dei giri rihiesti
	 * @throws InterruptedException
	 */
	public long gareggia(String nomePilota, int numGiri) throws InterruptedException {
		
		// utilizza il semaforo per richiedere l'accesso alla pista
		accessoPista.acquire();
		
		System.out.println("Il pilota " + nomePilota + " entra in pista -- posti liberi in pista: " + accessoPista.availablePermits());
		
		long tempo = System.currentTimeMillis();
		
		// percorre il numero di giri richiesti
		for (int i = 0; i < numGiri; i++) {
			Thread.sleep((int)(Math.random() * 2000));
			System.out.println("Il pilota " + nomePilota + " ha percorso il giro n " + (i+1));
		}
		
		tempo = System.currentTimeMillis() - tempo;
		System.out.println("Il pilota " + nomePilota + " lascia la pista con il tempo " + tempo);
		
		// rilascia il semaforo per l'accesso alla pista
		accessoPista.release();

		// ritorna il tempo del pilota
		return tempo;
	}

}
