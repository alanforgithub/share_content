/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ntian.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.camera.Log;
import com.android.camera.ui.Rotatable;
import com.android.camera.ui.TwoStateImageView;

/**
 * A @{code ImageView} which can rotate it's content.
 */
public class RotateImageView extends TwoStateImageView implements Rotatable {
	private static final String TAG = "RotateImageView";

	private static final int ANIMATION_SPEED = 270; // 270 deg/sec

	private int mCurrentDegree = 0; // [0, 359]
	private int mStartDegree = 0;
	private int mTargetDegree = 0;

	private boolean mClockwise = false;
	private boolean mEnableAnimation = true;

	private long mAnimationStartTime = 0;
	private long mAnimationEndTime = 0;

	public RotateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RotateImageView(Context context) {
		this(context, null);
	}

	protected int getDegree() {
		return mTargetDegree;
	}

	// Rotate the view counter-clockwise
	@Override
	public void setOrientation(int degree, boolean animation) {
		Log.d(TAG, "setOrientation(" + degree + ", " + animation
				+ ") mOrientation=" + mTargetDegree);
		mEnableAnimation = animation;
		// make sure in the range of [0, 359]
		degree = degree >= 0 ? degree % 360 : degree % 360 + 360;
		if (degree == mTargetDegree) {
			return;
		}

		mTargetDegree = degree;
		if (mEnableAnimation) {
			mStartDegree = mCurrentDegree;
			mAnimationStartTime = AnimationUtils.currentAnimationTimeMillis();

			int diff = mTargetDegree - mCurrentDegree;
			diff = diff >= 0 ? diff : 360 + diff; // make it in range [0, 359]

			// Make it in range [-179, 180]. That's the shorted distance between
			// the
			// two angles
			diff = diff > 180 ? diff - 360 : diff;

			mClockwise = diff >= 0;
			mAnimationEndTime = mAnimationStartTime + Math.abs(diff) * 1000
					/ ANIMATION_SPEED;
		} else {
			mCurrentDegree = mTargetDegree;
		}

		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if (drawable == null) {
			Log.e(TAG, "drawable == null, return");
			return;
		}

		Rect bounds = drawable.getBounds();
		int w = bounds.right - bounds.left;
		int h = bounds.bottom - bounds.top;

		if (w == 0 || h == 0) {
			Log.e(TAG, "w == 0 || h == 0, return");
			return; // nothing to draw
		}

		if (mCurrentDegree != mTargetDegree) {
			long time = AnimationUtils.currentAnimationTimeMillis();
			if (time < mAnimationEndTime) {
				int deltaTime = (int) (time - mAnimationStartTime);
				int degree = mStartDegree + ANIMATION_SPEED
						* (mClockwise ? deltaTime : -deltaTime) / 1000;
				degree = degree >= 0 ? degree % 360 : degree % 360 + 360;
				mCurrentDegree = degree;
				invalidate();
			} else {
				mCurrentDegree = mTargetDegree;
			}
		}

		int left = getPaddingLeft();
		int top = getPaddingTop();
		int right = getPaddingRight();
		int bottom = getPaddingBottom();
		int width = getWidth() - left - right;
		int height = getHeight() - top - bottom;

		int saveCount = canvas.getSaveCount();

		// Scale down the image first if required.
		if ((getScaleType() == ImageView.ScaleType.FIT_CENTER)
				&& ((width < w) || (height < h))) {
			float ratio = Math.min((float) width / w, (float) height / h);
			canvas.scale(ratio, ratio, width / 2.0f, height / 2.0f);
		}
		canvas.translate(left + width / 2, top + height / 2);
		canvas.rotate(-mCurrentDegree);
		canvas.translate(-w / 2, -h / 2);
		drawable.draw(canvas);
		canvas.restoreToCount(saveCount);
	}

	private Bitmap mThumb;
	private Drawable[] mThumbs;
	private TransitionDrawable mThumbTransition;

	public void setBitmap(Bitmap bitmap) {
		// Make sure uri and original are consistently both null or both
		// non-null.
		if (bitmap == null) {
			mThumb = null;
			mThumbs = null;
			setImageDrawable(null);
			setVisibility(GONE);
			return;
		}

		LayoutParams param = getLayoutParams();
		final int miniThumbWidth = param.width - getPaddingLeft()
				- getPaddingRight();
		final int miniThumbHeight = param.height - getPaddingTop()
				- getPaddingBottom();
		mThumb = ThumbnailUtils.extractThumbnail(getCroppedBitmap(bitmap),
				miniThumbWidth, miniThumbHeight);

		// mThumb = ;
		if (mThumbs == null || !mEnableAnimation) {
			mThumbs = new Drawable[2];
			mThumbs[1] = new BitmapDrawable(getContext().getResources(), mThumb);
			setImageDrawable(mThumbs[1]);
		} else {
			mThumbs[0] = new BitmapDrawable(getContext().getResources(), mThumb);
			mThumbs[1] = new BitmapDrawable(getContext().getResources(), mThumb);
			mThumbTransition = new TransitionDrawable(mThumbs);
			setImageDrawable(mThumbTransition);
			mThumbTransition.startTransition(500);
		}
		setVisibility(VISIBLE);

		// mThumb = getCroppedBitmap(bitmap);
		// setImageDrawable(new BitmapDrawable(getContext().getResources(),
		// mThumb));
		// setVisibility(VISIBLE);
	}

	/**
	 * Crops a circle out of the thumbnail photo.
	 */
	public Bitmap getCroppedBitmap(Bitmap mBitmap) {

		Bitmap bitmap = mBitmap;

		while (bitmap.getWidth() < 180 * 2 || bitmap.getHeight() < 180 * 2) {

			Matrix matrix = new Matrix();
			matrix.postScale(1.5f, 1.5f);
			// 缩放原图
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
		}

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);

		// 设置一个图片大小的矩形
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		// bm是一个刚好canvas大小的空Bitmap ，画完后应该会自动保存到bm
		Canvas canvas = new Canvas(output);

		final Paint paint = new Paint();
		paint.setAntiAlias(true);

		int halfWidth = bitmap.getWidth() / 2;
		int halfHeight = bitmap.getHeight() / 2;
		// 画圆
		// canvas.drawCircle(halfWidth, halfHeight, Math.max(halfWidth,
		// halfHeight), paint);
		canvas.drawCircle(halfWidth, halfHeight, 208, paint);
		// 设置为取两层图像交集部门,只显示上层图像
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// 画图像
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
}
