/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhishinet.image.request;

import com.zhishinet.image.common_utils.CommonParamCheckUtils;
import com.zhishinet.image.exception.ParamException;

/**
 *
 * @author serenazhao  获取人脸列表
 */
public class FaceGetFaceInfoRequest extends AbstractBaseRequest {
        
        //要查询的人脸Id
        private String faceId;
        
        public FaceGetFaceInfoRequest(String bucketName, String faceId) {
		super(bucketName);
                this.faceId = faceId;
	}
        
        public String getFaceId() {
                return faceId;              
        }

	@Override
	public void check_param() throws ParamException {
		super.check_param();
                CommonParamCheckUtils.AssertNotNull("faceId", faceId);
	}
}
