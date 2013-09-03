package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

/**
 *  A simple FixedImage class.
 * 
 * Holds a constant for the mass of the fixedImage which is
 * passed to the superclass upon instantiation of a new FixedImage
 * */
public class FixedImage extends Sprite{
	
	private int FIXEDIMAGE_MASS = 5;
	
	/**
	* Initialises the fixedImage with a mass of 5.
 	* @param initX the initial x position
 	* @param initY the initial y position
 	* @param drawView the view the fixedImage will exist
 	* @param bitmap the bitmap to draw the fixedImage on
 	*/
	public FixedImage(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 0, 0, drawView, bitmap, FIXEDIMAGE_MASS);
	}
}
