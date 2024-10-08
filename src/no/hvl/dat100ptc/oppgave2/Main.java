package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {
	public static void main(String[] args) {
		GPSPoint gpsPoint1 = new GPSPoint(50, 1.0, 2.0, 5.0);
		GPSPoint gpsPoint2 = new GPSPoint(60, 4.0, 6.0, 2.0);
		GPSData gpsData1 = new GPSData(2);
		gpsData1.insertGPS(gpsPoint1);
		gpsData1.insertGPS(gpsPoint2);
		gpsData1.print();
	}
}
