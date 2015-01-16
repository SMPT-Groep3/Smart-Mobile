package com.example.poc3drender;

import java.io.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;


import com.threed.jpct.*;
/**
 * A simple demo. This shows more how to use jPCT-AE than it shows how to write
 * a proper application for Android.
 * It includes basic activity management to handle pause and resume...
 * 
 * @author EgonOlsen
 * 
 */
public class MainActivity extends Activity {
   	private String thingName = "temp";
   	private int thingScale = 1;//end 
	private static MainActivity master = null;

	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer buffer = null;
	
	private World world = null;
	private RGBColor back = new RGBColor(50, 50, 100);

	private float touchTurn = 0;
	private float touchTurnUp = 0;

	private float xpos = -1;
	private float ypos = -1;

	private Object3D thing = null;
	private int fps = 0;

	private Light sun = null;
	private Camera cam = null;
	
	AssetManager assMan;
	InputStream is;
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mGLView = new GLSurfaceView(getApplication());
		renderer = new MyRenderer();
		mGLView.setRenderer(renderer);
		setContentView(mGLView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	protected void onStop() {
		super.onStop();
	}

	public boolean onTouchEvent(MotionEvent me) {

		if (me.getAction() == MotionEvent.ACTION_DOWN) {
			xpos = me.getX();
			ypos = me.getY();
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_UP) {
			xpos = -1;
			ypos = -1;
			touchTurn = 0;
			touchTurnUp = 0;
			return true;
		}

		if (me.getAction() == MotionEvent.ACTION_MOVE) {
			float xd = me.getX() - xpos;
			float yd = me.getY() - ypos;

			xpos = me.getX();
			ypos = me.getY();

			touchTurn = xd / -100f;
			touchTurnUp = yd / -100f;
			return true;
		}

		try {
			Thread.sleep(15);
		} catch (Exception e) {
			// No need for this...
		}

		return super.onTouchEvent(me);
	}

	protected boolean isFullscreenOpaque() {
		return true;
	}

	class MyRenderer implements GLSurfaceView.Renderer {

		private long time = System.currentTimeMillis();
		private boolean stop = false;

		public MyRenderer() {
		}

		public void stop() {
			stop = true;
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			if (buffer != null) {
				buffer.dispose();
			}
			buffer = new FrameBuffer(gl, w, h);

			if (master == null) {

				world = new World();
        		world.setAmbientLight(150, 150, 150);
        		

				sun = new Light(world);
				sun.setIntensity(250, 250, 250);

				thing = loadModel("mpm_F21.mp3", thingScale);
				thing.scale(0.15f);
        		thing.build();

				world.addObject(thing);
				world.setClippingPlanes(1f, 20000f);

				cam = world.getCamera();
				cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
				cam.lookAt(thing.getTransformedCenter());
				
				

				SimpleVector sv = new SimpleVector();
				sv.set(thing.getTransformedCenter());
				sv.y -= 100;
				sv.z -= 100;
				sun.setPosition(sv);
				//MemoryHelper.compact();

				if (master == null) {
					Logger.log("Saving master Activity!");
					master = MainActivity.this;
				}
			}
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		}

		public void onDrawFrame(GL10 gl) {

			try {
				if (!stop) {
					if (touchTurn != 0) {
						thing.rotateY(touchTurn);
						touchTurn = 0;
					}

					if (touchTurnUp != 0) {
						thing.rotateX(touchTurnUp);
						touchTurnUp = 0;
					}

					buffer.clear(back);
					world.renderScene(buffer);
					world.draw(buffer);
					buffer.display();

					if (System.currentTimeMillis() - time >= 1000) {
						Logger.log(fps + "fps");
						fps = 0;
						time = System.currentTimeMillis();
					}
					fps++;
				} else {
					if (buffer != null) {
						buffer.dispose();
						buffer = null;
					}
				}
			} catch (Exception e) {
				Logger.log(e, Logger.MESSAGE);
			}
		}
		
	    private Object3D loadModel(String filename, float scale) {
			try {
				return Object3D.mergeAll(Loader.loadOBJ(getResources().getAssets().open(filename), null, 20));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }
	}
}


