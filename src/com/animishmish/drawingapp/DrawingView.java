package com.animishmish.drawingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;


public class DrawingView extends View {

	//These variables are in order to compare current and past brush size in order to revert back
	private float brushSize, lastBrushSize;

	//drawing path
	private Path drawPath;
	//drawing and canvas paint
	private Paint drawPaint, canvasPaint;
	// intial color
	private int paintColor = 0xFF66000;
	//canvas
	private Canvas drawCanvas;
	//canvas bitmap
	private Bitmap canvasBitmap;
	// is the user is currently erasing or not
	private boolean erase=false;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		//view given size
	super.onSizeChanged(w, h, oldw, oldh);
	canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
	drawCanvas = new Canvas(canvasBitmap);
	
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	//detect when user touched
		float touchX = event.getX();
		float touchY = event.getY();
		
		//define what happens for Press - drag - Release
		switch (event.getAction()) {
		//Press
		case MotionEvent.ACTION_DOWN:
			drawPath.moveTo(touchX,touchY);
			break;
		//Drag
		case MotionEvent.ACTION_MOVE:
			drawPath.lineTo(touchX, touchY);
			break;
		//Release
		case MotionEvent.ACTION_UP:
			drawCanvas.drawPath(drawPath, drawPaint);
			//Reseting after release
			drawPath.reset();
			break;
			
		default:
			return false;
			
		}
		// Calling invalidate will cause the onDraw method to execute.
		invalidate();
		return true;
	}

	public DrawingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupDrawing();
		
		
		}
	

	private void setupDrawing() {

		//setting the default brush size to medium
		brushSize = getResources().getInteger(R.integer.medium_size);
		lastBrushSize = brushSize;
		
		
		//instantiate the drawing Path and Paint objects
		drawPath = new Path();
		drawPaint = new Paint();
		
		//set initial color
		drawPaint.setColor(paintColor);
		
		//set the initial path properties
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);
		//ask Shawn about this one
		canvasPaint = new Paint(Paint.DITHER_FLAG);
		
		
	}

	public void setColor(String newColor){
		//set color    
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
		}
	public void setBrushSize (float newSize){
		//update brush size
		float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
			    newSize, getResources().getDisplayMetrics());
			brushSize=pixelAmount;
			drawPaint.setStrokeWidth(brushSize);
	}
	
	public void setLastBrushSize(float lastSize){
	    lastBrushSize=lastSize;
	}
	public float getLastBrushSize(){
	    return lastBrushSize;
	}
	
	public void setErase(boolean isErase){
		//set erase true or false      
		erase=isErase;
		if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		else drawPaint.setXfermode(null);
		}
}
