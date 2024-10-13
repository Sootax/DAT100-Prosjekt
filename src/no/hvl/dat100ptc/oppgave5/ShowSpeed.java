package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN;
		double[] speeds = gpscomputer.speeds();
		double avgSpeed = gpscomputer.averageSpeed();
			
		// Tegner hver høyde i grafikk vinduet
		for (int i = 0; i < speeds.length; i++) {
		
			int y = (int) speeds[i] * 5;
			
			drawLine(x, ybase, x, ybase - y);
			
			x += 2;
				
		}
		
		// Tegner rektangel
		int xPos = MARGIN - 10;
		int yPos = MARGIN - 20;
		int bredde = 2 * MARGIN + 2 * speeds.length - 80;
		int høyde = 2 * MARGIN + BARHEIGHT - 70;
		
		// Tegner gjennomsnittsfart som grønn linje
		setColor(0, 0, 255);
		drawRectangle(xPos, yPos, bredde, høyde);
		
		setColor(57, 255, 20);
		drawLine(MARGIN, ybase - (int) avgSpeed * 5, x, ybase - (int) avgSpeed * 5);
			
	}
}
