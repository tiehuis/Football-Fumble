package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class FixedImage extends Sprite{
	
	
	public FixedImage(int initX, int initY, DrawView drawView, Bitmap bitmap) {
		super(initX, initY, 0, 0, drawView, bitmap);
	}
	
	/*private DrawView drawView;
	private Bitmap bitmap;
	private int x = 0, y = 0;
	
	@SuppressLint("WrongCall")
	public FixedImage(int x, int y, DrawView drawView, Bitmap bitmap, Canvas canvas){
		this.x = x;
		this.y = y;
		this.drawView = drawView;
		this.bitmap = bitmap;
		onDraw(canvas);
	}
	
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}*/
}
