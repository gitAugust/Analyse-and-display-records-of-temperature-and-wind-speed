
//import libraries
import java.util.List;
import java.net.MalformedURLException;

/**
 * @projectName: Analysis of weather records
 * @className: TestHarness
 * @description: Test the function of class and method in this project
 *               including get data for URL and process the data.
 * @author: Yunlong MA
 * @createDate: 16/12/2020
 * @version: v0.0
 */
public class TestHarness {
    public static void main(String args[]) throws MalformedURLException {
        /* test of data processing */
        // DataProcessTest();
        EasyGraphics display = new EasyGraphics(255,430);
    }

    private static void URLCollectionTest(){
        /* test of get data */
        for(int year=1999; year<2017; year++){
            try{
                WeatherModel weatherModel = new WeatherModel(year);
                List<Double> daylist = weatherModel.getday();
                List<Double> windspeedlist = weatherModel.getwindspeed();
                List<Double> temperaturelist = weatherModel.gettemperature();
                System.out.print("Year: "+year+"\n");
                System.out.print("The lengh of the day is:"+daylist.size()+" the first day is:"+daylist.get(0)+".\n");
                System.out.print("The lengh of the windspeed is:"+windspeedlist.size()+" the first windspeed is:"+windspeedlist.get(0)+".\n");
                System.out.print("The lengh of the temperature is:"+temperaturelist.size()+" the first temperature is:"+temperaturelist.get(0)+".\n");
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.print("Some wrong with the year:" + year + "!\n");
            }
        }
    }

    private static void DataProcessTest(){
        for (int year = 1999; year < 2017; year++) {
            try {
                WeatherModel weatherModel = new WeatherModel(year);
                List<Double> daylist = weatherModel.getday();
                WeatherView weatherView = new WeatherView(0);// 0:windspeed 1:temperature 2:input
                weatherView.dataprocess(weatherModel, daylist.get(0), daylist.get(daylist.size() - 1));

                System.out.print("Year: " + year + "\n");
            } 
            catch (Exception e) {
                e.printStackTrace();
                System.out.print("Some wrong with the year:" + year + "!\n");
            }
        }


    }
}