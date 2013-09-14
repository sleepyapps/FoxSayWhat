package com.sleepyapps.foxsaywhat;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	MediaPlayer mediaPlayer = null;
	Button aheeahee;
	Button aooooo;
	Button frakakow;
	Button hateeho;
	Button jachachow;
	Button jofftchoff;
	Button ringding;
	Button wabeddybombom;
	Button wapapow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		aheeahee = (Button) findViewById(R.id.aheeahee);
		aheeahee.setOnClickListener(this);

		aooooo = (Button) findViewById(R.id.aooooo);
		aooooo.setOnClickListener(this);
		
		frakakow = (Button) findViewById(R.id.frakakow);
		frakakow.setOnClickListener(this);
		
		hateeho = (Button) findViewById(R.id.hateeho);
		hateeho.setOnClickListener(this);
		
		jachachow = (Button) findViewById(R.id.jachachow);
		jachachow.setOnClickListener(this);
		
		jofftchoff = (Button) findViewById(R.id.jofftchoff);
		jofftchoff.setOnClickListener(this);
		
		ringding = (Button) findViewById(R.id.ringding);
		ringding.setOnClickListener(this);
		
		wabeddybombom = (Button) findViewById(R.id.wabeddybombom);
		wabeddybombom.setOnClickListener(this);
		
		wapapow = (Button) findViewById(R.id.wapapow);
		wapapow.setOnClickListener(this);
		
	}

	public void onClick(View v)
	{
		if (v instanceof Button) 
		{
			final Button button = ((Button) v);
			int id = button.getId();

			switch (id)
			{
			case R.id.aheeahee:
				playSoundClip(R.raw.aheeahee);
				break;
			case R.id.aooooo:
				playSoundClip(R.raw.aooooo);
				break;
			case R.id.frakakow:
				playSoundClip(R.raw.frakakow);
				break;
			case R.id.hateeho:
				playSoundClip(R.raw.hateeho);
				break;
			case R.id.jachachow:
				playSoundClip(R.raw.jachachow);
				break;
			case R.id.jofftchoff:
				playSoundClip(R.raw.jofftchoff);
				break;
			case R.id.ringding:
				playSoundClip(R.raw.ringding);
				break;
			case R.id.wabeddybombom:
				playSoundClip(R.raw.wabeddybombom);
				break;
			case R.id.wapapow:
				playSoundClip(R.raw.wapapow);
				break;
			default:
				break;
			}
		}
	}
	
	public void playSoundClip(int clip) {
        if (mediaPlayer != null) {
        	mediaPlayer.reset();
        	mediaPlayer.release();
        }
        
		mediaPlayer = MediaPlayer.create(this, clip);
		mediaPlayer.start();
	}
	
   public void onDestroy() {

	    //mediaPlayer.stop();
	    super.onDestroy();

	}
	   
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
