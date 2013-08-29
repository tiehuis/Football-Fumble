package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;

public class Ball extends Sprite{
	
	// Fields for the ball...
	
	public Ball(int initX, int initY, DrawView drawView, Bitmap bitmap) {		
		super(initX, initY, 0, 0, drawView, bitmap, 5);
	}
	
	// Methods for the ball...
}
