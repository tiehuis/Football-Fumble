/*
 * File: Sprite.java - A Sprite Class
 *
 * OD: Spartans
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a Ball
 * Issues:
 * Reference:
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.io.*;

/**
 *  A Sprite class.
 *  <p>
 *  To move x and y use {@link #move(Canvas, int, int)} and 
 *  to delete this instance from the spriteList use {@link #deleteInstance()}
 * 
 *	@author Spartans
 * */
public class Sprite implements Serializable{
	
	// Special serialization class version identifier
		public static final long serialVersionUID = 1L;					// the version of this class for confirming later serialization reads -- if not explicit, Java auto constructs a version ID

 	// Non-serialized static variables and constants
	private static String backup			= "sprite.data";				// the file name to serialize instances to and from
	private static long   instances			= 0L;						// the number of instances of point created so far -- statics are in the class and are not serialized

	// Non-serialized instance variables
	private transient long instance;									// marking transiant stops auto backup and restore

	protected DrawView drawView;
	protected Bitmap bitmap;
	protected double xSpeed;
	protected double ySpeed;
	protected int x 					= 0;
	protected int y						= 0;
	protected boolean currentCollision	= false;
	protected double mass 				= 0;
	
	/**
	 * Initializes the Sprite instance.
	 * @param initX the initial x position of this sprite.
	 * @param initY the initial y position of this sprite.
	 * @param xSpd the initial x speed of this sprite.
	 * @param ySpd the initial y speed of this sprite.
	 * @param drawView the view which this sprite is to be drawn on.
	 * @param bitmap the bitmap image associated with this sprite.
	 * @param mass the mass of the sprite.
	 */
	public Sprite(int initX, int initY, int xSpd, int ySpd, DrawView drawView, Bitmap bitmap, double mass){
		this.x 			= initX;
		this.y 			= initY;
		this.xSpeed		= xSpd;
		this.ySpeed 	= ySpd;
		this.drawView 	= drawView;
		this.bitmap 	= bitmap;
		this.mass 		= mass;
		DrawView.addToList(this);
	}
	
