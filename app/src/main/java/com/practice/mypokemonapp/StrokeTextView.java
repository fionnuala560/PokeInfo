package com.practice.mypokemonapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

public class StrokeTextView extends AppCompatTextView {
    private Paint strokePaint;
    private Paint textPaint;

    public StrokeTextView(Context context){
        super(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    private void init(){

        //initialise the stroke paint
        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(20);
        strokePaint.setColor(0xFF0000FF);
        strokePaint.setAntiAlias(true);
        strokePaint.setTextSize(getTextSize());

        //initialise the text paint
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(getCurrentTextColor());
        textPaint.setAntiAlias(true);

        //ensure initial text size is set
        updateTextSize(getTextSize());
    }

    private void updateTextSize(float textSize){
        strokePaint.setTextSize(textSize);
        textPaint.setTextSize(textSize);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    @Override
    public void setTextSize(float size){
        super.setTextSize(size);
        updateTextSize(size);
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Get text as string
        String text = getText().toString();
        // Calculate x and y coordinates for centering text
        float x = (getWidth() - strokePaint.measureText(text)) / 2;
        Paint.FontMetrics fontMetrics = strokePaint.getFontMetrics();
        float y = getHeight() / 2 - (fontMetrics.descent + fontMetrics.ascent) / 2;

        // Draw stroke
        canvas.drawText(text, x, y, strokePaint);

        // Draw text
        canvas.drawText(text, x, y, textPaint);
    }

}
