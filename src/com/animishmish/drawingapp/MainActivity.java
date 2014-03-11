package com.animishmish.drawingapp;


import java.lang.reflect.Array;
import java.util.UUID;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private float smallBrush, mediumBrush, largeBrush;
	
	private DrawingView drawView;
	//set color and tag arrays
	
	//color array_01 (snowy)
	String[] colorPaletteTag_1 = {"#D5D8DD","#5CA2BE","#135487", "#2A4353", "#989DA4", "#80D9FF", "#99D9FF", "#BFE6FF", "#E7F3FF", 
			"#FFFFFF", "#EFEFE3", "#FEFEFD"};
	int[] colorPalette_1 = {0xFFD5D8DD,0xFF5CA2BE,0xFF135487, 0xFF2A4353, 0xFF989DA4, 0xFF80D9FF, 0xFF99D9FF, 0xFFBFE6FF, 0xFFE7F3FF, 
			0xFFFFFFFF, 0xFFEFEFE3, 0xFFFEFEFD};
	
	//color array_02 (rainy)
		String[] colorPaletteTag_2 = {"#FFFFFF","#DDDCBD","#DEDEDE", "#B0B3B0", "#8D9EA2", "#D6D2CC", "#CAC4BE", "#CAD4D5", "#687174", 
				"#BCC4C5", "#8A8783", "#E3ECEA"};
		int[] colorPalette_2 = {0xFFFFFFFF,0xFFDDDCBD,0xFFDEDEDE, 0xFFB0B3B0, 0xFF8D9EA2, 0xFFD6D2CC, 0xFFCAC4BE, 0xFFCAD4D5, 0xFF687174, 
				0xFFBCC4C5, 0xFF8A8783, 0xFFE3ECEA};
	
	//color array_03 (cloudy)
		String[] colorPaletteTag_3 = {"#FFFFFF","#FF69B4","#008080", "#00008B", "#A52A2A", "#DDA0DD", "#0000CD", "#5F9EA0", "#006400", 
				"#FF1493", "#008080", "#FA8072"};
		int[] colorPalette_3 = {0xFFFFFFFF,0xFFFF69B4,0xFF008080, 0xFF00008B, 0xFFA52A2A, 0xFFDDA0DD, 0xFF0000CD, 0xFF5F9EA0, 0xFF006400, 
				0xFFFF1493, 0xFF008080, 0xFFFA8072};
		
	//color array_04 (sunny)
	String[] colorPaletteTag_4 = {"#FF2151","#FF7B29","#FFC629", "#FCE9C8", "#8B77B5", "#0460D9", "#2E97F2", "#F2E85E", "#F2EEB6", 
			"#BF6D24", "#80DB1C", "#D3E019"};
	int[] colorPalette_4 = {0xFFFF2151,0xFFFF7B29,0xFFFFC629, 0xFFFCE9C8, 0xFF8B77B5, 0xFF0460D9, 0xFF2E97F2, 0xFFF2E85E, 0xFFF2EEB6, 
			0xFFBF6D24, 0xFF80DB1C, 0xFFD3E019};
				
