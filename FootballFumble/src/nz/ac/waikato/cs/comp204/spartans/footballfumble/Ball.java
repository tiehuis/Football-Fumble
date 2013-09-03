
package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

/**
 *  A simple Ball class
 * 
 * 	Holds a constant for the mass of the ball which is
 * 	passed to the superclass upon instantiation of a new Ball.
 * */
public class Ball extends Sprite{
	
	private int BALL_MASS = 5;
	
/**
 * Initialises the Ball instance with initial speed of 0.
 * @param initX the initial x position
 * @param initY the initial y position
 * @param drawView the view the ball will exist
 * @param bitmap the bitmap to draw the ball on
 */
	public Ball(int initX, int initY, DrawView drawView, Bitmap bitmap) {		
		super(initX, initY, 0, 0, drawView, bitmap, BALL_MASS);
	}
}
