
//import libraries
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * @projectName: Analysis of weather records
 * @className: WeatherView
 * @description: Process the data from WeatherModel and show
 * @author: Yunlong MA
 * @createDate: 15/12/2020
 * @version: v0.0
 */
public class WeatherView {
	int state = 0;

	static final int NROWS = 255; // y direction
    static final int NCOLS = 413; // x direction

	/**
	 * @param state: category of analysised weather
	 */
	WeatherView(int state) {
		this.state = state;
	}

	/**
	 * @apiNote: Process the data in the selected period and show the result
	 * @param weatherModel: a model in class WeatherModel contain the data of the
	 *                      selected year
	 * @param startday:     start day of the analysised period
	 * @param endday:       end day of the analysised period
	 */
	public void dataprocess(WeatherModel weatherModel, Double startday, Double endday) {
		Double max = 0.0;
		Double min = 1000.0;
		Double sum = 0.0;
		List<Double> daylist = weatherModel.getday();
		List<Double> windspeedlist = weatherModel.getwindspeed();
		List<Double> temperaturelist = weatherModel.gettemperature();
		int start = daylist.indexOf(startday);
		int end = daylist.lastIndexOf(endday);
		while (start == -1 & startday < endday) {//find the first day after selected startday in the data
			startday++;
			start = daylist.indexOf(startday);
		}
		while (end == -1 & startday < endday) {//find the first day before selected endday in the data
			endday--;
			end = daylist.lastIndexOf(endday);
		}
		if (start == -1 | end == -1) {
			System.out.println("data for selected date is missing");
			System.exit(1);
		}
		//Decide the category of the processed data
		String category = new String();
		if (this.state == 0) {
			category = "windspeed";
		} else if (this.state == 1) {
			category = "temperature";
		} else {
			System.out.println("Please enter the type to be queried: 'temperature'or'windspeed'");
			Scanner sc = new Scanner(System.in);
			category = sc.nextLine();
			if (category.equals("windspeed")) {
				this.state=0;
			}
			else if(category.equals("temperature")) {
				this.state=1;
			}
			else {
				System.out.println("Input error");
			}
			
		}
		List<Double> subdaylist = daylist.subList(start, end);
		List<Double> subwindspeedlist = windspeedlist.subList(start, end);
		List<Double> subtemperaturelist = temperaturelist.subList(start, end);
		List<Integer> removelist = new ArrayList<Integer>();
		int i = 0;
		if (state==0) {
			this.state=0;
			for (Double data : subwindspeedlist) {
				if (data > 176 | data < 0) {
					System.out.printf("The windspeed: %.2f in day: %.0f is unreliable and has been removed! \n", data,
							daylist.get(i));
					data = 0.0;
					removelist.add(i);
					i--;
				}
				if (data - min < 0)
					min = data;
				if (data - max > 0)
					max = data;
				sum += data;
				i++;
			}
			for (int in:removelist){
				subdaylist.remove(in);
				subwindspeedlist.remove(in);
				subtemperaturelist.remove(in);
			}
			if (i < 1 | (sum / i) == 0) {
				System.out.println("The data in selected rangr is unreliable");
				System.exit(0);
			}
			System.out.println("The data is from day: " + Math.floor(startday) + " to day: " + Math.floor(endday)
					+ ". \nThe min windspeed is:" + min + ",The max windspeed is:" + max
					+ ",The mean windspeed is:" + sum / i);
		} else if (state==1) {
			for (Double data : subtemperaturelist) {
				if (data > 55 | data < -11.1) {
					System.out.printf("The temperature: %.3f in day: %.0f is unreliable and has been removed! \n", data,
							daylist.get(i));
					data = 0.0;
					removelist.add(i);
					i--;
				}
				if (data - min < 0)
					min = data;
				if (data - max > 0)
					max = data;
				sum += data;
				i++;
			}
			for (int in:removelist){
				subdaylist.remove(in);
				subtemperaturelist.remove(in);
				subwindspeedlist.remove(in);
			}
			if (i < 1 | (sum / i) == 0) {
				System.out.println("The data in selected rangr is unreliable");
				System.exit(0);
			}
			System.out.println("The data is from day: " + Math.floor(startday) + " to day: " + Math.floor(endday)
					+ ". \nThe min temperature is:" + min + ",The max temperature is:" + max
					+ ",The mean temperature is:" + sum / i);
		}

		weatherModel.setstartday(startday);
		weatherModel.setendday(endday);
		weatherModel.settotal(i);
		weatherModel.setsubwindspeed(subwindspeedlist);
		weatherModel.setsubtemperature(subtemperaturelist);
		weatherModel.setsubdaylist(subdaylist);
	}

