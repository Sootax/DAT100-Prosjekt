package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		this.gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return gpspoints;
	}
	
	public double totalDistance() {
		double distance = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
	}

	public double totalElevation() {
		double elevation = 0;
		for (int i = 1; i < gpspoints.length; i++) {
			double diff = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			if (diff > 0) {
				elevation += diff;
			}
		}
		return elevation;
	}

	public int totalTime() {
		int time = 0;
		for (int i = 1; i < gpspoints.length; i++) {
			time += gpspoints[i].getTime() - gpspoints[i - 1].getTime();
		}
		return time;
	}
		

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length-1];
		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		double[] speeds = speeds();
		return GPSUtils.findMax(speeds);
	}

	public double averageSpeed() {
		double totalDistance = totalDistance();
		double totalTime = totalTime();
		return totalDistance / totalTime;
	}

	// Torgeir
	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	// Torgeir
	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	// Torgeir
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
