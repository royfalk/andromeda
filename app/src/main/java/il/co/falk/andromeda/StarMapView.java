package il.co.falk.andromeda;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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
public class StarMapView extends View {
        //implements
        //GestureDetector.OnGestureListener,
        //GestureDetector.OnDoubleTapListener  {

    GestureDetector scrollGestureDetector;

    Paint paint;

    private Rect lMap, lView;
    //private Rect somthing;

    private GestureDetectorCompat mDetector;

    //private int x,y;
    //private float cx,cy;
    private float zoom;

    private static final String DEBUG_TAG = "star map gesture";
    private static final int MARGIN = 10;
    private static final String[] PLANET_COLORS = {"#999999", "#FF9966", "#996633", "#99FF99", "#009999", "#00CC00"};
    private ScaleGestureDetector mScaleDetector;

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

        //mDetector = new GestureDetectorCompat(context,this);
        //mDetector.setOnDoubleTapListener(this);

        //Universe u = Universe.getUniverse();
        lMap = new Rect(0,0,Universe.WIDTH, Universe.HEIGHT);
        lView = new Rect(lMap);

        /*x = 0;
        y = 0;
        cx = 0;
        cy = 0;*/

        zoom = 1;

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

        scrollGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
                    System.out.println("SCROLL " + distanceX + ", " + distanceY);
                    int l = (int)(lView.left - distanceX);
                    int r = (int)(lView.right - distanceX);
                    int t = (int)(lView.top - distanceY);
                    int b = (int)(lView.bottom - distanceY);
                    lView.set(l,t,r,b);
                    invalidate();
                    return true;
                }
        });
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

            canvas.drawCircle(x,y,5 * zoom, paint);

            if(p.colony != null)
                paint.setColor(p.colony.player.color);
            else
                paint.setColor(Color.LTGRAY);
            // TODO: x-y depending on length of planet name
            canvas.drawText("test", x-10,y+15, paint);
        }

        paint.setColor(Color.LTGRAY);
        canvas.drawRect(lView, paint);
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    // Scroll
    ////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////
    // Gestures
    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent event){
        mScaleDetector.onTouchEvent(event);
        scrollGestureDetector.onTouchEvent(event);
        //this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return true; //super.onTouchEvent(event);
    }

    /*@Override
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
    }*/




private class ScaleListener
        extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        Log.d("Scale", String.valueOf(detector.getScaleFactor()));

        float cx = detector.getFocusX();
        float cy = detector.getFocusY();

        zoom *= detector.getScaleFactor();
        if(zoom<1) zoom = 1;
        if(zoom>16) zoom = 16;

        int zwidth = Math.round(lMap.width()/zoom);
        int zheight = Math.round(lMap.height()/zoom);

        int zx = Math.round(Math.min(cx -  zwidth/2, zwidth/2));
        int zy = Math.round(Math.min(cy -  zheight/2, zheight/2));

        lView = new Rect(zx, zy, zwidth, zheight);

        invalidate();
        /*mScaleFactor *= detector.getScaleFactor();

        // Don't let the object get too small or too large.
        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

        invalidate();*/



        return true;
    }
}

}


