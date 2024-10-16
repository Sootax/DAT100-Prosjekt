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
		
		for ( GPSPoint point : gpspoints ) {
			if (point.getElevation() > maxElevation) {
				maxElevation = (int) point.getElevation();
			}
		}
		
		setColor(0, 0, 255);
		
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
		
		// Tegner rektangel
		int xPos = MARGIN - 10;
		int yPos = ybase - (maxElevation + MARGIN - 20);
		int bredde = 2 * MARGIN + 2 * gpspoints.length - 80;
		int høyde = 2 * MARGIN + maxElevation - 60;
		
		drawRectangle(xPos, yPos, bredde, høyde);
		
	}

}