	/**
	 * Checks the sprites position and runs the code each time {@link #onDraw(Canvas)} is called. 
	 */
	protected void update(){
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
	 * Called whenever the canvas is invalidated. Updates the canvas with this objects bitmap. 
	 * Calls the {@link #update()} method.
	 * @param canvas handles what to draw
	 */
	public void onDraw(Canvas canvas){
		update();
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	/**
	 * Moves the point by dx and dy.
	 * @deprecated the {@link #update()} method is used to move the object.
	 * @param canvas the canvas being used to draw this image on.
	 * @param dx the delta to move in the x direction.
	 * @param dy the delta to move in the y direction.
	 */
	@SuppressLint("WrongCall")
	public void move(Canvas canvas, int dx, int dy){
		this.x 		+= dx;
		this.y		+= dy;
		this.onDraw(canvas);
	}
	
	/**
	 * Checks the collision status of this instance against another Sprite.
	 * @param sprite the Sprite being compared against this Sprite.
	 * @return true if a currentCollision is occuring, false otherwise
	 */
	public boolean collides(Sprite sprite){ // Ccheck the changes made here.
		
		// If the sprite is already in a collision return false and deal with that first.
		if(currentCollision){
			return false;
		}
		
		// Case where s1 coming from top-left
		if(this.getLeft() <= sprite.getRight() && this.getTop() <= sprite.getBottom() && 
				this.getBottom() >= sprite.getBottom() && this.getRight() >= sprite.getRight()){
			currentCollision = true;
			return true;
		}
		
		// Case where s1 coming from top-right
		if(this.getLeft() <= sprite.getRight() && this.getTop() <= sprite.getBottom() 
				&& this.getRight() >= sprite.getLeft() && this.getBottom() >= sprite.getBottom()){
			currentCollision = true;
			return true;
		}
		
		currentCollision = false; // there was no collision, therefore there is no current collision
		return false;
	}
	
	/**
	 * Sets the new x and y directional speed.
	 * @param sprite the sprite that this instance is colliding with
	 */
	public void setDirection(Sprite sprite){
		setxSpeed(newxSpeed(sprite));
		setySpeed(newySpeed(sprite));
	}
	
	/**
	 * Calculates the new x directional speed based upon the mass of the two sprites that are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new x direction speed of this sprite
	 */
	public double newxSpeed(Sprite sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *7 slows initial speed
		return ((this.xSpeed * this.mass) + (sprite.xSpeed * sprite.mass))/(this.mass * 7);
	}
		
	/**
	 * Calculates the new y directional speed based upon the mass of the two sprites which are colliding.
	 * @param sprite the sprite that this instance is colliding with
	 * @return the new y direction speed of this sprite
	 */
	public double newySpeed(Sprite sprite){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *7 slows initial speed
		return ((this.ySpeed * this.mass) + (sprite.ySpeed * sprite.mass))/(this.mass * 7);		
	}
	
	/**
	 * Checks if the current sprite is within the y-region bounded by the drawView height.
	 * @return false if out of y bound, otherwise true
	 */
	public boolean inyBounds(){
		// Bottom of screen
		if(y > drawView.getHeight() - bitmap.getHeight() - getySpeed()){
			return false;
		}
		// Top of screen
		if(y + getySpeed() < 35){
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the current sprite is within the x-region bounded by the drawView width.
	 * @return false if out of x bound, otherwise true
	 */
	public boolean inxBounds(){
		// Right of screen
		if(x > drawView.getWidth() - bitmap.getWidth() - getxSpeed()){
			return false;
		}
		// Left of screen
		if(x + getxSpeed() < 0){
			return false;
		}		
		return true;	
	}
	
	/**
	 * Deletes this instance from the spriteList, effectively removing the programs access to this instance
	 */
	public void deleteInstance(){
		DrawView.deleteFromList(this);
	}
	
	/**
	 * @return the x position of the left of the bitmap image on canvas.
	 */
	public int getLeft(){
		return this.x;
	}
	
	/**
	 * @return the y position of the top of the bitmap image on canvas.
	 */
	public int getTop(){
		return this.y;
	}
	
	/**
	 * @return the x position of the right of the bitmap image on canvas.
	 */
	public int getRight(){
		return this.x + bitmap.getWidth();
	}
	
	/**
	 * @return the y position of the bottom of the bitmap image on canvas,
	 */
	public int getBottom(){
		return this.y + bitmap.getHeight();
	}
	
	/**
	 * @return the width of bitmap image
	 */
	public int getWidth(){
		return bitmap.getWidth();
	}
	
	/**
	 * @return the height of the bitmap image
	 */
	public int getHeight(){
		return bitmap.getHeight();
	}

	/**
	 * @return the current speed in the x direction
	 */
	public double getxSpeed() {
		return xSpeed;
	}
		
	/**
 	* @return the current speed in the y direction
 	*/
	public double getySpeed() {
		return ySpeed;
	}

	/**
	 * @param xSpeed xSpeed to set (in pixels)
	 */
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	/**
	 * @param ySpeed ySpeed to set (in pixels)
	 */
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	 /**
     *  Saves an instance to a file using serialization.
	 *
	 *  @throws IOException				on file access issues
     *
	 */
    public static void save(Sprite s) throws IOException {
		
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
    public static Sprite load() throws IOException, ClassNotFoundException {
		
		// Debug info
		System.out.println("in load()");

		// Open a private file for this application id
		FileInputStream   fis				= new FileInputStream(new File(backup));
		
		// Wrap the file output stream with a helper filter to write objects
		ObjectInputStream ois				= new ObjectInputStream(fis);
		
		// Load the data -- new instance, so no need to synch
		Sprite s								= (Sprite)ois.readObject();
		
		// Close underlying stream through filter
		ois.close();
		
		// Debug info
		System.out.println("after read, s.instance = " + s.instance);
		
		// Set the transient final instance id
		synchronized(Sprite.class) {
			s.instance						= ++instances;
		}		
		
		return s;
		
	}
}