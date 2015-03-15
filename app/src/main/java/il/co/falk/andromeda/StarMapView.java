package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
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

    //GestureDetector scrollGestureDetector;

    Paint paint;

    private FRect lMap, lView, pView;

    //private int x,y;
    //private float cx,cy;
    private float zoom;

    private static final String DEBUG_TAG = "star map gesture";
    private static final int MARGIN = 10;
    private static final String[] PLANET_COLORS = {"#999999", "#FF9966", "#996633", "#99FF99", "#009999", "#00CC00"};
    private ScaleGestureDetector mScaleDetector;
    private GestureDetectorCompat mDetector;

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
        lMap = new FRect(0,0,Universe.WIDTH, Universe.HEIGHT);
        lView = new FRect(lMap);

        /*x = 0;
        y = 0;
        cx = 0;
        cy = 0;*/

        zoom = 1;

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

        mDetector = new GestureDetectorCompat(context, new SimpleGestureListener());


        /*scrollGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
                    System.out.println("SCROLL " + distanceX + ", " + distanceY);

                    lView.move(distanceX/getWidth(), distanceY/getHeight());
                    invalidate();
                    return true;
                }
        });*/
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
        //float gridX = width/universe.WIDTH;
        //float gridY = height/universe.HEIGHT;

        //setTextSizeForWidth(paint, 40, "dododo");
        paint.setTextSize(32);

        for(Planet p : universe.planets) {
            int planetColor = Color.parseColor(PLANET_COLORS[p.production]);
            paint.setColor(planetColor);
            //float x = MARGIN + p.location.x * gridX * zoom;
            //float y = MARGIN + p.location.y * gridY * zoom;
            if(!lView.contains(p.location.x, p.location.y)) continue;

            float x = MARGIN + lView.getRelativeX(p.location.x, width);
            float y = MARGIN + lView.getRelativeY(p.location.y, height);

            //float x = MARGIN + width/(width+MARGIN) * (1+p.location.x) * zoom;
            //float y = MARGIN + height/(height+MARGIN) * (1+p.location.y) * zoom;

            canvas.drawCircle(x,y,5 * lView.z, paint);

            if(p.colony != null)
                paint.setColor(p.colony.player.color);
            else
                paint.setColor(Color.LTGRAY);
            // TODO: x-y depending on length of planet name

            String test = "test";
            Rect b = new Rect();
            paint.getTextBounds(test, 0 ,test.length(), b);
            canvas.drawText(test, x-b.width()/2,y+ 5 * lView.z + 5, paint);
        }

        //paint.setColor(Color.LTGRAY);
        //canvas.drawRect(lView.getRect(), paint);
    }




    ////////////////////////////////////////////////////////////////////////////////////////
    // Gestures
    ////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        mScaleDetector.onTouchEvent(event);
        mDetector.onTouchEvent(event);

        return true;
    }


    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        /*@Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }*/

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Log.d("Tap", "tap");

            Universe universe = Universe.getUniverse();
            float width = getWidth() - MARGIN*2;
            float height = getHeight() - MARGIN*2;

            for(Planet p : universe.planets) {
                if (!lView.contains(p.location.x, p.location.y)) continue;

                float x = event.getX();
                float y = event.getY();

                float px = MARGIN + lView.getRelativeX(p.location.x, width);
                float py = MARGIN + lView.getRelativeY(p.location.y, height);

                float radius = 5 * lView.z;

                if (x <= px + radius && x >= px - radius && y <= py + radius && y >= py - radius) {
                    Context context = getContext();
                    Intent intent = new Intent(context, PlanetActivity.class);
                    intent.putExtra(PlanetActivity.PLANET_NAME, p.name);

                    context.startActivity(intent);
                }
            }

            return true;
        }

        @Override
        public boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
            System.out.println("SCROLL " + distanceX + ", " + distanceY);

            lView.move(distanceX/getWidth(), distanceY/getHeight());
            invalidate();
            return true;
        }
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("Scale", String.valueOf(detector.getScaleFactor()));
            Universe universe = Universe.getUniverse();

            float z = detector.getScaleFactor();
            lView.zoom(z);

            invalidate();
            return true;
        }
    }

}

class FRect {
    public float x,y, w, h, origX, origY, origW, origH, z;
    public final float MIN_ZOOM = 1, MAX_ZOOM = 16;

    FRect(float x,float y,float w,float h) {
        origX = this.x = x;
        origY = this.y = y;
        origW = this.w = w;
        origH = this.h = h;
        z = 1;
    }

    FRect(FRect rect) {
        origX = this.x = rect.x;
        origY = this.y = rect.y;
        origW = this.w = rect.w;
        origH = this.h = rect.h;
        z = 1;
    }

    public boolean contains(float x, float y) {
        if(x<this.x || y<this.y || x>this.x+w || y>this.y+h) return false;
        return true;
    }

    public void zoom(float zfactor) {
        z *= zfactor;
        if(z < MIN_ZOOM) z = MIN_ZOOM;
        if(z > MAX_ZOOM) z = MAX_ZOOM;

        float cx = (x+w)/2;
        float cy = (y+h)/2;
        w = origW / z;
        h = origH / z;

        x = cx - w/2;
        if(x<0) x=0;
        if(x+w>origW) x=origW-w;

        y = cy - h/2;
        if(y<0) y=0;
        if(y+h>origH) y=origH-h;
    }

    public void move(float dx, float dy) {
        x += dx*w;
        if(x<0) x=0;
        if(x+w>origW) x=origW-w;

        y += dy*h;
        if(y<0) y=0;
        if(y+h>origH) y=origH-h;
    }

    public Rect getRect() {
        int ix = Math.round(x);
        int iy = Math.round(y);
        int iw = Math.round(w);
        int ih = Math.round(h);
        return new Rect(ix, iy, ix+iw, iy+ih);
    }

    public float getRelativeX(float aX, float actualWidth) {
        if(aX<x || aX>x+w) return -1;
        return (aX-x)/w*actualWidth;
    }

    public float getRelativeY(float aY, float actualheight) {
        if(aY<x || aY>y+h) return -1;
        return (aY-y)/h*actualheight;
    }
}

