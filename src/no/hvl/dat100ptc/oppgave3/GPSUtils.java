package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudes = new double[gpspoints.length];
		int posisjon = 0;
		
		for (GPSPoint gpspoint : gpspoints) {
			latitudes[posisjon] = gpspoint.getLatitude();
			posisjon++;
		}
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudes = new double[gpspoints.length];
		int posisjon = 0;
		
		for (GPSPoint gpspoint : gpspoints) {
			longitudes[posisjon] = gpspoint.getLongitude();
			posisjon++;
		}
		return longitudes;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
		
		double phi1 = toRadians(latitude1);
		double phi2 = toRadians(latitude2);
		double deltaphi = toRadians(latitude2 - latitude1);
		double deltadelta = toRadians(longitude2 - longitude1);
		
		double a = compute_a(phi1, phi2, deltaphi, deltadelta);
		double c = compute_c(a);
		
		d = R * c;
		
		return d;
				
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		double a = pow(sin(deltaphi/2), 2) + cos(phi1) * cos(phi2) * pow((sin(deltadelta/2)), 2);
		
		return a;
	}

	private static double compute_c(double a) {
		
		double c = 2 * atan2(sqrt(a), sqrt(1-a));
		
		return c;
		
	}

	// Torgeir jobber med denne
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO

	}
	
	// Torgeir jobber med denne
	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		throw new UnsupportedOperationException(TODO.method());
		
		// TODO 
		
	}
	
	// Torgeir jobber med denne
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		
	}
}
