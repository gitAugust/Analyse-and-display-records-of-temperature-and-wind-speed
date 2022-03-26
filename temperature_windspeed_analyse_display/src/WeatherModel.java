
//import libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: Analysis of weather records
 * @className: WeatherModel
 * @description: Get the data for the selected year from URL
 * @author: Yunlong MA
 * @createDate: 15/12/2020
 * @version: v0.0
 */
public class WeatherModel {
	private List<Double> daylist = new ArrayList<Double>();
	private List<Double> timelist = new ArrayList<Double>();
	private List<Double> windspeedlist = new ArrayList<Double>();
	private List<Double> temperaturelist = new ArrayList<Double>();

	public Double startday;
	public Double endday;
	public int total;
	public List<Double> subdaylist;
	public List<Double> subwindspeedlist;
	public List<Double> subtemperaturelist;
	/**
	 * @param year: the selected year which will be analysised
	 */
	WeatherModel(int year) {
		String url = geturl(year);
		try {
			URL WeatherURL = new URL(url);
			URLConnection weatherConnection = WeatherURL.openConnection();
			InputStreamReader weatherReader = new InputStreamReader(weatherConnection.getInputStream());
			BufferedReader weatherBuffer = new BufferedReader(weatherReader);
			String line = "";

			while ((line = weatherBuffer.readLine()) != null) {
				Double spliteddata[] = new Double[4];
				if (line.length() < 20) {//Exclude the rows contain no data
					continue;
				}
				String[] dataString = new String[9];
				if (year == 2006) {//the data formae is different in year 2006
					dataString = line.split("	");
				} else {
					dataString = line.split(",");
				}
				if (dataString.length < 9) {
					continue;
				}
				spliteddata = splitdata(dataString, year);

				daylist.add(spliteddata[0]);
				timelist.add(spliteddata[1]);
				windspeedlist.add(spliteddata[2]);
				temperaturelist.add(spliteddata[3]);
			}
		} catch (IOException e) {
			System.out.printf("The data for the selected year: " + "%d is missing.\n", year);
		}
	}

	/**
	 * @apiNote: Get the corresponding URL for the selected year
	 * @param year: the selected year
	 * @return url: the corresponding URL
	 */
	private String geturl(int year) {
		String[] urldictionary = {
				"https://cairngormweather.eps.hw.ac.uk/1999/data1999.txt",
				"https://cairngormweather.eps.hw.ac.uk/2000/data2000.txt",
				"https://cairngormweather.eps.hw.ac.uk/2001/data2001.txt",
				"https://cairngormweather.eps.hw.ac.uk/2002/data2002.txt",
				"https://cairngormweather.eps.hw.ac.uk/2003/data2003.txt",
				"https://cairngormweather.eps.hw.ac.uk/2004/data2004.txt",
				"https://cairngormweather.eps.hw.ac.uk/2005/data2005.txt",
				"https://cairngormweather.eps.hw.ac.uk/2006/YEARDATA2006.txt",
				"https://cairngormweather.eps.hw.ac.uk/2007/YEARDATA2007.txt",
				"https://cairngormweather.eps.hw.ac.uk/2008/YEARDATA2008.txt",
				"https://cairngormweather.eps.hw.ac.uk/2009/2009.txt",
				"https://cairngormweather.eps.hw.ac.uk/2010/2010%20Data.txt",
				"https://cairngormweather.eps.hw.ac.uk/2011/2011%20Data.txt",
				"https://cairngormweather.eps.hw.ac.uk/2012/Data%202012.txt",
				"https://cairngormweather.eps.hw.ac.uk/2013/CurrentYear.DAT",
				"https://cairngormweather.eps.hw.ac.uk/2014/YEARDATA2014.DAT",
				"https://cairngormweather.eps.hw.ac.uk/2015/YEARDATA2015.txt",
				"https://cairngormweather.eps.hw.ac.uk/2016/YEARDATA%202016.txt",
		};
		String url = urldictionary[year - 1999];
		return url;
	}

	/**
	 * @apiNote: split the data for the selected year into day, time, windspeed and
	 *           temperature
	 * @param dataString: the selected string containing the different data
	 * @param year:       the year for the data, this param is needed since the data
	 *                    forme is different from year to year
	 * @return spliteddata: the splited data in a [],[day time windspeed temperature]
	 */
	private Double[] splitdata(String[] dataString, int year) {
		Double spliteddata[] = new Double[4];
		if (year < 2007) {
			spliteddata[0] = Double.parseDouble(dataString[1].trim());
			spliteddata[1] = Double.parseDouble(dataString[2].trim());
			spliteddata[2] = Double.parseDouble(dataString[3].trim());
			spliteddata[3] = Double.parseDouble(dataString[8].trim());//use temperature data from the first sensor
		} else {
			spliteddata[0] = Double.parseDouble(dataString[0].trim());
			spliteddata[1] = Double.parseDouble(dataString[1].trim());
			spliteddata[2] = Double.parseDouble(dataString[2].trim());
			spliteddata[3] = Double.parseDouble(dataString[7].trim());
		}
		return spliteddata;//[day time windspeed temperature]
	}

	/**
	 * @apiNote: get the private data
	 * @return spliteddata: the list of day
	 */
	public List<Double> getday() {
		return this.daylist;
	}

	/**
	 * @apiNote: get the private data
	 * @return spliteddata: the list of time
	 * @deprecated: unused in this project
	 */
	public List<Double> gettime() {
		return this.timelist;
	}

	/**
	 * @apiNote: get the private data
	 * @return spliteddata: the list of windspeed
	 */
	public List<Double> getwindspeed() {
		return this.windspeedlist;
	}

	/**
	 * @apiNote: get the private data
	 * @return spliteddata: the list of temperature
	 */
	public List<Double> gettemperature() {
		return this.temperaturelist;
	}

	public void setstartday(Double startday){
		this.startday=startday;
	}

	public void setendday(Double endday){
		this.endday=endday;
	}

	public void settotal(int total){
		this.total=total;
	}

	public void setsubwindspeed(List<Double> subwindspeedlist){
		this.subwindspeedlist=subwindspeedlist;
	}

	public void setsubtemperature(List<Double> subtemperaturelist){
		this.subtemperaturelist=subtemperaturelist;
	}

	public void setsubdaylist(List<Double> subdaylist){
		this.subdaylist=subdaylist;
	}
	
}
