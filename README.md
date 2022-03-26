# Analyse-and-display-records-of-temperature-and-wind-speed
Building a Java program to analyse and display records of  temperature and wind speed recorded between 1999 and 2016 at a weather station on the top of  a Scottish mountain called Cairngorm
 
## Task overview and main requirements
The aim of this assignment is to write a Java program that allows a user to
- Select a year in the range 1996 to 2016 (e.g. 2002).
- Select a type of observation to be either temperature or wind speed.
- Select a start day and end day (both in the range 1-365) for analysis.
- Display the maximum, minimum, and mean values of that observation for the selected day 
range in the terminal/command window.
- For extra marks, display a graph showing how that observation varied throughout the day 
range in an EasyGraphics window.

### __WeatherModel class__
Implementing the following requirements: 
- Create a data record (hint: this could be a separate class) that can store day, time, 
windspeed (in miles per hour) and temperature (in degrees Celsius) data for an entire year.
- Read day, time, windspeed, and temperature for an entire year from a URL that is supplied
as an argument to a method. This requirement will involve opening a connection to the 
URL, reading the data, parsing the data into separate fields, and then storing the data. The 
code supplied in URLtest.java will help you with implementing this part of the program. 
- The data read from the URL should be parsed as follows: day (range 1-365), column 2 (up 
to 2006), column 1 (2007 onwards); time is recorded at 30 minute intervals in column 3 (up 
to 2006) and column 2 otherwise; windspeed (mean over the 30 min period) is stored in 
column 4 (up to 2006) and column 3 otherwise; temperature is stored in columns 9 and 10 
(up to 2006) and columns 8 and 9 otherwise. There are two temperature sensors, and you 
should return data from the first one (i.e. from column 9 (up to 2006) or column 8 
otherwise). • Return a list of temperature readings for the range of days that are provided. 
- Return a list of mean wind speed readings for the range of days that are provided.
- If data for these days are not available or are unreliable (e.g. a mean wind speed of 0), 
return a suitable message.

### __WeatherView class__
WeatherView class acting as a viewer only, implementing the following requirements:
- Calculate the mean, maximum, and minimum values of temperature or mean windspeed
over the selected range of days, and display these values in the command window or 
terminal.
- Optionally (see below), plot a graph of temperature throughout the selected range of start 
day to end day in an EasyGraphics window.
- Optionally, plot a graph of mean wind speed throughout the selected range of start day to 
end day in a separate EasyGraphics window.
- Optionally highlight the maximum and minimum temperature and mean wind speed over 
the selected range of days in each of the EasyGraphics windows.

### __WeatherController class__
- It must include a main method, so that the program runs when java WeatherController
is typed on the command line.
- When the program runs, the WeatherController class must initiate dialog with the user
to select: 
* a year in the range 1999-2016
* a start day in the range 1-365
* an end day in the range 1-365. 
- Construct a suitable URL and pass it to WeatherModel, so that data can be read and stored.
- Call methods in WeatherView to display the data on the command window or terminal, 
and (optionally) in EasyGraphics windows.
