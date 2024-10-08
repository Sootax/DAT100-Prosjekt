package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		int secs;
		int hr, min, sec;
		hr = Integer.parseInt(timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX+2));
		min = Integer.parseInt(timestr.substring(TIME_STARTINDEX+3, TIME_STARTINDEX+5));
		sec = Integer.parseInt(timestr.substring(TIME_STARTINDEX+6, TIME_STARTINDEX+8));
		min += hr * 60;
		sec += min * 60;
		return sec;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
        return new GPSPoint(time, latitude, longitude, elevation);
	}
	
}
