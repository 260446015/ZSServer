package com.huishu.ait.common.enums;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 图片类型枚举类
 * @author wjc
 *
 */
public enum PictureTypes {
	
	PNG("png", Workbook.PICTURE_TYPE_PNG),
	JPEG("jpeg", Workbook.PICTURE_TYPE_JPEG);

	private PictureTypes(String pictureType, int poiPictureType){
		this.pictureType = pictureType;
		this.poiPictureType = poiPictureType;
	}
	
	private String pictureType;//图片类型
	private int poiPictureType;//POI的图片类型
	
	public String getPictureType() {
		return pictureType;
	}
	public int getPoiPictureType() {
		return poiPictureType;
	}
	
}
