package com.zhishinet.image.controller;

import com.zhishinet.image.dto.Items;
import com.zhishinet.image.service.ISpotScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Copyright Shanghai Hand Co. Ltd.
 *
 * @author liguo.wang@hand-china.com
 * @version: 1.0
 */
@RestController
@RequestMapping("api/ocr")
public class SpotScoreController {

    @Autowired
    private ISpotScoreService spotScoreService;

    @RequestMapping(value = "/spotScore", method = RequestMethod.GET)
    public ResponseEntity spotScore(@RequestParam("correct") List<String> correct,@RequestParam("url") List<String> url) {
        List<Items> items = spotScoreService.spotScore(correct,url);
        return ResponseEntity.ok(items);
    }

    @RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
    public ResponseEntity imgUpload(@RequestParam("photo") MultipartFile file) throws Exception{
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        }
        String url = spotScoreService.imgUpload(file);
        return ResponseEntity.ok(url);
    }

    @RequestMapping(value = "/exportSpotScore", method = RequestMethod.GET)
    public void exportSpotScore(@RequestParam("correct") List<String> correct,
                                @RequestParam("url") List<String> url,
                                HttpServletResponse response) {
        List<Items> items = spotScoreService.spotScore(correct,url);
        spotScoreService.exportSpotScore(correct, items,response);
    }
}
