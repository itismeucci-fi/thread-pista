package it.meucci.thread.pista;

import java.util.concurrent.Semaphore;

/**
 * Spogliatorio
 * @author 
 *
 */
public class Spogliatoio {

	/**
	 * Semaforo per regolare l'accesso allo spogliatorio
	 */
	private Semaphore accessoSpogliatoio;
	
	/**
	 * Il costruttore accetta in input il numero di piloti che possono entrare contemporaneamente nello spogliatoio
	 * @param numPosti
	 */
	public Spogliatoio(int numPosti) {
		/* 
		 * inizializzo il semaforo con il numero di posti disponibili, 
		 * il secondo parametro true garantisce che la coda dei piloti in attesa venga smaltita in ordine
		 */
		accessoSpogliatoio = new Semaphore(numPosti, true);
	}
	
	/**
	 * Utilizato dal pilota per indossare la tuta
	 * @param nomePilota
	 * @throws InterruptedException
	 */
	public void indossaTuta(String nomePilota) throws InterruptedException {
		// utilizza il semaforo per richiedere l'accesso allo spogliatoio
		accessoSpogliatoio.acquire();
		
		System.out.println("Il pilota " + nomePilota + " entra nello spogliatoio per indossare la tuta -- posti liberi nello spogliatoio: " + accessoSpogliatoio.availablePermits());
		
		Thread.sleep(1000);

		System.out.println("Il pilota " + nomePilota + " esce dallo spogliatoio dopo aver indossato la tuta");
		
		// rilascia il semaforo per l'accesso allo spogliatoio
		accessoSpogliatoio.release();
	}

	
	/**
	 * Utilizato dal pilota per indossare i propri indumenti
	 * @param nomePilota
	 * @throws InterruptedException
	 */
	public void indossaIndumenti(String nomePilota) throws InterruptedException {
		// utilizza il semaforo per richiedere l'accesso allo spogliatoio
		accessoSpogliatoio.acquire();
		
		System.out.println("Il pilota " + nomePilota + " entra nello spogliatoio per indossare i propri indumenti -- posti liberi nello spogliatoio: " + accessoSpogliatoio.availablePermits());
		
		Thread.sleep(1000);

		System.out.println("Il pilota " + nomePilota + " esce dallo spogliatoio dopo aver indossato i propri indumenti");

		// rilascia il semaforo per l'accesso allo spogliatoio		
		accessoSpogliatoio.release();
	}

}
