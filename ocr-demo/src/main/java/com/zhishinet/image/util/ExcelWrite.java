/**
 * ExcelWrite 2017/9/7 11:18
 * <p>
 * Copyright (C) HAND Enterprise Solutions Company Ltd.
 * All Rights Reserved
 */
package com.zhishinet.image.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author gang.wang
 * @Title: ExcelWrite
 * @Description: (描述此类的功能)
 * @date 2017/9/7 11:18
 */
public class ExcelWrite {

	private static final String EXCEL_2007 = ".xlsx";
	private static final String EXCEL_2003 = ".xls";
	private String type = ".xlsx"; //生成excel的类型默认2007
	private Workbook workbook;

	// add by weisen.yang
	private static final int DEFAULT_WIDTH = 10*256;


	public ExcelWrite() {
		if (EXCEL_2007.equals(this.type)) {
			workbook = new XSSFWorkbook();
		} else if (EXCEL_2003.equals(this.type)) {
			workbook = new HSSFWorkbook();
		} else {
			throw new RuntimeException("不支持的文件类型");
		}
	}

	public ExcelWrite(String type) {
		this.type = type;
		if (EXCEL_2007.equals(this.type)) {
			workbook = new XSSFWorkbook();
		} else if (EXCEL_2003.equals(this.type)) {
			workbook = new HSSFWorkbook();
		} else {
			throw new RuntimeException("不支持的文件类型");
		}
	}

	public void createSheet(String sheetName, List<Map<String, Object>> data) {

		if (null == workbook || StringUtils.isBlank(sheetName) || CollectionUtils.isEmpty(data)) {
			return;
		}
		if (null != workbook.getSheet(sheetName)) {
			throw new RuntimeException("Sheet " + sheetName + "已存在");
		}
		Sheet sheet = workbook.createSheet(sheetName);
		// 创建Sheet页的头
		Row headRow = sheet.createRow(0);
		Set<String> headers = data.get(0).keySet();
		int cellNum = 0;
		Iterator<String> iterator = headers.iterator();
		while(iterator.hasNext()){
			String title = iterator.next();
			headRow.createCell(cellNum).setCellValue(title);
			cellNum++;
		}
		// 填充Sheet页数据
		for (int i=0 ;i<data.size();i++){
			Row row = sheet.createRow(i+1);
			Collection<String>  values =  data.get(i).keySet();
			Iterator<String> it = values.iterator();
			cellNum = 0;
			while (it.hasNext()){
				Object value = data.get(i).get(it.next());
				if(null != value) {
					row.createCell(cellNum).setCellValue(value.toString());
				}
				cellNum++;
			}
		}
		// 调整列宽
		for (int i=0;i<cellNum;i++) {
			sheet.autoSizeColumn(i);
		}
	}

	// add by weisen.yang
	public void createSheetOnlyTitle(String sheetName, List<String> data) {

		if (null == workbook || StringUtils.isBlank(sheetName) || CollectionUtils.isEmpty(data)) {
			return;
		}
		if (null != workbook.getSheet(sheetName)) {
			throw new RuntimeException("Sheet " + sheetName + "已存在");
		}
		Sheet sheet = workbook.createSheet(sheetName);
		// 创建Sheet页的头
		Row headRow = sheet.createRow(0);
		int cellNum = 0;
		Iterator<String> iterator = data.iterator();
		while(iterator.hasNext()){
			String title = iterator.next();
			headRow.createCell(cellNum).setCellValue(title);
			cellNum++;
		}

		// 调整列宽
		for (int i=0;i<cellNum;i++) {
			sheet.autoSizeColumn(i);
		}

		for (int i=0;i<cellNum;i++) {
			int columnWidth = sheet.getColumnWidth(i);
			if (columnWidth < DEFAULT_WIDTH) {
				sheet.setColumnWidth(i, DEFAULT_WIDTH);
			}
		}
	}

	public String getType() {
		return type;
	}

	public ExcelWrite setType(String type) {
		this.type = type;
		return this;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public ExcelWrite setWorkbook(Workbook workbook) {
		this.workbook = workbook;
		return this;
	}
}
