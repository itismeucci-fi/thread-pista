package it.meucci.thread.pista;

import java.util.TreeMap;

public class PilotaThread extends Thread {

	private TreeMap<Long, String> classifica;
	private Spogliatoio spogliatoio;
	private Pista pista;

	public PilotaThread(String name, TreeMap<Long, String> c, Spogliatoio s, Pista p) {
		super(name);
		this.classifica = c;
		this.spogliatoio = s;
		this.pista = p;
	}

	@Override
	public void run() {

		try {
			//il pilota indossa la tuta prima di entrare in pista
			spogliatoio.indossaTuta(getName());

			//il pilota percorre i giri
			long tempo = pista.gareggia(getName(), 15);
			//salvo il tempo nella classifica
			classifica.put(tempo, getName());
			
			//il pilota indossa i propri indumenti
			spogliatoio.indossaIndumenti(getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
