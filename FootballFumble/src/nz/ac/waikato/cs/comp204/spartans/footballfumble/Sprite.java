package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Random;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite{
	
	protected DrawView drawView;
	protected Bitmap bitmap;
	protected int x = 0, y = 0;
	protected double xSpeed;
	protected double ySpeed;
	protected boolean currentCollision = false;
	protected Random random = new Random();
	protected double mass = 0;
	
	public Sprite(int initX, int initY, int xSpd, int ySpd, DrawView drawView, Bitmap bitmap, double mass){
		this.x = initX;
		this.y = initY;
		this.xSpeed = xSpd;
		this.ySpeed = ySpd;
		this.drawView = drawView;
		this.bitmap = bitmap;
		this.mass = mass;
		System.out.println("sprite right: " + bitmap.getWidth());
		System.out.println("sprite bottom: " + bitmap.getHeight());
	}
	
	protected void update(){
		if(x > drawView.getWidth() - bitmap.getWidth() - getxSpeed()){
			setxSpeed(getxSpeed() * -1);
		}
		if(x + getxSpeed() < 0){
			setxSpeed(getxSpeed() * -1);
		}
		if(y > drawView.getHeight() - bitmap.getHeight() - getySpeed()){
			setySpeed(getySpeed() * -1);
		}
		if((y + getySpeed()) < 0){
			setySpeed(getySpeed() * -1);
		}
			
		x += getxSpeed();
		y += getySpeed();
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
	
	public void changeDirection(Sprite s1){
		setxSpeed(getxDirectionCollision(s1));
		setySpeed(getyDirectionCollision(s1));
	}
	
	public double getxDirectionCollision(Sprite s1){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *5 slows initial speed
		return ((this.xSpeed * this.mass) + (s1.xSpeed * s1.mass))/(this.mass * 5);
	}
		
	public double getyDirectionCollision(Sprite s1){
		
		// Applies momentum to the objects. Player mass is 70, ball is 5. *5 slows initial speed
		return ((this.ySpeed * this.mass) + (s1.ySpeed * s1.mass))/(this.mass * 5);		
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

	public double getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}

	public double getySpeed() {
		return ySpeed;
	}

	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}
}

