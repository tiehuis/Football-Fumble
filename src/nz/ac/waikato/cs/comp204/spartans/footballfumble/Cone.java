package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Cone extends FixedImage{
	
	public Cone(int initX, int initY, DrawView drawView, Bitmap bitmap){
		super(initX, initY, drawView, bitmap);
	}
	
	public void onDraw(Canvas canvas){
		
		rect.set(x+9, y+9, x+bitmap.getWidth()-5, y+bitmap.getHeight()-5);
		canvas.drawRect(rect, paint);
		
		canvas.drawBitmap(bitmap, x, y, null);
		
		update();
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
