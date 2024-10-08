package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSData {
	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		this.gpspoints = new GPSPoint[antall];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return true;
		}

		return false;
	
	}

	public boolean insert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		GPSPoint gpspoint = GPSDataConverter.convert(timeStr, latitudeStr, longitudeStr, elevationStr);
		return insertGPS(gpspoint);
	}

	public void print() {
		System.out.println("====== GPS Data - START ======");
		for (int i = 0; i < gpspoints.length; i++) {
			System.out.printf((i+1) + " (%.1f, %.1f) %.1f%n",
					gpspoints[i].getLatitude(),
					gpspoints[i].getLongitude(),
					gpspoints[i].getElevation());
		}
		System.out.println("====== GPS Data - SLUTT ======");
	}
}
