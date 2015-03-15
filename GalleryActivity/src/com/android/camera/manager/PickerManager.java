package com.android.camera.manager;

import android.view.View;

import com.android.camera.Camera;
import com.android.camera.CameraSettings;
import com.android.camera.IconListPreference;
import com.android.camera.ListPreference;
import com.android.camera.Log;
import com.android.camera.ModeChecker;
import com.android.camera.SettingChecker;
import com.android.camera.ui.PickerButton;
import com.android.camera.ui.PickerButton.Listener;
import com.android.gallery3d.R;
import com.ntian.listener.OnCurrentModeListener;

public class PickerManager extends ViewManager implements Listener, Camera.OnPreferenceReadyListener,
        Camera.OnParametersReadyListener {
    private static final String TAG = "PickerManager";
    
    public interface PickerListener {
        boolean onSlowMotionPicked(String turnon);
        boolean onCameraPicked(int camerId);
        boolean onFlashPicked(String flashMode);
        boolean onStereoPicked(boolean stereoType);
        boolean onModePicked(int mode, String value, ListPreference preference);
    }
    
    private PickerButton mSlowMotion;
    private PickerButton mGestureShot;
    private PickerButton mHdr;
    private PickerButton mSmileShot;
    private PickerButton mFlashPicker;
    private PickerButton mCameraPicker;
    private PickerButton mStereoPicker;
    private PickerListener mListener;
    private boolean mNeedUpdate;
    private boolean mPreferenceReady;
    
    private static final int PICKER_BUTTON_NUM = 7;
    private static final int BUTTON_SMILE_SHOT = 0;
    private static final int BUTTON_HDR = 1;
    private static final int BUTTON_FLASH = 2;
    private static final int BUTTON_CAMERA = 3;
    private static final int BUTTON_STEREO = 4;
    private static final int BUTTON_SLOW_MOTION = 5;
    private static final int BUTTON_GESTURE_SHOT = 6;
    private PickerButton[] mPickerButtons= new PickerButton[PICKER_BUTTON_NUM]; 
    
    public PickerManager(Camera context) {
        super(context);
        context.addOnPreferenceReadyListener(this);
        context.addOnParametersReadyListener(this);
    }
    
    @Override
    protected View getView() {
        View view = inflate(R.layout.onscreen_pickers);
        
        mSlowMotion = (PickerButton)view.findViewById(R.id.onscreen_slow_motion_picker);
        mGestureShot = (PickerButton)view.findViewById(R.id.onscreen_gesture_shot_picker);
        mSmileShot = (PickerButton)view.findViewById(R.id.onscreen_smile_shot_picker);
        mHdr = (PickerButton)view.findViewById(R.id.onscreen_hdr_picker);
        mFlashPicker = (PickerButton)view.findViewById(R.id.onscreen_flash_picker);
        mCameraPicker = (PickerButton)view.findViewById(R.id.onscreen_camera_picker);
        mStereoPicker = (PickerButton)view.findViewById(R.id.onscreen_stereo3d_picker);
        
        
        //modify by wgb ,hide the icon
        mSlowMotion.setVisibility(View.GONE);
        mGestureShot.setVisibility(View.GONE);
        mSmileShot.setVisibility(View.GONE);
        mHdr.setVisibility(View.GONE);
        mFlashPicker.setVisibility(View.GONE);
        mCameraPicker.setVisibility(View.GONE);
        mStereoPicker.setVisibility(View.GONE);
        
        mPickerButtons[BUTTON_SLOW_MOTION] = mSlowMotion;
        mPickerButtons[BUTTON_GESTURE_SHOT] = mGestureShot;
        mPickerButtons[BUTTON_SMILE_SHOT] = mSmileShot;
        mPickerButtons[BUTTON_HDR] = mHdr;
        mPickerButtons[BUTTON_FLASH] = mFlashPicker;  //ok闪光灯
        mPickerButtons[BUTTON_CAMERA] = mCameraPicker; //ok前后摄像头
        mPickerButtons[BUTTON_STEREO] = mStereoPicker;
        
        mPickerButtons[BUTTON_FLASH].setOnCurrentModeListener(new OnCurrentModeListener() {
			
			@Override
			public void currentMode(Object type, Object mode) {
				
				if(null != mOnCurrentModeListener){
					mOnCurrentModeListener.currentMode(FLASH_MODE, mode);
				}
				
			}
		});
        
        
        applyListeners();
        return view;
    }
    
    private OnCurrentModeListener mOnCurrentModeListener;
    /**
     * 模式监听
     * @param mOnCurrentModeListener
     */
    public void setOnCurrentModeListener(OnCurrentModeListener mOnCurrentModeListener){
    	this.mOnCurrentModeListener = mOnCurrentModeListener;
    }
    
    /**
     * 切换摄像头
     */
    public void switchCamera(){
    	mPickerButtons[BUTTON_CAMERA].nTianOnClick();
    }
    
    /**
     * 切换闪光灯模式
     */
    public void switchFlashMode(){
    	mPickerButtons[BUTTON_FLASH].nTianOnClick();
    }
    
    /**
     * DHR开关
     */
    public void switchDHR(){
    	mPickerButtons[BUTTON_HDR].nTianOnClick();
    }
    
    private void applyListeners() {
    	if (mSlowMotion != null ) {
    	    mSlowMotion.setListener(this);
    	}
    	if (mGestureShot != null) {
    		mGestureShot.setListener(this);
        }
    	/*if (mSmileShot != null) {
            mSmileShot.setListener(this);
        }*/
    	if (mHdr != null) {
            mHdr.setListener(this);
        }
        if (mFlashPicker != null) {
            mFlashPicker.setListener(this);
            mFlashPicker.setFlashButton(true);  //add by ChMX for mantis 712
        }
        if (mCameraPicker != null) {
            mCameraPicker.setListener(this);
        }
        if (mStereoPicker != null) {
            mStereoPicker.setListener(this);
        }
        Log.d(TAG, "applyListeners() mFlashPicker=" + mFlashPicker + ", mCameraPicker=" + mCameraPicker
                + ", mStereoPicker=" + mStereoPicker);
    }
    
    private void clearListeners() {
        if (mSlowMotion != null ) {
            mSlowMotion.setListener(null);
        }
    	if (mGestureShot != null) {
    		mGestureShot.setListener(null);
    	}
    	/*if (mSmileShot != null) {
            mSmileShot.setListener(null);
        }*/
    	if (mHdr != null) {
            mHdr.setListener(null);
        }
        if (mFlashPicker != null) {
            mFlashPicker.setListener(null);
        }
        if (mCameraPicker != null) {
            mCameraPicker.setListener(null);
        }
        if (mStereoPicker != null) {
            mStereoPicker.setListener(null);
        }
    }
    
    public void setListener(PickerListener listener) {
        mListener = listener;
    }
    
    @Override
    public void onPreferenceReady() {
        Log.i(TAG, "onPreferenceReady()");
        mNeedUpdate = true;
        mPreferenceReady = true;
    }

    @Override
    public void onCameraParameterReady() {
        Log.i(TAG, "onCameraParameterReady()");
        if (mSlowMotion != null) {
             mSlowMotion.reloadPreference();
        }
    	if (mGestureShot != null) {
    		mGestureShot.reloadPreference();
    	}
        if (mSmileShot != null) {
            mSmileShot.setVisibility(View.GONE);
            //mSmileShot.reloadPreference();
        }
        if (mHdr != null) {
            mHdr.reloadPreference();
        }
        if (mFlashPicker != null) {
            mFlashPicker.reloadPreference();
        }
        if (mCameraPicker != null) {
            mCameraPicker.reloadPreference();
        }
        if (mStereoPicker != null) {
            mStereoPicker.reloadPreference();
        }
        refresh();
    }
    
    @Override
    public boolean onPicked(PickerButton button, ListPreference pref, String newValue) {
        boolean picked = false;
        String key = pref.getKey();
        if (mListener != null) {
        	int index = -1;
        	for (int i = 0; i < PICKER_BUTTON_NUM; i ++) {
        		if (button.equals(mPickerButtons[i])) {
        			index = i;
        			break;
        		}
        	}
        	
        	switch(index) {
        	case BUTTON_SLOW_MOTION:
        	    picked = mListener.onSlowMotionPicked(newValue);
        	    break;
        	case BUTTON_GESTURE_SHOT:  //手势拍照
        		button.setValue(newValue);
        		picked = mListener.onModePicked(ModePicker.MODE_GESTURE_SHOT, newValue, pref);
        		break;
        	case BUTTON_SMILE_SHOT:
        		button.setValue(newValue);
        		picked = mListener.onModePicked(ModePicker.MODE_SMILE_SHOT, newValue, pref);
        		break;
        	case BUTTON_HDR:   //HDR
        		button.setValue(newValue);
        		picked = mListener.onModePicked(ModePicker.MODE_HDR, newValue, pref);
        		mOnCurrentModeListener.currentMode(OnCurrentModeListener.HDR_SWITCH, newValue);
        		break;
        	case BUTTON_FLASH:  //闪光灯模式
        		picked = mListener.onFlashPicked(newValue);
        		break;
        	case BUTTON_CAMERA:  //切换摄像头
        		picked = mListener.onCameraPicked(Integer.parseInt(newValue));
        		break;
        	case BUTTON_STEREO:
        		picked = mListener.onStereoPicked(CameraSettings.STEREO3D_ENABLE.endsWith(newValue) ? true : false);
        		break;
        	default:
        		break;	
        	}
        	
        }
        Log.i(TAG, "onPicked(" + key + ", " + newValue + ") mListener=" + mListener + " return " + picked);
        return picked;
    }

    public void setCameraId(int cameraId) {
        if (mCameraPicker != null) {
            mCameraPicker.setValue("" + cameraId);
        }
    }
    
    //add by ChMX for mantis 712
    public void disableFlashState() {
    	if (mFlashPicker != null) {
    		mFlashPicker.setValueState("off");
        }
    }
    
    
    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh() mPreferenceReady=" + mPreferenceReady + ", mNeedUpdate=" + mNeedUpdate);
        if (mPreferenceReady && mNeedUpdate) {
        	mSlowMotion.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_SLOW_MOTION));
        	mGestureShot.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_GESTURE_SHOT));
        	/*mSmileShot.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_SMILE_SHOT));*/
        	mHdr.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_HDR));
            mFlashPicker.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_FLASH));
            mCameraPicker.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_DUAL_CAMERA));
            mStereoPicker.initialize((IconListPreference)getContext().getListPreference(
                    SettingChecker.ROW_SETTING_STEREO_MODE));
            mNeedUpdate = false;
        }
        if (mSlowMotion != null) {
            mSlowMotion.reloadValue();
            mSlowMotion.reloadPreference();
        }
        if (mGestureShot != null) {
        	mGestureShot.reloadPreference();
        }
        if (mSmileShot != null) {
            mSmileShot.setVisibility(View.GONE);
            //mSmileShot.reloadPreference();
        }
        if (mHdr != null) {
            mHdr.reloadPreference();
        }
        if (mFlashPicker != null) {
            //mFlashPicker.updateView();  //modify by wgb
        }
        if (mCameraPicker != null) {
            boolean visible = ModeChecker.getCameraPickerVisible(getContext());
            //mCameraPicker.setVisibility(visible ? View.VISIBLE : View.GONE);  //modify by wgb
        }
        if (mStereoPicker != null) {
            boolean visible = ModeChecker.getStereoPickerVisibile(getContext());
            //mStereoPicker.setVisibility(visible ? View.VISIBLE : View.GONE);   //modify by wgb
        }
    }
    
    @Override
    protected void onRelease() {
        super.onRelease();
        mNeedUpdate = true;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(mSlowMotion != null) {
        	mSlowMotion.setEnabled(enabled);
        	mSlowMotion.setClickable(enabled);
        }
        if (mGestureShot != null) {
        	mGestureShot.setEnabled(enabled);
        	mGestureShot.setClickable(enabled);
        }
        /*if (mSmileShot != null) {
            mSmileShot.setEnabled(enabled);
            mSmileShot.setClickable(enabled);
        }*/
        if (mHdr != null) {
            mHdr.setEnabled(enabled);
            mHdr.setClickable(enabled);
        }
        if (mFlashPicker != null) {
        	mFlashPicker.setEnabled(enabled);
            mFlashPicker.setClickable(enabled);
         }
        if (mCameraPicker != null) {
            mCameraPicker.setEnabled(enabled);
            mCameraPicker.setClickable(enabled);
        }
        if (mStereoPicker != null) {
            mStereoPicker.setEnabled(enabled);
            mStereoPicker.setClickable(enabled);
        }
    }
}
