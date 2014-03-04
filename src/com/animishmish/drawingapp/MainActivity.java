package com.animishmish.drawingapp;


import java.util.UUID;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	private float smallBrush, mediumBrush, largeBrush;
	
	private DrawingView drawView;
	//what's my current color
	private ImageButton currPaint, drawBtn, eraseBtn;
	

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
		
		//get ref for draw button
		drawBtn = (ImageButton)findViewById(R.id.draw_btn);
		// set class as on click listener
		drawBtn.setOnClickListener(this);
		drawView.setBrushSize(mediumBrush);
		
		//erase button listener
		eraseBtn = (ImageButton)findViewById(R.id.erase_btn);
		eraseBtn.setOnClickListener(this);

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
