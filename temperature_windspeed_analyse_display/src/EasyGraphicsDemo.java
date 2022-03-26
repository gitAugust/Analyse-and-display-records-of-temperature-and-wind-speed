/*

EasyGraphics demo

Illustrates how to use some EasyGraphics methods

Richard Clayton
November 2021

*/

public class EasyGraphicsDemo{
        
    // set size of image
    static final int NROWS = 255; // y direction
    static final int NCOLS = 255; // x direction

    public static void main(String args[]){
        
        EasyGraphics display = new EasyGraphics(NCOLS, NROWS);
        
        // display some nice colours
        plotColorGrid( display );
        
        // clear the screen
        // try using the waitSeconds method instead
        // EasyReader keyboard = new EasyReader();
        // char temp = keyboard.readChar("Press y to continue\n");
        display.clear();
        
        
        // plot some circles and text
        // try displaying text from a file, or from the keyboard
        plotCirclesAndText( display, "Hello World" );
    
        System.out.println("Now quit the EasyGraphicsDemo window to exit");
    }
    
    
    // some static methods to delegate functionality
    
    private static void plotColorGrid( EasyGraphics display){
        
        // display colour grid with blue upper right and red lower right.
        int red = 0;
        int green = 0;
        int blue = 0;
        
        for (int row = 1; row < NROWS; row++){
            for (int col = 1; col < NCOLS; col++){
                blue = row;
                red = NROWS - row;
                green = NCOLS - col;
                
                display.setColor(red, green, blue);
                display.plot(col, row);   
            }
        }
    }
    
    private static void plotCirclesAndText( EasyGraphics display, String text){
        
        for (int i = 1; i <= 15; i++){
            // choose a random location, diameter, and colour
            // for a circle
            int x = (int)(Math.random()*NCOLS);
            int y = (int)(Math.random()*NROWS);

            int diameter = (int)(Math.random()*(NCOLS/4));

            int red = (int)(Math.random()*NCOLS);
            int green = (int)(Math.random()*NCOLS);
            int blue = (int)(Math.random()*NCOLS);
        
            System.out.println("In static method: (x,y)=(" + x + "," + y + ")");
            
            // now display the circle
            display.setColor(red, green, blue);
            
            // try displaying different shapes, and lines
            display.fillEllipse(x, y, diameter, diameter);
        }
        
        int x = (int)(Math.random()*NCOLS);
        int y = (int)(Math.random()*NROWS);
        display.drawString( text, x, y, 18);
        
    }

}