	public void plotwindspeed(WeatherModel weatherModel){
		List<Double> thislist = weatherModel.subwindspeedlist;
		List<Double> thisdaylist = weatherModel.subdaylist;
		List<Double> meanlist = new ArrayList<Double>();
		List<Double> newdaylist = new ArrayList<Double>();

		int counter = 0;
		int num = 0;
		Double sumdata = 0.0;
		Double today = 0.0;
		for(Double day:thisdaylist){
			if(day>today){
				today = day;
				newdaylist.add(day);
				if(num>0)meanlist.add(sumdata/num);
				num=0;
				sumdata = thislist.get(counter);
			}
			else{
				sumdata += thislist.get(counter);
				num++;
			}
			counter++;
		}

		
		
		int i=0, mini=0, maxi = 0;//counter
		Double min=0.0, max=0.0;
		for (Double data : meanlist) {
			if (data - min < 0){
				min = data;
				mini = i;
			}
			if (data - max > 0){
				max = data;
				maxi = i;
			}
			i++;
			// System.out.printf("max: %f min: %f\n", max,min);
		}
		Double scalex = Double.valueOf(NCOLS)/i;
		Double scaley = (NROWS-10)/max;
		// System.out.println("scalex is :"+scalex+" scaley is :" +scaley+"\n");
		// System.out.printf("maxi: %d mini:%d\n", maxi,mini);
		// System.out.printf("max: %f min: %f\n", max,min);

		EasyGraphics display = new EasyGraphics(NCOLS,NROWS);		
		Double a = thislist.get(1)*scaley;
		display.moveTo(0, a.intValue());
		i = 0;
		for(Double data:meanlist){
			data = (data-min)*scaley;
			Double x = scalex*i;
			// System.out.printf("x: %f y: %f", x,data);
			display.lineTo(x.intValue(), data.intValue());
			i++;
		}
		display.setColor(0, 0, 255);
		Double x = scalex*mini;
		Double y = 0.0;
		display.fillEllipse(x.intValue(), y.intValue(), 8, 8);
		if(y<NCOLS/2)display.drawString("min", x.intValue()+10, y.intValue()+10, 20);
		else display.drawString("min", x.intValue()-10, y.intValue()+10, 20);

		display.setColor(255, 0, 0);
		x = scalex*maxi;
		y = scaley*max;
		display.fillEllipse(x.intValue(), y.intValue(), 8, 8);
		if(y<NCOLS/2)display.drawString("max", x.intValue()+10, y.intValue()-10, 20);
		else display.drawString("max", x.intValue()-10, y.intValue()-10, 20);
		System.out.println("Now quit the EasyGraphicsDemo window to exit");
	}
	
	public void plottemperature(WeatherModel weatherModel){
		List<Double> thislist = weatherModel.subtemperaturelist;
		int i=0, mini=0, maxi = 0;//counter
		Double min=0.0, max=0.0, sum=0.0;
		for (Double data : thislist) {
			if (data - min < 0){
				min = data;
				mini = i;
			}
			if (data - max > 0){
				max = data;
				maxi = i;
			}
			sum += data;
			i++;
		}
		Double scalex = Double.valueOf(NCOLS)/i;
		Double scaley = (NROWS-10)/(max-min);

		EasyGraphics display = new EasyGraphics(NCOLS,NROWS);		
		Double a = thislist.get(1)*scaley;
		display.moveTo(0, a.intValue());
		i = 0;
		for(Double data:thislist){
			data = (data-min)*scaley;
			Double x = scalex*i;
			display.lineTo(x.intValue(), data.intValue());
			i++;
		}
		display.setColor(0, 0, 255);
		Double x = scalex*mini;
		Double y = 0.0;
		display.fillEllipse(x.intValue(), y.intValue(), 8, 8);
		if(y<NCOLS/2)display.drawString("min", x.intValue()+10, y.intValue()+10, 20);
		else display.drawString("min", x.intValue()-10, y.intValue()+10, 20);
		
		display.setColor(255, 0, 0);
		x = scalex*maxi;
		y = scaley*(max-min);
		display.fillEllipse(x.intValue(), y.intValue(), 8, 8);
		if(y<NCOLS/2)display.drawString("max", x.intValue()+10, y.intValue()-10, 20);
		else display.drawString("max", x.intValue()-10, y.intValue()-10, 20);
		System.out.println("Now quit the EasyGraphicsDemo window to exit");
	}
}
