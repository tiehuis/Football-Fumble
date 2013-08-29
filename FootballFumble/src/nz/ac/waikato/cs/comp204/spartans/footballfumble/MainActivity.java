package nz.ac.waikato.cs.comp204.spartans.footballfumble;

import android.os.Bundle;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Color;


public class MainActivity extends Activity {
	
	DrawView drawView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Keeps screen on
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		drawView = new DrawView(this);
		drawView.setBackgroundColor(Color.YELLOW);
		setContentView(drawView);
	}
}