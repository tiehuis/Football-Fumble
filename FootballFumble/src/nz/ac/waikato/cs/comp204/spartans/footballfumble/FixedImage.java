package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

public class FixedImage extends Sprite{
	
	// Fields for the ball...
	
	public FixedImage(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		
		super(initX, initY, 0, 0, drawView, bitmap, 5);
	}
	
	// Methods for the ball...
}
