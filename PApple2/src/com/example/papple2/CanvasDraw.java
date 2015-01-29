package com.example.papple2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;

public class CanvasDraw extends SurfaceView {

	private static final int INVALID_POINTER_ID = -1;

	private int mActivePointerId = INVALID_POINTER_ID;

	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;

	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;

	private Bitmap img;

	public CanvasDraw(Context context) {
		super(context);

		this.setFocusable(true);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		mPosX = 0.00f;
		mPosY = 0.00f;
		mScaleFactor = 1.00f;
		setWillNotDraw(false);
		// Veranderen in aan te passen onderdeel
		if (img == null) {
			img = BitmapFactory.decodeResource(getResources(),
					R.drawable.shelloutline);
		}

		invalidate();
	}

	public CanvasDraw(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.setFocusable(true);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		mPosX = 0.00f;
		mPosY = 0.00f;
		mScaleFactor = 1.00f;
		setWillNotDraw(false);
		img = BitmapFactory.decodeResource(getResources(),
				R.drawable.shelloutline);
		invalidate();
	}

	public CanvasDraw(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setFocusable(true);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		mPosX = 0.00f;
		mPosY = 0.00f;
		mScaleFactor = 1.00f;
		setWillNotDraw(false);
		img = BitmapFactory.decodeResource(getResources(),
				R.drawable.shelloutline);
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// Let the ScaleGestureDetector inspect all events.
		mScaleDetector.onTouchEvent(ev);

		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			mLastTouchX = x;
			mLastTouchY = y;
			mActivePointerId = ev.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			// Only move if the ScaleGestureDetector isn't processing a gesture.
			if (!mScaleDetector.isInProgress()) {
				final float dx = x - mLastTouchX;
				final float dy = y - mLastTouchY;

				mPosX += dx;
				mPosY += dy;

				transformItem();
			}

			mLastTouchX = x;
			mLastTouchY = y;

			break;
		}

		case MotionEvent.ACTION_UP: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = ev.getPointerId(pointerIndex);
			if (pointerId == mActivePointerId) {
				// This was our active pointer going up. Choose a new
				// active pointer and adjust accordingly.
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				mLastTouchX = ev.getX(newPointerIndex);
				mLastTouchY = ev.getY(newPointerIndex);
				mActivePointerId = ev.getPointerId(newPointerIndex);
			}
			break;
		}
		}

		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

		for (LayerItem i : LayerItemProvider.getInstance().getItems()) {
			i.Draw(canvas);
		}
		img = Bitmap.createScaledBitmap(img, canvas.getWidth(),
				canvas.getHeight(), true);

		canvas.drawBitmap(img, new Matrix(), null);
	}

	private void transformItem() {

		LayerItem i = layerAdapter.getSelectedItem();
		if (i != null) {
			if (i.getSoort() == 0) {
				i.setMatrix(mPosX, mPosY, mScaleFactor);
			} else if (i.getSoort() == 1) {
				i.setXY((int) mPosX, (int) mPosY);
			}
		}

		invalidate();
	}

	public void resetMatrix() {
		mPosX = 0.00f;
		mPosY = 0.00f;
		mScaleFactor = 1.00f;
		mLastTouchX = 1.00f;
		mLastTouchY = 1.00f;
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));

			transformItem();
			return true;
		}
	}

	public void setPartImage(Bitmap partImg) {
		img = partImg;
	}

}
