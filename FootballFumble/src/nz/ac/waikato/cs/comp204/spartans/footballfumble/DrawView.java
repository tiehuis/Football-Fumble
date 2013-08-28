package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View{

	private Random random = new Random();
	public Ball ball;
	public Sprite sprite1;
	public FixedImage goal;
	public List<Sprite> spriteList = new ArrayList<Sprite>();
	public Paint paint = new Paint(); 
	
	public DrawView(Context context) {
		super(context);
		Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_50px);
		sprite1 = new Sprite(200, 400, -2, 5, this, bitmap1);
		spriteList.add(sprite1);
		Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_50px);
		ball = new Ball(200, 200, this, bitmap2);	
		spriteList.add(ball);
		Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),  R.drawable.ball_25px);
		goal = new FixedImage(105, 112, this, bitmap3);
		spriteList.add(goal);


	}
	
	@SuppressLint("WrongCall")
	@Override
	public void onDraw(Canvas canvas){			
		for(int i=0; i<spriteList.size();i++){
			spriteList.get(i).onDraw(canvas);
		}
		
		/*ball.onDraw(canvas);
		sprite1.onDraw(canvas);
		goal.onDraw(canvas);*/
		
		if (sprite1.collides(goal))
			sprite1.changeDirection();
		
		if (ball.collides(goal))
			ball.changeDirection();
		
		if(sprite1.collides(ball) || ball.collides(sprite1)){
			sprite1.changeDirection();
			ball.changeDirection();
		}
		
		paint.setColor(Color.BLACK); 
		paint.setTextSize(30); 
		canvas.drawText(ball.getCollisions().toString(), 20, 40, paint); 
				
		invalidate();
	}
}
