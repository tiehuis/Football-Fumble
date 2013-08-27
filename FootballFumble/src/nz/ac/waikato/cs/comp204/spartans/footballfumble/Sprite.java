package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite{
	
	private DrawView drawView;
	private Bitmap bitmap;
	private int x = 0, y = 0;
	private int xSpeed, ySpeed;
	private boolean currentCollision = false;
	private Random random = new Random();
	
	public Sprite(int initX, int initY, int xSpd, int ySpd, DrawView drawView, Bitmap bitmap){
		this.x = initX;
		this.y = initY;
		this.xSpeed = xSpd;
		this.ySpeed = ySpd;
		this.drawView = drawView;
		this.bitmap = bitmap;
		System.out.println("sprite right: " + bitmap.getWidth());
		System.out.println("sprite bottom: " + bitmap.getHeight());
	}
	
	protected void update(){
		if(x > drawView.getWidth() - bitmap.getWidth() - xSpeed){
			xSpeed = xSpeed * -1;
		}
		if(x + xSpeed < 0){
			xSpeed = xSpeed * -1;
		}
		if(y > drawView.getHeight() - bitmap.getHeight() - ySpeed){
			ySpeed = ySpeed * -1;
		}
		if((y + ySpeed) < 0){
			ySpeed = ySpeed * -1;
		}
			
		x += xSpeed;
		y += ySpeed;
	}
	
	public void onDraw(Canvas canvas){
		update();
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	@SuppressLint("WrongCall")
	public void move(Canvas canvas, int dx, int dy){
		this.x += dx;
		this.y += dy;
		this.onDraw(canvas);
	}
	
	public boolean collides(Sprite s1){
		
		// s1 coming from top-left
		if(this.getLeft() <= s1.getRight() && this.getTop() <= s1.getBottom() && this.getBottom() >= s1.getBottom() && this.getRight() >= s1.getRight()){
			// If this is the same collision as the last one then we don't want to recognise it until the current collision is finished
			if(currentCollision)
				return false;
			return true;
		}
		
		// s1 coming from top-right
		if(this.getLeft() <= s1.getRight() && this.getTop() <= s1.getBottom() && this.getRight() >= s1.getLeft() && this.getBottom() >= s1.getBottom()){
			if(currentCollision)
				return false;
			return true;
		}
		
		// There was no collision, therefore there is no current collision
		currentCollision = false;
		
		return false;
	}
	
	public void changeDirection(){
		xSpeed = xSpeed * -1;
		ySpeed = ySpeed * -1;
	}
	
	public int getLeft(){
		return this.x;
	}
	
	public int getTop(){
		return this.y;
	}
	
	public int getRight(){
		return this.x + bitmap.getWidth();
	}
	
	public int getBottom(){
		return this.y + bitmap.getHeight();
	}
	
	public int getWidth(){
		return bitmap.getWidth();
	}
	
	public int getHeight(){
		return bitmap.getHeight();
	}
}

