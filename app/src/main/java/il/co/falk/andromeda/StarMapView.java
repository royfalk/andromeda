package il.co.falk.andromeda;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import il.co.falk.andromeda.game.Configuration;
import il.co.falk.andromeda.game.Planet;
import il.co.falk.andromeda.game.Player;
import il.co.falk.andromeda.game.Ship;
import il.co.falk.andromeda.game.Game;

/**
 * Created by roy on 1/21/15.
 */
public class StarMapView extends View {

    Paint paint;

    private FRect lMap, lView, pView;

    //private int x,y;
    //private float cx,cy;
    private float zoom;

    private static final String DEBUG_TAG = "star map gesture";
    private static final String[] PLANET_COLORS = {"#999999", "#FF9966", "#996633", "#99FF99", "#009999", "#00CC00"};
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mDetector;

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

        lMap = new FRect(0,0, Configuration.universeWidth, Configuration.universeHeight);
        lView = new FRect(lMap);

        zoom = 1;

        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());

        mDetector = new GestureDetector(context, new SimpleGestureListener());
    }




    protected void onDraw(Canvas canvas) {
        float width = getWidth();
        float height = getHeight();

        paint.setTextSize(32);

        for(Planet planet : Game.INSTANCE.getPlanets()) {
            if(!lView.contains(planet.location.x, planet.location.y)) continue;
            drawPlanet(canvas, width, height, planet);
            drawPlanetLabel(canvas, width, height, planet);
            drawShipsInOrbit(canvas, width, height, planet);
        }
    }

    void drawPlanet(Canvas canvas, float width, float height, Planet planet) {
        int planetColor = Color.parseColor(PLANET_COLORS[planet.production]);
        paint.setColor(planetColor);

        float x = lView.getRelativeX(planet.location.x, width);
        float y = lView.getRelativeY(planet.location.y, height);

        canvas.drawCircle(x, y, 5 * lView.z, paint);
    }

    void drawPlanetLabel(Canvas canvas, float width, float height, Planet planet) {
        float x = lView.getRelativeX(planet.location.x, width);
        float y = lView.getRelativeY(planet.location.y, height);

        if(planet.colony != null)
            paint.setColor(planet.colony.player.getColor());
        else
            paint.setColor(Color.LTGRAY);
        // TODO: x-y depending on length of planet name

        Rect b = new Rect();
        paint.getTextBounds(planet.name, 0 ,planet.name.length(), b);
        canvas.drawText(planet.name, x - b.width() / 2, y + 5 * lView.z + 20, paint);
    }

    void drawShipsInOrbit(Canvas canvas, float width, float height, Planet planet) {
        if(planet.ships.size() == 0) return;

        List<Player> presentPlayers = new ArrayList<>();
        for(Ship u : planet.ships)
            if(!presentPlayers.contains(u.player)) presentPlayers.add(u.player);

        if(presentPlayers.size()==1)
            paint.setColor(presentPlayers.get(0).getColor());
        else
            paint.setColor(Color.WHITE);

        float x = lView.getRelativeX(planet.location.x, width);
        float y = lView.getRelativeY(planet.location.y, height);

        canvas.drawRect(x - height, y - height, 5 * lView.z, 5 * lView.z, paint);
    }

    void drawShipsInTransit(Canvas canvas, float width, float height, Planet planet) {

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

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Log.d("Tap", "tap");

            float width = getWidth();
            float height = getHeight();

            for(Planet p : Game.INSTANCE.getPlanets()) {
                if (!lView.contains(p.location.x, p.location.y)) continue;

                float x = event.getX();
                float y = event.getY();

                float px = lView.getRelativeX(p.location.x, width);
                float py = lView.getRelativeY(p.location.y, height);

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

            float z = detector.getScaleFactor();
            float px = detector.getFocusX()/getWidth();
            float py = detector.getFocusY()/getHeight();

            lView.zoom(z, px, py);

            invalidate();
            return true;
        }
    }

}

class FRect {
    public float x,y, w, h, origX, origY, origW, origH, z;
    public final float MIN_ZOOM = 1, MAX_ZOOM = 4;

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

    public void zoom(float zfactor, float pX, float pY) {
        z *= zfactor;
        if(z < MIN_ZOOM) z = MIN_ZOOM;
        if(z > MAX_ZOOM) z = MAX_ZOOM;

        // TODO: This still isn't perfect but much better
        float cx = x+pX*w;
        float cy = y+pY*y;
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
        if(aX<x || aX>x+w) {
            Log.e("ERROR", "getRelativeX");
            return -1;
        }
        return (aX-x)/w*actualWidth;
    }

    public float getRelativeY(float aY, float actualheight) {
        if(aY<y || aY>y+h) {
            Log.e("ERROR", "getRelativeY");
            return -1;
        }
        return (aY-y)/h*actualheight;
    }
}

