package eu.impersonator.wisdomgenerator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import eu.impersonator.chucknorris.ChuckNorrisImpl;
import eu.impersonator.interfaces.ChuckNorris;
import eu.impersonator.interfaces.ChuckNorrisListener;

public class WisdomPrinter implements ChuckNorrisListener, Runnable,
		BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting.");
		new Thread(this).start();
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping now.");

	}

	public void run() {
		ChuckNorris chuck = new ChuckNorrisImpl();
		chuck.subscribe(this);
	}

	public void notifyTruth(String truth) {
		System.out.println("\n\n" + truth);
	}
}
