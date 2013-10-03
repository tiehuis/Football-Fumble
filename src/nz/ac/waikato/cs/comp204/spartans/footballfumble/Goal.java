package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class Goal extends FixedImage{
	
	private HitZone hitZone = null;
	private Post leftPost, rightPost = null;
	private final int POST_WIDTH = 20;
	private final int POST_HEIGHT = 100;
	
	public Goal(int initX, int initY, DrawView drawView, Bitmap bitmap){
		super(initX, initY, drawView, bitmap);
		hitZone = new HitZone(initX+25, initY, initX+bitmap.getWidth()-20, initY+bitmap.getHeight()-30);
		leftPost = new Post(initX, initY, initX+POST_WIDTH, initY+5+POST_HEIGHT);
		rightPost = new Post(initX+bitmap.getWidth()-POST_WIDTH, initY, initX+bitmap.getWidth(), initY+bitmap.getHeight());
	}
	
	// Override the onDraw as we don't need to check if a Goal/FixedImage is within the x and y bounds
	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
		hitZone.onDraw(canvas);
		leftPost.onDraw(canvas);
		rightPost.onDraw(canvas);
	}
	
	public boolean collides(Sprite sprite){
		
		if(leftPost.ballHitsPost() || rightPost.ballHitsPost()){
			return true;
		}
		
		return false;
	}
	
	public boolean isGoal(){
		if(hitZone.withinZone()){
			return true;
		}
		return false;
	}
	
	
	
	
	// Private class which will be contained inside the goal image. If the ball collides with this HitZone, a goal is scored.
	// If the ball collides with the Goal image, the usual collision applies.
	private class HitZone{
		
		private int left, top, right, bottom;
		private Rect zone = new Rect();
		private Paint paint = new Paint();
		
		public HitZone(int left, int top, int right, int bottom){
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
			
			paint.setColor(Color.YELLOW);
			paint.setStyle(Style.FILL);
			paint.setAlpha(0);
			
			zone.set(this.left, this.top, this.right, this.bottom);
		}
		
		public void onDraw(Canvas canvas){
			canvas.drawRect(zone, paint);
		}
		
		public boolean withinZone(){
			
			if(Match.ball.rect.intersect(zone)){
				return true;
			}
			return false;
		}
	}
	
	
	
	
	private class Post{
		
		private int left, top, right, bottom;
		private Paint paint = new Paint();
		private Rect post = new Rect();
		
		public Post(int left, int top, int right, int bottom){
			this.left = left;
			this.top = top;
			this.right = right;
			this.bottom = bottom;
			
			paint.setColor(Color.BLACK);
			paint.setStyle(Style.FILL);
			//paint.setAlpha(0);
			
			post.set(this.left, this.top, this.right, this.bottom);
		}
		
		public void onDraw(Canvas canvas){
			canvas.drawRect(post, paint);
		}
		
		public boolean ballHitsPost(){
			
			
		if(this.post.intersect(Match.ball.rect)){
			// If the sprite is already in a collision return false and deal with that first.
			if(currentCollision){
				return false;
			}
			return (currentCollision = true);
		}
		return (currentCollision = false);	// there was no collision, therefore there is no current collision
			
		/*
		if(this.post.intersect(Match.ball.rect)){
			return true;
		}
		return false;
		*/
		
		}
		
	}
}
