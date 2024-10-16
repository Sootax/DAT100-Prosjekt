package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	private GPSPoint[] gpspoints;
	public String[] statistics;
	
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

	
	// conversion factor m/s to miles per hour (mph)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;
		
		double timeInHours = secs / 3600.00;
		
		if (speedmph < 10)
			met = 4.0;
		
		if (speedmph > 10 && speedmph <= 12)
			met = 6.0;
		
		if (speedmph > 12 && speedmph <= 14)
			met = 8.0;
		
		if (speedmph > 14 && speedmph <= 16)
			met = 10.0;
		
		if (speedmph > 16 && speedmph <= 20)
			met = 12.0;
		
		if (speedmph > 20)
			met = 16.0;
		
		kcal = met * weight * timeInHours;
		
		return kcal;
	
	}
	
	
	public double totalKcal(double weight) {
		
		double totalKcal = 0;
		
		double[] speeds = speeds();		
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			double gpsPointSpeed = speeds[i];
			
			totalKcal += kcal(weight, secs, gpsPointSpeed);
		}
		
		return totalKcal;
		
		
	}
	
	
	private static double WEIGHT = 80.0;
	private static String BORDER = "=".repeat(35);

	public void displayStatistics() {
		this.statistics = new String[]{
				BORDER,
				String.format("Total Time     : %s", GPSUtils.formatTime(totalTime())),
				String.format("Total Distance : %7s km", String.format("%.2f", totalDistance() / 1000)),
				String.format("Total Elevation: %7s m", String.format("%.2f", totalElevation())),
				String.format("Max Speed      : %7s km/t", String.format("%.2f", maxSpeed() * 3.6)),
				String.format("Average Speed  : %7s km/t", String.format("%.2f", averageSpeed() * 3.6)),
				String.format("Energy         : %7s kcal", String.format("%.2f", totalKcal(WEIGHT))),
				BORDER
		};
		for (int i = 0; i < statistics.length; i++) {
			System.out.println(statistics[i]);
		}
	}
}
