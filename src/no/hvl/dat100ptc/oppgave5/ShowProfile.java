package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static final int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {
		
		int x = MARGIN; // første høyde skal tegnes ved MARGIN
		
		int maxElevation = 0;
		int pointsCount = gpspoints.length;
		
		// Finner høyeste punkt for å justere grafikkvindu		
		for ( GPSPoint point : gpspoints ) {
			if (point.getElevation() > maxElevation) {
				maxElevation = (int) point.getElevation();
			}
		}
			
		// Angir str på grafikkvindu basert på høyeste punkt og antall punkt.
		
		int windowX = pointsCount * 2 + MARGIN * 2;
		int windowY = maxElevation + MARGIN * 2;
		
		makeWindow("Grafikk", windowX, windowY);
		
		
		// Justerer ybase så det ikke blir mye tomrom over høydeprofilen
		ybase = maxElevation + MARGIN;
		
		// Tegner hver høyde i grafikk vinduet
		for (int i = 0; i < gpspoints.length; i++) {
		
			int y = (int) gpspoints[i].getElevation();
			
			// Håndterer negative verdier
			if (y < 0) { 
				y = 0;
			}				
			
			drawLine(x, ybase, x, ybase - y);
			
			x += 2;
			
		}
		
	}

}
