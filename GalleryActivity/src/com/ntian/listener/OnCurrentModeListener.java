package com.ntian.listener;

/**
 * 监听当前模式
 * @author guangbingw
 *
 */
public interface OnCurrentModeListener {
	/** 闪光灯模式 */
	public final int FLASH_MODE = 10001;
	/**HDR*/
	public final int HDR_SWITCH = 10002;
	void currentMode(Object type,Object mode);

}
