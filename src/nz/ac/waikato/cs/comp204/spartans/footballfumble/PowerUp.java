package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class PowerUp extends FixedImage{
	
	public String effect;
	public int magnitude;
	
	
	public PowerUp(int initX, int initY, DrawView drawView, String effect){
		super(initX, initY, drawView, null);
		this.effect = effect;
		paint.setColor(Color.BLACK);
	}
	
	public void onDraw(Canvas canvas){
		
		// Draw the rectangle just a bit smaller than the overall bitmap
		rect.set(x, y, x+30, y+30);
		canvas.drawRect(rect, paint);
	}
	
	public void Affect(Player player){
		if(effect.equals("speed")){
			player.setxSpeed(player.getxSpeed() + 2);
			player.setySpeed(player.getySpeed() + 2);
		}
		else if(effect.equals("strength")){
			player.setMass(player.getMass() + 10);
		}
	}

}
