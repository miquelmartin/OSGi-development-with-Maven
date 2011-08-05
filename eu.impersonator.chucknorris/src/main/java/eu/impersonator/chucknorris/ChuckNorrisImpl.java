package eu.impersonator.chucknorris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eu.impersonator.interfaces.ChuckNorris;
import eu.impersonator.interfaces.ChuckNorrisListener;

//This is NOT a service

public class ChuckNorrisImpl implements Runnable, ChuckNorris {
	private Random rand = new Random();
	private List<ChuckNorrisListener> listeners = new ArrayList<ChuckNorrisListener>();
	private boolean done = false;

	public ChuckNorrisImpl() {
		// Note: because we are not using services yet, we can't use an
		// activator and register a service, so we sart it all manually
		new Thread(this).start();
	}

	public void stop() {
		done = true;
	}

	public void subscribe(ChuckNorrisListener listener) {
		listeners.add(listener);
	}

	public synchronized void run() {
		
		while (!done) {
			try {
				this.wait(rand.nextInt(6000));
			} catch (InterruptedException e) {
				System.err.println("Chuck Norris got interrupted");
			}
			
			for(ChuckNorrisListener l : listeners){
				l.notifyTruth(Factopedia.getRandomFact());
			}
		}
	}
}
