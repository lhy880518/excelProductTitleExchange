package com.basic.my.excel.service;

import com.basic.my.excel.vo.Fruit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {
    List<Fruit> makeFruitList(String[] names, long[] prices, int[] quantities);

    SXSSFWorkbook excelFileDownloadProcess(List<Fruit> list);

    List<Fruit> uploadExcelFile(MultipartFile file);

    HSSFWorkbook excelChangeFileProcess(MultipartFile file);
}
