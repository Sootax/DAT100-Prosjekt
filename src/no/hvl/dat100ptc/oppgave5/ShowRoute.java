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

		// replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {
		setColor(0, 0, 255);
		int radius = 2;

		GPSPoint[] gpspoints = gpscomputer.getGPSPoints();
		int xPrev = MARGIN + (int)((gpspoints[0].getLongitude() - minlon) * xstep);
		int yPrev = MARGIN + (int)((maxlat - gpspoints[0].getLatitude()) * ystep);

		for (int i = 0; i < gpspoints.length; i++) {
			int x = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
			int y = MARGIN + (int)((maxlat - gpspoints[i].getLatitude()) * ystep);

			if (i > 0) {
				drawLine(xPrev, yPrev, x, y);
			}
			fillCircle(x, y, radius);
			xPrev = x;
			yPrev = y;
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);

		int totalTime = gpscomputer.totalTime();
		int hours = totalTime / 3600;
		int minutes = totalTime % 3600 / 60;
		int seconds = totalTime % 60;
		String timeStr = hours + ":" + minutes + ":" + seconds;

		
		drawString("Total Time: " + timeStr, 10, 10);
		
	}

	public void replayRoute(int ybase) {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
