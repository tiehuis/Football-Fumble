package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class player extends Sprite{
	
	private boolean isDown = false;
	private boolean firstTouch = false;
	double touchX, touchY, speed, acceleration = 3;
	int topSpeed = 6;
	public player(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 6, 6, drawView, bitmap, 5);
	}
	
	protected void update(){
		if(firstTouch)
		{
			double distance, trigX, trigY, theta; 
			if(isDown){
				if(xSpeed <= topSpeed){
					speed += acceleration;
					if(speed > topSpeed)
						speed = topSpeed;
				}
			}
			else{
				/*
				if(speed > 0){
					speed -= acceleration;
					if(speed < 0)
						speed = 0;
					System.err.print("Fail");
				}*/
				speed = 0;
				return;
			}/*
			if(touchX > x){
				if(touchY > y){
					trigX = touchX - x;
					trigY = touchY - y;
				}
				else{
					trigX = touchX - x;
					trigY = y - touchY;
				}
			}
			else{
				if(touchY > y){
					trigX = x - touchX;
					trigY = touchY - y;
				}
				else{
					trigX = x - touchX;
					trigY = y - touchY;
				}
			}*/
			trigX = touchX - x;
			trigY = touchY - y;
			distance = Math.sqrt((trigX * trigX) + (trigY * trigY));
			theta = Math.acos(trigX / distance);			
			distance = distance / speed;
			trigX = distance * Math.cos(theta);
			trigY = distance * Math.sin(theta);
			
			x += trigX;
			if(touchY > y){
				y += trigY;
			}
			else{
				y -= trigY;
			}			
		}
	/*
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		firstTouch = true;
		touchX = event.getX();
		touchY= event.getY();
		switch (event.getAction()) {
		
		case MotionEvent.ACTION_DOWN:
			isDown = true;
			break;

		case MotionEvent.ACTION_MOVE:
			isDown = true;	
			break;
	            		
		case MotionEvent.ACTION_UP:
			isDown = false;
	        break;
	        
		case MotionEvent.ACTION_OUTSIDE:
			isDown = false;
		    break;
		}	
		v.invalidate();
	return true;			
	}

*/
	drawView.setOnTouchListener(new View.OnTouchListener() 
	{
	    public boolean onTouch(View v, MotionEvent e) 
	    {
	    	firstTouch = true;
			touchX = e.getX();
			touchY= e.getY();
			switch (e.getAction()) 
			{
			
			case MotionEvent.ACTION_DOWN:
				isDown = true;
				break;

			case MotionEvent.ACTION_MOVE:
				isDown = true;	
				break;
		            		
			case MotionEvent.ACTION_UP:
				isDown = false;
		        break;
		        
			case MotionEvent.ACTION_OUTSIDE:
				isDown = false;
			    break;
			}				
		return true;	
	    }
	});
}
}
	


