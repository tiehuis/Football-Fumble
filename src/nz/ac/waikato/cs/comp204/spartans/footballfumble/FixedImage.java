/*
 * File: FixedImage.java - A FixedImage class.
 *
 * OD: 'Name'
 * Copyright: Spartans, 23/08/13++
 * License: GNU GPL v2
 * 
 * Notes: This file is used to instantiate a fixedImage
 * Issues: This program may be redundant
 * Reference: Sprite
 * Implements:
 * 
 */

package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

/**
 * A FixedImage class.
 * 
 * Holds a constant for the mass of the fixedImage which is
 * passed to the superclass upon instantiation of a new FixedImage
 * 
 * @author Spartans
 * */
public class FixedImage extends Sprite{
	
	private static final int FIXEDIMAGE_MASS	= 5;
	
	/**
	* Initializes the fixedImage with a mass of 5.
 	* @param initX the initial x position
 	* @param initY the initial y position
 	* @param drawView the view the fixedImage will exist
 	* @param bitmap the bitmap to draw the fixedImage on
 	* @author 'Name'
 	*/
	public FixedImage(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 0, 0, drawView, bitmap, FIXEDIMAGE_MASS);
	}
	
	public boolean collides(Sprite sprite){
		
		if(this.rect.intersect(sprite.rect)){
	
			// If the sprite is already in a collision return false and deal with that first.
			if(currentCollision){
				return false;
			}
			return (currentCollision = true);
			
		}
		return (currentCollision = false);	// there was no collision, therefore there is no current collision
	}
}
