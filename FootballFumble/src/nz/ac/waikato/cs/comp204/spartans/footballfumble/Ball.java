/*
 * File: Ball.java - A Ball class
 *
 * OD: Spartans
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues: The ballSpeed is updated in the drawView and not here.
 * Reference: Sprite
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import java.io.*;

/**
 *  A Ball class.
 * 
 * 	Holds a constant for the mass of the ball which is
 * 	passed to the superclass upon instantiation of a new Ball.
 *  @author Spartans
 * */
public class Ball extends Sprite implements Serializable{
	
	// Special serialization class version identifier
			public static final long serialVersionUID = 1L;					// the version of this class for confirming later serialization reads -- if not explicit, Java auto constructs a version ID

	 	// Non-serialized static variables and constants
		private static String backup			= "ball.data";				// the file name to serialize instances to and from
		private static long   instances			= 0L;						// the number of instances of point created so far -- statics are in the class and are not serialized

		// Non-serialized instance variables
		private transient long instance;									// marking transiant stops auto backup and restore

	
	private static int BALL_MASS = 5;
	
	/**
 	* Initializes the Ball instance using {@link 
 	* nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	* with an initial speed of (0,0) and the mass stored in {@link #BALL_MASS}.
 	* @param initX the initial x position
 	* @param initY the initial y position
 	* @param drawView the view the ball will exist
 	* @param bitmap the bitmap to draw the ball on
 	*/
	public Ball(int initX, int initY, DrawView drawView, Bitmap bitmap) {		
		super(initX, initY, 0, 0, drawView, bitmap, BALL_MASS);
	}
	
	public void update(){
		
		// When the ball is draw, slow the x and y speed.
		if(Math.abs(this.getxSpeed()) > 0.5 || Math.abs(this.getySpeed()) > 0.5){
			this.setxSpeed(this.getxSpeed() * .95);
			this.setySpeed(this.getySpeed() * .95);

		}else{
			// If x or y speed is less than 0.3, stop the ball moving
			this.setxSpeed(0);
			this.setySpeed(0);
		}
		
		if(!inxBounds()){
			x = drawView.getWidth();
			setxSpeed((getxSpeed() + 3) * -1);
		}
		if(!inyBounds()){
			y = drawView.getHeight();
			setySpeed((getySpeed() + 3) * -1);
		}	
		
		x += getxSpeed();
		y += getySpeed();
	}
	
	/**
     *  Saves an instance to a file using serialization.
	 *
	 *  @throws IOException				on file access issues
     *
	 */
    public static void save(Ball b) throws IOException {
		
		// Debug info
		System.out.println("in save()");

		// Open a private file for this application id
		FileOutputStream   fos				= new FileOutputStream(new File(backup));
		
		// Wrap the file output stream with a helper filter to write objects
		ObjectOutputStream oos				= new ObjectOutputStream(fos);
		
		// Store the data to the file -- synchronizing to ensure atomic
		synchronized(b) {
			oos.writeObject(b);											// ObjectOutputStream checks if overridden otherwise uses default implementation
		}
		
		// Close underlying stream through filter
		oos.close();
	
	}
    
    /**
     *  Loads an instance from a file using serialization.
	 *
	 *  @throws IOException				on file access issues
	 *  @throws ClassNotFoundException	on readObject() error
     *
	 */
    public static Ball load() throws IOException, ClassNotFoundException {
		
		// Debug info
		System.out.println("in load()");

		// Open a private file for this application id
		FileInputStream   fis				= new FileInputStream(new File(backup));
		
		// Wrap the file output stream with a helper filter to write objects
		ObjectInputStream ois				= new ObjectInputStream(fis);
		
		// Load the data -- new instance, so no need to synch
		Ball b								= (Ball)ois.readObject();
		
		// Close underlying stream through filter
		ois.close();
		
		// Debug info
		System.out.println("after read, s.instance = " + b.instance);
		
		// Set the transient final instance id
		synchronized(Ball.class) {
			b.instance						= ++instances;
		}		
		
		return b;
		
	}
}