//what's my current color
	private ImageButton currPaint, drawBtn, eraseBtn, newBtn,saveBtn,weatherBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		
				
		smallBrush = getResources().getInteger(R.integer.small_size);
		mediumBrush = getResources().getInteger(R.integer.medium_size);
		largeBrush = getResources().getInteger(R.integer.large_size);
		drawView = (DrawingView)findViewById(R.id.drawing);
		
		//Getting the default color for the user to start working with
		LinearLayout paintLayout = (LinearLayout)findViewById(R.id.top_row_paint_colors);
		//get the first color from the top row
		currPaint = (ImageButton)paintLayout.getChildAt(0);
		currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
		
		//get ref for weather button & set on click listener
		weatherBtn = (ImageButton)findViewById(R.id.weather_btn);
		weatherBtn.setOnClickListener(this);
		
		//get ref for draw button
		drawBtn = (ImageButton)findViewById(R.id.draw_btn);
		// set class as on click listener
		drawBtn.setOnClickListener(this);
		drawView.setBrushSize(mediumBrush);
		
		//erase button listener
		eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
		eraseBtn.setOnClickListener(this);
		//new button Listener
		newBtn = (ImageButton)findViewById(R.id.new_btn);
		newBtn.setOnClickListener(this);
		//new save Button listener
		newBtn =(ImageButton)findViewById(R.id.save_btn);
		newBtn.setOnClickListener(this);
		
		
				
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View view) {
	
		//responds to clicks
				if(view.getId()==R.id.weather_btn){
				    //weather button clicked
					//create pop up dialog for weather type
					final Dialog weatherDialog = new Dialog(this);
					weatherDialog.setTitle("What is The Weather?");
					//display the weather pop up
					weatherDialog.setContentView(R.layout.weather_chooser);
					
					
					weatherDialog.show();	
					Button snowy = (Button) weatherDialog.findViewById(R.id.btn_snowy);
					Button rainy = (Button) weatherDialog.findViewById(R.id.btn_rainy);
					Button cloudy = (Button) weatherDialog.findViewById(R.id.btn_cloudy);
					Button sunny = (Button) weatherDialog.findViewById(R.id.btn_sunny);

					Log.v("Checking","Done");

					//Snowy Button on click listener
					snowy.setOnClickListener(new OnClickListener() {
	
						
						
						@Override
						public void onClick(View v) {
							for (int i =1; i <= 12; i++){
								int p;
								if (i <10) {
							p = getResources().getIdentifier("color_0"+i,"id", getPackageName());
								}
								else {
									p = getResources().getIdentifier("color_"+i,"id", getPackageName());

								}
								
							ImageButton color1 = (ImageButton)findViewById(p);
							
							color1.setBackgroundColor(colorPalette_1[i-1]);
							color1.setTag(colorPaletteTag_1[i-1]);
							
							
							}
					        weatherDialog.dismiss();
					   
						}
					});

						//Rainy Button on click listener
						rainy.setOnClickListener(new OnClickListener() {
		
							@Override
							public void onClick(View v) {
								for (int i =1; i <= 12; i++){
									int p;
									if (i <10) {
								p = getResources().getIdentifier("color_0"+i,"id", getPackageName());
									}
									else {
										p = getResources().getIdentifier("color_"+i,"id", getPackageName());

									}
									
								ImageButton color2 = (ImageButton)findViewById(p);
								
								color2.setBackgroundColor(colorPalette_2[i-1]);
								color2.setTag(colorPaletteTag_2[i-1]);
								
								
								}
						        weatherDialog.dismiss();
						    
							}
						
					});
						//Cloudy Button on click listener
						cloudy.setOnClickListener(new OnClickListener() {
		
							@Override
							public void onClick(View v) {
								for (int i =1; i <= 12; i++){
									int p;
									if (i <10) {
								p = getResources().getIdentifier("color_0"+i,"id", getPackageName());
									}
									else {
										p = getResources().getIdentifier("color_"+i,"id", getPackageName());

									}
									
								ImageButton color3 = (ImageButton)findViewById(p);
								
								color3.setBackgroundColor(colorPalette_3[i-1]);
								color3.setTag(colorPaletteTag_3[i-1]);
								
								//color2.setBackgroundColor(0xFFCC00CC);
								//color2.setTag("#FFCC00CC");
								}
						        weatherDialog.dismiss();
						    
							}
						
					});	
					
						//Sunny Button on click listener
						sunny.setOnClickListener(new OnClickListener() {
		
							@Override
							public void onClick(View v) {
								for (int i =1; i <= 12; i++){
									int p;
									if (i <10) {
								p = getResources().getIdentifier("color_0"+i,"id", getPackageName());
									}
									else {
										p = getResources().getIdentifier("color_"+i,"id", getPackageName());

									}
									
								ImageButton color4 = (ImageButton)findViewById(p);
								
								color4.setBackgroundColor(colorPalette_4[i-1]);
								color4.setTag(colorPaletteTag_4[i-1]);
								
								//color2.setBackgroundColor(0xFFCC00CC);
								//color2.setTag("#FFCC00CC");
								}
						        weatherDialog.dismiss();
						    
							}
						
					});	
						

				}
				
				
		//responds to clicks
		if(view.getId()==R.id.draw_btn){
		    //draw button clicked
			
			//create pop up dialog for brush size
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Brush size:");
			//display the brush pop up
			brushDialog.setContentView(R.layout.brush_chooser);
			
				//listen for click on small size brush
				ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
				smallBtn.setOnClickListener(new OnClickListener(){
				    @Override
				    public void onClick(View v) {
				        drawView.setBrushSize(smallBrush);
				        drawView.setLastBrushSize(smallBrush);
				        //after we clicked we dismiss the dialog
				        brushDialog.dismiss();
				    }
				});
				
				//Medium brush button
				ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
				mediumBtn.setOnClickListener(new OnClickListener(){
				    @Override
				    public void onClick(View v) {
				        drawView.setBrushSize(mediumBrush);
				        drawView.setLastBrushSize(mediumBrush);
				        brushDialog.dismiss();
				    }
				});
				 
				//Large Brush button
				ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
				largeBtn.setOnClickListener(new OnClickListener(){
				    @Override
				    public void onClick(View v) {
				        drawView.setBrushSize(largeBrush);
				        drawView.setLastBrushSize(largeBrush);
				        brushDialog.dismiss();
				    }
				});
				
		brushDialog.show();	
			
		}
		
		//Erase Pop up size dialog 
		else if(view.getId()==R.id.erase_btn){
		    //switch to erase - choose size
			final Dialog brushDialog = new Dialog(this);
			brushDialog.setTitle("Eraser size:");
			brushDialog.setContentView(R.layout.brush_chooser);
			
			ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
			smallBtn.setOnClickListener(new OnClickListener(){
		
			    @Override
			    public void onClick(View v) {
			        drawView.setErase(true);
			        drawView.setBrushSize(smallBrush);
			        brushDialog.dismiss();
			    }
			});
			ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
			mediumBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			        drawView.setErase(true);
			        drawView.setBrushSize(mediumBrush);
			        brushDialog.dismiss();
			    }
			});
			ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
			largeBtn.setOnClickListener(new OnClickListener(){
			    @Override
			    public void onClick(View v) {
			        drawView.setErase(true);
			        drawView.setBrushSize(largeBrush);
			        brushDialog.dismiss();
			    }
			});
			brushDialog.show();
		}	
		else if(view.getId()== R.id.new_btn){
			//new button
			//create alert pop up window with delete warning
			AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
			newDialog.setTitle("Fresh Start");
			newDialog.setMessage("Start a new Sketch (This one is gone to heaven)");
			newDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
			        drawView.startNew();
			        dialog.dismiss();
				}
			});
			newDialog.setNegativeButton("Nooo!", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			
			newDialog.show();
		}
		else if(view.getId() == R.id.save_btn){
			//save drawing
			//Enable cache for saving the photo
			drawView.setDrawingCacheEnabled(true);
			//save image as png
			String imgSaved = MediaStore.Images.Media.insertImage(getContentResolver(), drawView.getDrawingCache(), 
					UUID.randomUUID().toString()+".png", "drawing");
			//give user feedback about saving operation
			if(imgSaved!=null){
			    Toast savedToast = Toast.makeText(getApplicationContext(),
			        "Drawing saved to Gallery! Horray!", Toast.LENGTH_SHORT);
			    savedToast.show();
			}
			else{
			    Toast unsavedToast = Toast.makeText(getApplicationContext(),
			        "Image could not be saved :(", Toast.LENGTH_SHORT);
			    unsavedToast.show();
			}
			//clear cache so you don't save over an existing drawing
			drawView.destroyDrawingCache();		
			
			
			//pop up warning
			AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
			saveDialog.setTitle("Save drawing");
			saveDialog.setMessage("Do you want to save this skethc and add it to the Gallery?");
			saveDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        //save drawing
			    }
			});
			saveDialog.setNegativeButton("Noooo!", new DialogInterface.OnClickListener(){
			    public void onClick(DialogInterface dialog, int which){
			        dialog.cancel();
			    }
			});
			saveDialog.show();
		}
	
	}
	public void paintClicked(View view){
	    //use chosen color
		if(view!=currPaint) {
			//update color
			ImageButton imgView  =(ImageButton)view;
			String color = view.getTag().toString();
			drawView.setColor(color);

			
			//update the UI to reflect the new chosen paint and set the previous one back to normal
			imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));
			currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
			currPaint=(ImageButton)view;
		}
	}

}
