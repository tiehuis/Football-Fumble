package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class FixedImage {
	
	private DrawView drawView;
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
	}
}
