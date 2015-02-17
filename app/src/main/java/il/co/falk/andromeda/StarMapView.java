package il.co.falk.andromeda;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class StarMapView extends View implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener  {
    Paint paint;
    private GestureDetectorCompat mDetector;

    private int x,y,zoom;

    private static final String DEBUG_TAG = "star map gesture";
    private static final int MARGIN = 10;
    private static final String[] PLANET_COLORS = {"#999999", "#FF9966", "#996633", "#99FF99", "#009999", "#00CC00"};

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

        mDetector = new GestureDetectorCompat(context,this);
        mDetector.setOnDoubleTapListener(this);

        x = 0;
        y = 0;
        zoom = 1;
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
        float width = getWidth() - MARGIN*2;
        float height = getHeight() - MARGIN*2;
        float gridX = width/universe.WIDTH;
        float gridY = height/universe.HEIGHT;

        for(Planet p : universe.planets) {
            int planetColor = Color.parseColor(PLANET_COLORS[p.production]);
            paint.setColor(planetColor);
            float x = MARGIN + p.location.x * gridX * zoom;
            float y = MARGIN + p.location.y * gridY * zoom;
            //float x = MARGIN + width/(width+MARGIN) * (1+p.location.x) * zoom;
            //float y = MARGIN + height/(height+MARGIN) * (1+p.location.y) * zoom;

            canvas.drawCircle(x,y,5 + zoom, paint);

            if(p.colony != null)
                paint.setColor(p.colony.player.color);
            else
                paint.setColor(Color.LTGRAY);
            // TODO: x-y depending on length of planet name
            canvas.drawText("test", x-10,y+15, paint);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // Gestures
    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());

        if(zoom>1) zoom/=2;
        Log.d("zoom", String.valueOf(zoom));

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());

        if(zoom<16) zoom*=2;
        Log.d("zoom", String.valueOf(zoom));

        return true;
    }

}


