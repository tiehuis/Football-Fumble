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
	
	public DrawView(Context context) {
		super(context);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_150px);
		sprite1 = new Sprite(random.nextInt(600), random.nextInt(600), this, bitmap1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_150px);
		ball = new Ball(random.nextInt(600), random.nextInt(600), this, bitmap2);
		//goal = new FixedImage(0, 0, this, BitmapFactory.decodeResource(getResources(),  R.drawable.goal_100px_15px), null);
	}
	
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){
		ball.onDraw(canvas);
		sprite1.onDraw(canvas);
		
		if(sprite1.collides(ball) || ball.collides(sprite1)){
			sprite1.changeDirection();
			ball.changeDirection();
		}
		
		invalidate();
	}
}
