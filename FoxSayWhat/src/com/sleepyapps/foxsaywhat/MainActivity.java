package com.sleepyapps.foxsaywhat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.tenjin.android.TenjinSDK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {

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
	ImageView title;
	
	String location = Environment.DIRECTORY_RINGTONES;
	String isType = MediaStore.Audio.Media.IS_RINGTONE;
	int type = RingtoneManager.TYPE_RINGTONE;
	
	String name;
	
	final CharSequence[] saveSoundOptions = {"Ringtone", "Notification", "Alarm"};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		aheeahee = (Button) findViewById(R.id.aheeahee);
		aheeahee.setOnClickListener(this);
		aheeahee.setOnLongClickListener(this);
		
		aooooo = (Button) findViewById(R.id.aooooo);
		aooooo.setOnClickListener(this);
		aooooo.setOnLongClickListener(this);
		
		frakakow = (Button) findViewById(R.id.frakakow);
		frakakow.setOnClickListener(this);
		frakakow.setOnLongClickListener(this);
		
		hateeho = (Button) findViewById(R.id.hateeho);
		hateeho.setOnClickListener(this);
		hateeho.setOnLongClickListener(this);
		
		jachachow = (Button) findViewById(R.id.jachachow);
		jachachow.setOnClickListener(this);
		jachachow.setOnLongClickListener(this);
		
		jofftchoff = (Button) findViewById(R.id.jofftchoff);
		jofftchoff.setOnClickListener(this);
		jofftchoff.setOnLongClickListener(this);
		
		ringding = (Button) findViewById(R.id.ringding);
		ringding.setOnClickListener(this);
		ringding.setOnLongClickListener(this);
		
		wabeddybombom = (Button) findViewById(R.id.wabeddybombom);
		wabeddybombom.setOnClickListener(this);
		wabeddybombom.setOnLongClickListener(this);
		
		wapapow = (Button) findViewById(R.id.wapapow);
		wapapow.setOnClickListener(this);
		wapapow.setOnLongClickListener(this);
		
		title = (ImageView) findViewById(R.id.titleImage);
		title.setOnClickListener(this);
		title.setOnLongClickListener(this);
		
		initTenjin();
	}

	public void initTenjin()
	{
		new Thread(new Runnable() {
			public void run() 
			{
				try
				{
					AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(MainActivity.this);
					String advertisingID = adInfo.getId();
					Context context = getApplicationContext();
				 
					BufferedReader rd = null;
					StringBuilder sb = null;
					String line = null;
					
					if (adInfo != null && !TextUtils.isEmpty(advertisingID))
					{
						// Tenjin Testing
						String url = "http://track-staging.tenjin.io/v0/event?";
						
						Map<String, String> params = new HashMap<String, String>();
						params.put("bundle_id", getPackageName());
						params.put("advertising_id", advertisingID);
						params.put("platform", "android");
						params.put("limit_ad_tracking", adInfo.isLimitAdTrackingEnabled() ? "1" : "0");
						params.put("os_version", Build.VERSION.RELEASE);
						params.put("app_version", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName + "." + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
						String paramString = convertURLParams(params, true);
						
						String header = "Authorization";
						String value = "Basic " + Base64.encodeToString(("RKTQZ5TD3NV4HSZZOXVTGJHBJWLQTJOM" + ":" + "").getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
				 
						Map<String, String> headers = new HashMap<String, String>();
						headers.put(header, value);
						
						Log.i("TEST", url+paramString);
						URL requestURL = new URL(url + paramString);
						
						HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
						connection.setConnectTimeout(15000);
						connection.setReadTimeout(30000);
						connection.setRequestProperty(header, value);
						connection.setRequestMethod("GET");
						connection.connect();
						
						int statusCode = connection.getResponseCode();
						
						// Read the result from the server.
						rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
						sb = new StringBuilder();
						
						while ((line = rd.readLine()) != null)
						{
							sb.append(line + '\n');
						}
					
						String response = sb.toString();
						Log.i("TEST", response);
						//new HTTPConnection().connect(url, params, headers, null, HTTPConnection.TYPE_GET);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
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
		else if(v instanceof ImageView && (v.getId() == R.id.titleImage))
		{
			playSoundClip(R.raw.whatthefoxsay);
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
	   
	public void saveSoundClip(final int clip) {
		if(isExternalStorageWritable()){
			name = this.getResources().getResourceEntryName(clip) + ".mp3";

     	   
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
			   
		    builder.setTitle("Save this foxy sound as:")
		           .setSingleChoiceItems(saveSoundOptions, -1, new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int which) {

		            	   if(which == 0){
		            		   location = Environment.DIRECTORY_RINGTONES;
		            		   isType = MediaStore.Audio.Media.IS_RINGTONE;
		            		   type = RingtoneManager.TYPE_RINGTONE;
		            	   }
		            	   else if(which == 1){
		            		   location = Environment.DIRECTORY_NOTIFICATIONS;
		            		   isType = MediaStore.Audio.Media.IS_NOTIFICATION;
		            		   type = RingtoneManager.TYPE_NOTIFICATION;
		            	   }
		            	   else{
		            		   location = Environment.DIRECTORY_ALARMS;
		            		   isType = MediaStore.Audio.Media.IS_ALARM;
		            		   type = RingtoneManager.TYPE_ALARM;
		            	   }
		               }
		           })
		           .setPositiveButton("Save", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	    File file = new File(Environment.getExternalStoragePublicDirectory(
		            	            location), name);
		            	    
		            	    boolean saved = saveToSD(file, clip);
		            	    String msg;
		            	    if(saved){
		            	    	msg = name + " successfully saved";
		            	    }
		            	    else{
		            	    	msg = "Error saving " + name;
		            	    }
		            	    
		            	    Toast toast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
		            	    toast.show();
		               }
		           })
		           .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   
		               }
		           });
		    builder.show();
		}
		else{
    	    Toast toast = Toast.makeText(MainActivity.this, "SD card not available", Toast.LENGTH_SHORT);
    	    toast.show();
		}
	}
	
	public boolean saveToSD(File file, int clip){
		
		Uri mUri = Uri.parse("android.resource://com.sleepyapps.foxsaywhat/" + clip);
		ContentResolver mCr = this.getContentResolver();
		AssetFileDescriptor soundFile;
		try {
		       soundFile= mCr.openAssetFileDescriptor(mUri, "r");
		   } catch (FileNotFoundException e) {
		       soundFile=null;   
		   }

		   try {
		      byte[] readData = new byte[1024];
		      FileInputStream fis = soundFile.createInputStream();
		      FileOutputStream fos = new FileOutputStream(file);
		      int i = fis.read(readData);

		      while (i != -1) {
		        fos.write(readData, 0, i);
		        i = fis.read(readData);
		      }

		      fos.close();
		   } catch (IOException io) {
			   return false;
		   }
		
//		
//		byte[] buffer=null;
//		InputStream fIn = getBaseContext().getResources().openRawResource(clip);
//		int size=0;
//		
//		try {
//			 size = fIn.available();
//			 buffer = new byte[size];
//			 fIn.read(buffer);
//			 fIn.close();
//		} catch (IOException e) {
//			return false;
//		}
//		
//		FileOutputStream save;
//		try {
//			 save = new FileOutputStream(file);
//			 save.write(buffer);
//			 save.flush();
//			 save.close();
//		} catch (FileNotFoundException e) {
//			return false;
//		} catch (IOException e) {
//		     return false;
//		}    
		
	   ContentValues values = new ContentValues();
	   values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
	   values.put(MediaStore.MediaColumns.TITLE, name);
	   values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
	   values.put(MediaStore.MediaColumns.SIZE, file.length());
	   values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
	   values.put(isType, true);
	   values.put(MediaStore.Audio.Media.IS_MUSIC, false);
	
	   Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
	   Uri newUri = MainActivity.this.getContentResolver().insert(uri, values);
	   try {
	       RingtoneManager.setActualDefaultRingtoneUri(MainActivity.this, type, newUri);
	   } catch (Throwable t) {
	       return false;
	   }
	   
	   return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		//Integrate TenjinSDK
		String apiKey = "X6SJQRU3UE3PXWJDEQB2S17GE7YZ3SQ7";
		TenjinSDK instance = TenjinSDK.getInstance(this, apiKey);
		instance.connect();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Linkify the message
	    final SpannableString s = new SpannableString("This soundboard created in honor of the song 'The Fox' by Ylvis. See the original video posted by tvnorge: http://www.youtube.com/watch?v=jofNR_WkoCE");
	    Linkify.addLinks(s, Linkify.ALL);

	    final TextView input = new TextView(MainActivity.this);
	    final AlertDialog d = new AlertDialog.Builder(MainActivity.this)
	        .setMessage( s )
	        .setTitle("Ring-ding-ding")
	        .setPositiveButton("Fraka-kow", null)
	        .create();
	    d.show();

	    // Make the textview clickable. Must be called after show()
	    ((TextView)d.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());

	    return true;
	}

	@Override
	public boolean onLongClick(View v) {
		if (v instanceof Button) 
		{
			final Button button = ((Button) v);
			int id = button.getId();

			switch (id)
			{
			case R.id.aheeahee:
				saveSoundClip(R.raw.aheeahee);
				break;
			case R.id.aooooo:
				saveSoundClip(R.raw.aooooo);
				break;
			case R.id.frakakow:
				saveSoundClip(R.raw.frakakow);
				break;
			case R.id.hateeho:
				saveSoundClip(R.raw.hateeho);
				break;
			case R.id.jachachow:
				saveSoundClip(R.raw.jachachow);
				break;
			case R.id.jofftchoff:
				saveSoundClip(R.raw.jofftchoff);
				break;
			case R.id.ringding:
				saveSoundClip(R.raw.ringding);
				break;
			case R.id.wabeddybombom:
				saveSoundClip(R.raw.wabeddybombom);
				break;
			case R.id.wapapow:
				saveSoundClip(R.raw.wapapow);
				break;
			default:
				break;
			}
			
			return true;
		}
		else if(v instanceof ImageView && (v.getId() == R.id.titleImage))
		{
			saveSoundClip(R.raw.whatthefoxsay);
			return true;
		}
		return false;
	}
	
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	private String convertURLParams(Map <String, String> source, boolean encode)
	{
		String result = "";
		
		for (Map.Entry<String, String> entry : source.entrySet())
		{
			// Add ampersand if this is not the first entry.
			if (result.length() > 0)
				result += "&";
			
			if (encode)
				result += Uri.encode(entry.getKey()) + "=" + Uri.encode(entry.getValue());
			else
				result += entry.getKey() + "=" + entry.getValue();
		}
				
		return result;
	}
}
