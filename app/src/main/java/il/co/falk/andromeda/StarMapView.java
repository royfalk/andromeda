package il.co.falk.andromeda;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import il.co.falk.andromeda.game.Colony;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Universe;

/**
 * Created by roy on 1/21/15.
 */
public class StarMapView extends View  {
    Paint paint;

    public StarMapView(Context context) {
        super(context);
        init(context);
    }

    public StarMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StarMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
    }


    //public void initStarMapView(Context context, Universe universe, RelativeLayout relativeLayout, int width, int height) {
    public void initStarMapView(Context context, Universe universe) {

        /*planetLabels = new ArrayList<>();
        planetIcons = new ArrayList<>();*/

        /*for(Planet p : universe.planets) {
            // create planet labels
            TextView tv = new TextView(context);
            tv.setText(p.name);
            relativeLayout.addView(tv);
            float x = width/104 * (1+p.location.x);
            float y = height/104 * (1+p.location.y);
            tv.setX(x);
            tv.setY(y);
            planetLabels.add(tv);
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tv = (TextView)v;
                    Log.d("clickclick", (String)tv.getText());
                }
            });

            // create planet icons

        }*/

        // Custom Drawing
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        /*if (mTextHeight == 0) {
            mTextHeight = mTextPaint.getTextSize();
        } else {
            mTextPaint.setTextSize(mTextHeight);
        }

        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));*/

    }

    protected void onDraw(Canvas canvas) {
        Universe universe = Universe.getUniverse();
        int width = getWidth();
        int height = getHeight();

        for(Planet p : universe.planets) {
            float x = width/104 * (1+p.location.x);
            float y = height/104 * (1+p.location.y);
            canvas.drawCircle(x,y,5, paint);
        }
    }
}


