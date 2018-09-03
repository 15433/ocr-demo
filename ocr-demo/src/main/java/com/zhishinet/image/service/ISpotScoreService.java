package com.zhishinet.image.service;

import com.zhishinet.image.dto.Items;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Copyright Shanghai Hand Co. Ltd.
 *
 * @author liguo.wang@hand-china.com
 * @version: 1.0
 */
public interface ISpotScoreService {
    /**
     * 识别打分
     * @param correct
     * @param url
     */
    List<Items> spotScore(List<String> correct, List<String> url);

    /**
     * 小文件上传
     * @param file
     */
    String imgUpload(MultipartFile file) throws IOException;

    /**
     * 识别结果导出
     * @param correct
     * @param items
     * @param response
     */
    void exportSpotScore(List<String> correct, List<Items> items, HttpServletResponse response);
}
