/*
 * File: AI.java - An AI class
 *
 * OD: Spartans
 * Copyright: Spartans, 28/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This program holds the methods associated with the AI class.
 * Issues: This class is derived from player, and needs to be refined.
 * Reference: Sprite, Player
 * Implements:
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.io.*;

import android.graphics.Bitmap;

/**
 *  A simple AI class which gets the position of the ball and moves towards it.
 * 
 * 	Moves the AI sprite towards the ball on each call of {@link #update()}.
 *	@author Spartans
 * */
public class AI extends Sprite implements Serializable{
	
	// Special serialization class version identifier
	public static final long serialVersionUID = 1L;					// the version of this class for confirming later serialization reads -- if not explicit, Java auto constructs a version ID

	// Non-serialized static variables and constants
	private static String backup			= "ai.data";				// the file name to serialize instances to and from
	private static long   instances			= 0L;						// the number of instances of point created so far -- statics are in the class and are not serialized
	
	// Non-serialized instance variables
	private transient long instance;	
	
	private static int TOP_SPEED		= 5;
	private static int PLAYER_MASS 		= 70;
	private static double ACCELERATION 	= 2;		 //ensure acceleration is always positive
	private boolean inRange 			= false;
	private double dstX, dstY, speed; 			 //ensure speed is never negative.
	
	/**
	 * Initializes the Player instance using {@link 
 	 * nz.ac.waikato.cs.comp204.spartans.footballfumble.Sprite#Sprite(int, int, int, int, DrawView, Bitmap, double)}
 	 * with an initial speed of (6,6).
	 * @param initX the initial x position of this sprite.
	 * @param initY the initial y position of this sprite.
	 * @param drawView the view which this sprite is to be drawn on.
	 * @param bitmap the bitmap image associated with this sprite.
	 */
	public AI(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 6, 6, drawView, bitmap, PLAYER_MASS);
	}
		
	/**
	 * Checks for the current position of the ball.
	 * <p>
	 * If a current touch is down, a direct path will be calculated for the ai sprite to move toward. Each
	 * update a new sub-triangle is calculated and the x and y directional speeds are changed so that they
	 * keep following the current ball position.
	 * <p>
	 */
	protected void update(){
		
		double distance, trigX, trigY, theta; 
		
		dstX 		= MatchGlobal.matchBall.getLeft();
		dstY		= MatchGlobal.matchBall.getTop();
	
		// If the AI is not in range of the ball, move towards it and accelerate if less than top speed.
		if(!inRange){
			if(speed + ACCELERATION <= TOP_SPEED){
				speed += ACCELERATION;
			}else{
					speed = TOP_SPEED;
			}
		}else{ // Will need to be adjusted
			if((speed -= ACCELERATION/5) >0){
				speed -= ACCELERATION/5;
			}else{
				speed = 0;
			}
		}
		// Calculate the appropriate triangle related to the touchx and touchy values
		trigX 		= Math.abs(dstX - x);
		trigY 		= Math.abs(dstY - y);
		distance	= Math.sqrt((trigX * trigX) + (trigY * trigY));
		theta 		= Math.acos(trigX / distance);	
		
		// Use the following to perform some other task if within a pre-defined range. Can use the inRange boolean.
		if (distance < 50){
		}
		
		// Calculating direction to move AI
		if (dstX >= x){
			xSpeed		= speed * Math.cos(theta);
		} else {
			xSpeed 		= speed * Math.cos(theta + Math.PI);
		}
		
		if (dstY >= y){
			ySpeed		= speed * Math.sin(theta);
		} else {
			ySpeed		= speed * Math.sin(theta + Math.PI);
		}
		
		x 			+= xSpeed;
		y 			+= ySpeed;
	}
	
	 /**
     *  Saves an instance to a file using serialization.
	 *
	 *  @throws IOException				on file access issues
     *
	 */
    public static void save(AI s) throws IOException {
		
		// Debug info
		System.out.println("in save()");

		// Open a private file for this application id
		FileOutputStream   fos				= new FileOutputStream(new File(backup));
		
		// Wrap the file output stream with a helper filter to write objects
		ObjectOutputStream oos				= new ObjectOutputStream(fos);
		
		// Store the data to the file -- synchronizing to ensure atomic
		synchronized(s) {
			oos.writeObject(s);											// ObjectOutputStream checks if overridden otherwise uses default implementation
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
    public static AI load() throws IOException, ClassNotFoundException {
		
		// Debug info
		System.out.println("in load()");

		// Open a private file for this application id
		FileInputStream   fis				= new FileInputStream(new File(backup));
		
		// Wrap the file output stream with a helper filter to write objects
		ObjectInputStream ois				= new ObjectInputStream(fis);
		
		// Load the data -- new instance, so no need to synch
		AI s								= (AI)ois.readObject();
		
		// Close underlying stream through filter
		ois.close();
		
		// Debug info
		System.out.println("after read, s.instance = " + s.instance);
		
		// Set the transient final instance id
		synchronized(AI.class) {
			s.instance						= ++instances;
		}		
		
		return s;
		
	}
}
