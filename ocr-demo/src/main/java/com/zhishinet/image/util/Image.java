package com.zhishinet.image.util;

import com.zhishinet.image.request.FaceAddFaceRequest;
import com.zhishinet.image.request.FaceAddGroupIdsRequest;
import com.zhishinet.image.request.FaceCompareRequest;
import com.zhishinet.image.request.FaceDelFaceRequest;
import com.zhishinet.image.request.FaceDelGroupIdsRequest;
import com.zhishinet.image.request.FaceDelPersonRequest;
import com.zhishinet.image.request.FaceDetectRequest;
import com.zhishinet.image.request.FaceGetFaceIdsRequest;
import com.zhishinet.image.request.FaceGetFaceInfoRequest;
import com.zhishinet.image.request.FaceGetGroupIdsRequest;
import com.zhishinet.image.request.FaceGetInfoRequest;
import com.zhishinet.image.request.FaceGetPersonIdsRequest;
import com.zhishinet.image.request.FaceIdCardCompareRequest;
import com.zhishinet.image.request.FaceIdCardLiveDetectFourRequest;
import com.zhishinet.image.request.FaceIdentifyRequest;
import com.zhishinet.image.request.FaceLiveDetectFourRequest;
import com.zhishinet.image.request.FaceLiveDetectPictureRequest;
import com.zhishinet.image.request.FaceLiveGetFourRequest;
import com.zhishinet.image.request.FaceMultiIdentifyRequest;
import com.zhishinet.image.request.FaceNewPersonRequest;
import com.zhishinet.image.request.FaceSetInfoRequest;
import com.zhishinet.image.request.FaceShapeRequest;
import com.zhishinet.image.request.FaceVerifyRequest;
import com.zhishinet.image.request.GeneralOcrRequest;
import com.zhishinet.image.request.IdcardDetectRequest;
import com.zhishinet.image.request.NamecardDetectRequest;
import com.zhishinet.image.request.OcrBankCardRequest;
import com.zhishinet.image.request.OcrBizLicenseRequest;
import com.zhishinet.image.request.OcrDrivingLicenceRequest;
import com.zhishinet.image.request.OcrPlateRequest;
import com.zhishinet.image.request.PornDetectRequest;
import com.zhishinet.image.request.TagDetectRequest;
        
/**
 * @author chengwu
 * Image提供给用户使用的API接口
 */

public interface Image {

    /**
     * OCR-车牌识别
     */
    String ocrPlate(OcrPlateRequest request);

    /**
     * OCR-银行卡识别
     */
    String ocrBankCard(OcrBankCardRequest request);

    /**
     * OCR-营业执照识别
     */
    String ocrBizLicense(OcrBizLicenseRequest request);

    /**
     * OCR-行驶证驾驶证识别
     */
    String ocrDrivingLicence(OcrDrivingLicenceRequest request);

    /**
     * OCR-通用印刷体识别
     */
    String generalOcr(GeneralOcrRequest request);

	/**
	 * OCR-手写体识别
	 */
	String handwritingOcr(GeneralOcrRequest request);

    /**
	 * 黄图识别接口
	 *
	 * @param request
	 *            黄图识别请求
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String pornDetect(PornDetectRequest request);

    /**
	 *标签识别接口
	 *
	 * @param request
	 *            标签识别请求
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String tagDetect(TagDetectRequest request);

    /**
	 *身份证识别接口
	 *
	 * @param request
	 *            身份证识别请求
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String idcardDetect(IdcardDetectRequest request);

        /**
	 *名片识别接口
	 *
	 * @param request
	 *            名片ocr识别请求
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String namecardDetect(NamecardDetectRequest request);

        /**
	 *人脸检测接口
	 *
	 * @param request
	 *            人连检测请求
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String faceDetect(FaceDetectRequest request);

        /**
	 *人脸定位接口
	 *
	 * @param request
	 *            人脸定位接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
    String faceShape(FaceShapeRequest request);

        /**
	 *个体创建接口
	 *
	 * @param request
	 *            个体创建接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceNewPerson(FaceNewPersonRequest request);

     /**
	 *个体删除接口
	 *
	 * @param request
	 *            个体删除接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceDelPerson(FaceDelPersonRequest request);

      /**
	 *增加人脸接口
	 *
	 * @param request
	 *            增加人脸接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceAddFace(FaceAddFaceRequest request);

        /**
	 *删除人脸接口
	 *
	 * @param request
	 *            删除人脸接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceDelFace(FaceDelFaceRequest request);

     /**
	 *个体设置信息接口
	 *
	 * @param request
	 *            人脸设置信息接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceSetInfo(FaceSetInfoRequest request);

     /**
	 *个体获取信息接口
	 *
	 * @param request
	 *            个体获取信息接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceGetInfo(FaceGetInfoRequest request);

      /**
	 *获取组列表接口
	 *
	 * @param request
	 *            获取组列表接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */
     String faceGetGroupIds(FaceGetGroupIdsRequest request);

