package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {
		setColor(0, 255, 0);
		int radius = 3;

		GPSPoint[] gpspoints = gpscomputer.getGPSPoints();
		int xPrev = MARGIN + (int)((gpspoints[0].getLongitude() - minlon) * xstep);
		int yPrev = ybase - (int)((gpspoints[0].getLatitude() - minlat) * ystep);

		for (int i = 1; i < gpspoints.length; i++) {
			int x = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
			int y = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);

			drawLine(xPrev, yPrev, x, y);
			fillCircle(x, y, radius);

			xPrev = x;
			yPrev = y;
		}
	}

	public void showStatistics() {
		setColor(0, 0, 0);
		gpscomputer.displayStatistics();
		String[] statistics = gpscomputer.statistics;

		int labelX = 10;
		int colonX = 120;
		int valueX = 150;
		int startY = 10;
		int lineHeight = 20;

		for (int i = 1; i < statistics.length - 1; i++) {
			String[] parts = statistics[i].split(":\\s+");
			drawString(parts[0], labelX, startY + i * lineHeight);
			drawString(":", colonX, startY + i * lineHeight);
			if (parts.length > 1) {
				drawString(parts[1], valueX, startY + i * lineHeight);
			}
		}
	}

	public void replayRoute(int ybase) {

		

		GPSPoint[] gpspoints = gpscomputer.getGPSPoints();
		int xPrev = MARGIN + (int)((gpspoints[0].getLongitude() - minlon) * xstep);
		int yPrev = ybase - (int)((gpspoints[0].getLatitude() - minlat) * ystep);
		
		int radius = 5;
		setColor(0, 0, 255);
		int nodeId = fillCircle(xPrev, yPrev, radius);
		
		setSpeed(10);
		
		for (int i = 0; i < gpspoints.length; i++) {
			
			int x = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
			int y = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);
			
			setColor(0, 0, 255);
			moveCircle(nodeId, x, y);	
			pause(50);
		}

	}
}
