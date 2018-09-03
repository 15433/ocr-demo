package com.zhishinet.image.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.zhishinet.image.dto.ItemCoord;
import com.zhishinet.image.dto.Items;
import com.zhishinet.image.util.ExcelWrite;
import com.zhishinet.image.util.ImageClient;
import com.zhishinet.image.request.GeneralOcrRequest;
import com.zhishinet.image.service.ISpotScoreService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Copyright Shanghai Hand Co. Ltd.
 *
 * @author liguo.wang@hand-china.com
 * @version: 1.0
 */
@Service
public class SpotScoreServiceImpl implements ISpotScoreService {

    private final String URL = "http://test1-1257242748.picsh.myqcloud.com/";

    private final String SPOT_SCORE_EXPORT_HEAD = "正确答案,识别答案,正确率,可信度";

    @Override
    public List<Items> spotScore(List<String> correct, List<String> url) {
        if (correct == null || correct.size() == 0){
            throw new RuntimeException("correct can't be null");
        }
        if (url == null){
            throw new RuntimeException("url can't be null");
        }
        return ocrSpot(correct,url);
    }

    @Override
    public String imgUpload(MultipartFile file) throws IOException {

        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (fileName == null){
            throw new RuntimeException("fileName can't be null");
        }
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKIDjMn2DGqoIagaHolIG3uZ2MUQAj6Hm3f5", "ZyCGAtRBqKfpnZBborpeHix0rWzHk3Ej");
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = "test1-1257242748";

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        //String src = "C:/Users/Garen/Pictures/three.jpg";
        //File localFile = new File(src);
        //File localFile = (File) file;
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        String left = fileName.substring(0,fileName.length() - prefix.length());
        // 生成新文件
        final File newFile = File.createTempFile(left, prefix);
        // 转换类型
        file.transferTo(newFile);
        // 指定要上传到 COS 上对象键
        // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
        //String[] str = fileName.split("/");
        //String key = str[str.length-1];
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, newFile);
        PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
        System.out.println(putObjectResult);
        // 关闭客户端(关闭后台线程)
        cosclient.shutdown();
        //程序结束时，删除临时文件
        deleteFile(newFile);
        return URL+fileName;
    }

    @Override
    public void exportSpotScore(List<String> correct, List<Items> items, HttpServletResponse response) {
        String[] head = StringUtils.split(SPOT_SCORE_EXPORT_HEAD, ",");
        List<String> header = new ArrayList<>();
        for (int i = 0; i < head.length; i++) {
            header.add(head[i].trim());
        }
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        // 开始生成Excel
        ExcelWrite write = createSheet("识别结果", correct, items, header);
        // 设置响应信息
        String fileName = "SpotScore_" + System.currentTimeMillis();
        setResponseInfo(fileName,response,write);
    }

    private ExcelWrite createSheet(String sheetName, List<String> correct, List<Items> items, List<String> head) {

        ExcelWrite write = new ExcelWrite(".xlsx");
        Workbook workbook = new SXSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        if (CollectionUtils.isEmpty(items)) {
            return write;
        }
        Sheet sheet = workbook.createSheet(sheetName);
        // 创建Sheet页的头
        Row headRow = sheet.createRow(0);
        int cellNum = 0;
        Iterator<String> iterator = head.iterator();
        while (iterator.hasNext()) {
            String title = iterator.next();
            headRow.createCell(cellNum).setCellValue(title);
            cellNum++;
        }
        // 填充Sheet页数据
        for (int i = 0; i < items.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Items item = items.get(i);

            // 正确答案
            Cell cell0 = row.createCell(0);
            cell0.setCellType(SXSSFCell.CELL_TYPE_STRING);
            cell0.setCellValue(item.getKey());

            // 识别答案
            Cell cell1 = row.createCell(1);
            cell1.setCellType(SXSSFCell.CELL_TYPE_STRING);
            cell1.setCellValue(item.getItemString());

            // 正确率
            Cell cell2 = row.createCell(2);
            cell2.setCellType(SXSSFCell.CELL_TYPE_STRING);
            cell2.setCellValue(item.getScore());

            // 可信度
            Cell cell3 = row.createCell(3);
            cell3.setCellType(SXSSFCell.CELL_TYPE_STRING);
            cell3.setCellValue(item.getItemConf());

            // 调整列宽
            sheet.autoSizeColumn(0, true);
            sheet.autoSizeColumn(1, true);
            sheet.autoSizeColumn(2, true);
            sheet.autoSizeColumn(3, true);
        }

        write.setWorkbook(workbook);
        return write;
    }

    /**
     * 设置响应信息
     * @param fileName
     * @param response
     * @param write
     */
    private void setResponseInfo(String fileName, HttpServletResponse response, ExcelWrite write) {
        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/force-download");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            write.getWorkbook().write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * ocr识别
     * @param correct
     * @param urlList
     */
    private List<Items> ocrSpot(List<String> correct, List<String> urlList) {
        String appId = "1257242748";
        String secretId = "AKIDjMn2DGqoIagaHolIG3uZ2MUQAj6Hm3f5";
        String secretKey = "ZyCGAtRBqKfpnZBborpeHix0rWzHk3Ej";
        String bucketName = "test1";

        ImageClient imageClient = new ImageClient(appId, secretId, secretKey);

        List<Items> itemsList = new ArrayList<>();
        urlList.forEach(url -> {
            List<Items> itemsListOne = handwritingOcr(imageClient, bucketName,url);
            if (itemsListOne.size() > 0){
                itemsList.addAll(itemsListOne);
            }
        });
        if (itemsList.size() == 0){
            throw new RuntimeException("未识别出内容");
        }
        List<Items> returnItems = new ArrayList<>(10);
        for (int i = 0; i < correct.size(); i++) {
            Map<Double,Items> scores = new HashMap<>(16);
            for (int j = 0; j < itemsList.size(); j++) {
                // 去打分
                scores.put(score(itemsList.get(j).getItemString().toLowerCase().replaceAll(" +",""),correct.get(i).toLowerCase().replaceAll(" +","")),itemsList.get(j));
            }
            double score = Collections.max(scores.keySet());
            Items items = scores.get(score);
            items.setScore(score).setKey(correct.get(i));
            returnItems.add(items);
        }
        return returnItems;
    }

    /**
     * 手写体识别 - 拼接
     * @param imageClient
     * @param bucketName
     * @param url
     * @return
     */
    private List<Items> handwritingOcr(ImageClient imageClient, String bucketName, String url) {
        String ret;
        GeneralOcrRequest request = new GeneralOcrRequest(bucketName, url);
        ret = imageClient.handwritingOcr(request);
        System.out.println("handwritingOcr:" + ret);

        JSONObject retJson = new JSONObject(ret);

        retJson = new JSONObject(retJson.get("data").toString());

        JSONArray jsonArray = com.alibaba.fastjson.JSONObject.parseArray(retJson.get("items").toString());


        List<Items> list = jsonArray.toJavaList(Items.class);

        for (int i = 0; i < list.size(); i++) {
            com.alibaba.fastjson.JSONObject object = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.parse(jsonArray.getJSONObject(i).get("itemcoord").toString());
            ItemCoord coord = JSON.toJavaObject(object,ItemCoord.class);
            list.get(i).setItemCoord(coord);
            //System.out.println(list.get(i).getItemCoord().getHeight());
        }
        for (int i = 0; i < list.size()-1; i++) {
            //double t = list.get(i - 1).getItemCoord().getWidth() * 1.0 / list.get(i - 1).getItemstring().length();
            // |y1-y2|<height1 && |x2-x1| > width1
            if (Math.abs(list.get(i+1).getItemCoord().getY() - list.get(i).getItemCoord().getY()) < list.get(i).getItemCoord().getHeight()
                    && Math.abs(list.get(i+1).getItemCoord().getX() - list.get(i).getItemCoord().getX()) > list.get(i).getItemCoord().getWidth()){
                list.get(i).setItemString(list.get(i).getItemString() + list.get(i+1).getItemString());
                list.remove(i+1);
                i = i-1;
            }
        }
        return list;
    }
    /**
     * @param str1 OCR 识别结果
     * @param str2 正确答案
     */
    private double score(String str1, String str2) {
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        int result2 = levenshteinDistance.apply(str1, str2);
        int maxLength = str1.length() > str2.length() ? str1.length() : str2.length();
        return ((double) maxLength - (double) result2) / (double) maxLength;
    }
}