    /**
     * Person新增组信息 https://cloud.tencent.com/document/product/641/12417
     * @param useNewDomain 是否使用新域名，<br>
     * true: http://recognition.image.myqcloud.com/face/multidentify <br>
     * false: http://service.image.myqcloud.com/face/multidentify <br>
     * 如果开发者使用的是原域名（service.image.myqcloud.com）且已产生调用，则无需更换域名。
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
     * 其他为失败, message为success或者失败原因
     */
    String faceAddGroupIds(FaceAddGroupIdsRequest request, boolean useNewDomain);

    /**
     * Person删除组信息 https://cloud.tencent.com/document/product/641/12417
     * @param useNewDomain 是否使用新域名，<br>
     * true: http://recognition.image.myqcloud.com/face/multidentify <br>
     * false: http://service.image.myqcloud.com/face/multidentify <br>
     * 如果开发者使用的是原域名（service.image.myqcloud.com）且已产生调用，则无需更换域名。
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
     * 其他为失败, message为success或者失败原因
     */
    String faceDelGroupIds(FaceDelGroupIdsRequest request, boolean useNewDomain);
     
      /**
	 *获取人列表接口
	 * 
	 * @param request
	 *            获取人列表接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceGetPersonIds(FaceGetPersonIdsRequest request);
     
    /**
	 *获取人脸列表接口
	 * 
	 * @param request
	 *            获取人脸列表接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceGetFaceIds(FaceGetFaceIdsRequest request);
     
      /**
	 *获取人脸信息接口
	 * 
	 * @param request
	 *            获取人脸信息接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceGetFaceInfo(FaceGetFaceInfoRequest request);
     
       /**
	 *获取人脸识别接口
	 * 
	 * @param request
	 *            获取人脸识别接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceIdentify(FaceIdentifyRequest request);
     
     /**
	 *人脸验证接口
	 * 
	 * @param request
	 *            人脸验证接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceVerify(FaceVerifyRequest request);

    /**
     * 人脸对比接口
     * @param request 人脸对比接口
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
     * 其他为失败, message为success或者失败原因
     */
    String faceCompare(FaceCompareRequest request);

    /**
     * 多脸检索
     * @param useNewDomain 是否使用新域名，<br>
     * true: http://recognition.image.myqcloud.com/face/multidentify <br>
     * false: http://service.image.myqcloud.com/face/multidentify <br>
     * 如果开发者使用的是原域名（service.image.myqcloud.com）且已产生调用，则无需更换域名。
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
     * 其他为失败, message为success或者失败原因
     */
    String faceMultiIdentify(FaceMultiIdentifyRequest request, boolean useNewDomain);
     
     /**
	 *身份证对比接口
	 * 
	 * @param request
	 *            身份证对比接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceIdCardCompare(FaceIdCardCompareRequest request);
     
        /**
	 *获取验证码接口
	 * 
	 * @param request
	 *            获取验证码接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceLiveGetFour(FaceLiveGetFourRequest request);
     
       /**
	 *身份对比接口
	 * 
	 * @param request
	 *            身份对比接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceIdCardLiveDetectFour(FaceIdCardLiveDetectFourRequest request);
     
      /**
	 *身份检测接口
	 * 
	 * @param request
	 *            身份检测接口
	 * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
	 *         其他为失败, message为success或者失败原因
	 */    
     String faceLiveDetectFour(FaceLiveDetectFourRequest request);

    /**
     * 人脸静态活体检测 https://cloud.tencent.com/document/product/641/12558
     * @param useNewDomain 是否使用新域名，<br>
     * true: http://recognition.image.myqcloud.com/face/multidentify <br>
     * false: http://service.image.myqcloud.com/face/multidentify <br>
     * 如果开发者使用的是原域名（service.image.myqcloud.com）且已产生调用，则无需更换域名。
     * @return JSON格式的字符串, 格式为{"code":$code, "message":"$mess"}, code为0表示成功,
     * 其他为失败, message为success或者失败原因
     */
    String faceLiveDetectPicture(FaceLiveDetectPictureRequest request, boolean useNewDomain);
     
    /**
     * 关闭Image客户端连接池，释放涉及的资源，释放后，不能再使用Image的接口，必须重新生成一个新对象
     */
    void shutdown();

}
