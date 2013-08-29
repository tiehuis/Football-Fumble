package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class DrawView extends View{

	private Random random = new Random();
	public Ball ball;	
	public Sprite sprite1;
	public FixedImage goal;
	public player Player;
	
	public DrawView(Context context) {
		super(context);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),  R.drawable.player);
		sprite1 = new Sprite(random.nextInt(600), random.nextInt(600), 6, 6, this, bitmap1, 70);		
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_50px);
		ball = new Ball(random.nextInt(600), random.nextInt(600), this, bitmap2);
		//goal = new FixedImage(0, 0, this, BitmapFactory.decodeResource(getResources(),  R.drawable.goal_100px_15px), null);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_150px);
		Player = new player(250, 500, this, bitmap3);			
	}
	
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){
		ball.onDraw(canvas);		
		sprite1.onDraw(canvas);
		Player.onDraw(canvas);
		
		// When the ball is drawn, the speed is slowed
		ball.setxSpeed(ball.getxSpeed() * .95);
		ball.setySpeed(ball.getySpeed() * .95);
		
		
		if(sprite1.collides(ball) || ball.collides(sprite1)){
			// Only the ball changes direction
			ball.changeDirection(sprite1);		
		}	
		
		invalidate();
	}
}
