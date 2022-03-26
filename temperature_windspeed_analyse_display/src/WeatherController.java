
//import libraries
import java.util.Scanner;

/**
 * @projectName: Analysis of weather records
 * @className: WeatherController
 * @description: Main class for the project, input the select period of
 *               analysised weather
 * @author: Yunlong MA
 * @createDate: 15/12/2020
 * @version: v0.0
 */
public class WeatherController {
    public static void main(String[] args) {
        Double date[] = getdate();//get the expect range of date 
        WeatherModel weatherModel = new WeatherModel(date[0].intValue());// collect the data of the selected rang from URL and saved 
        WeatherView weatherView = new WeatherView(0);// 0:windspeed 1:temperature 2:input
        weatherView.dataprocess(weatherModel, date[1], date[2]);//calculate the dasired data and show
        weatherView.plottemperature(weatherModel);
        weatherView.plotwindspeed(weatherModel);

    }

    /**
     * @apiNote: get the selected date from Scanner
     * @return: int[] for the selected date : date[0]=year; date[1]= start day;
     *          date[2]=end day
     */
    public static Double[] getdate() {
        boolean success = false;// mark the statue of input
        Double date[] = new Double[3];
        do {
            System.out.println("Please select a year in the range 1999-2016");
            Scanner sc = new Scanner(System.in);
            date[0] = sc.nextDouble();
            //check the input data
            if (Math.max(1999, date[0]) != Math.min(date[0], 2016)) {
                System.out.println("Selected number is out of range, start select again!");
                continue;
            }
            System.out.println("Please select a start day in the range 1-365");
            date[1] = sc.nextDouble();
            if (Math.max(1, date[1]) != Math.min(date[1], 365)) {
                System.out.println("Selected number is out of range, start select again!");
                continue;
            }
            System.out.println("Please select a end day in the range 1-365");
            date[2] = sc.nextDouble();
            if (Math.max(1, date[2]) != Math.min(date[2], 365)) {
                System.out.println("Selected number is out of range, start select again!");
                continue;
            }
            success = true;
        } while (!success);// get input untile get the expected date
        return date;
    }

}