package org.onebusaway.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import org.onebusaway.android.R;

public class BikeStatusImageView extends AppCompatImageView {
    private boolean struckOut;

    public BikeStatusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BikeStatusImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStrokeWidth(3);
        p.setColor(getResources().getColor(R.color.stop_info_occupancy_historical));
        canvas.drawLine(0,40,40,0,p);
    }

    public void addSlash() {

    }
}
