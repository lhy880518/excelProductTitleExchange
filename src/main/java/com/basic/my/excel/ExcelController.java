package com.basic.my.excel;

import com.basic.my.basic.util.CacheHandler;
import com.basic.my.excel.service.ExcelService;
import com.basic.my.excel.vo.Fruit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Controller
public class ExcelController {

    static Logger log = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    @Autowired
    CacheHandler cacheHandler;

    @RequestMapping(value = "/downloadExcelFile", method = RequestMethod.POST)
    public String downloadExcelFile(Model model) {
        String[] names = {"자몽", "애플망고", "멜론", "오렌지"};
        long[] prices = {5000, 10000, 7000, 6000};
        int[] quantities = {50, 50, 40, 40};
        List<Fruit> list = excelService.makeFruitList(names, prices, quantities);

        SXSSFWorkbook workbook = excelService.excelFileDownloadProcess(list);

        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "과일표");

        return "excelDownloadView";
    }

    @RequestMapping(value = "uploadExcelFile", method = RequestMethod.POST)
    public String uploadExcelFile(MultipartHttpServletRequest request, Model model) {
        MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
        }
        List<Fruit> list = excelService.uploadExcelFile(file);

        model.addAttribute("list", list);
        return "jsonView";
    }

    @RequestMapping(value = "doExcelChangeFile", method = RequestMethod.POST)
    public String doExcelChangeFile(MultipartHttpServletRequest request, Model model){
        // 엑셀 파일 가져오기
        MultipartFile file = null;
        Iterator<String> iterator = request.getFileNames();
        if(iterator.hasNext()) {
            file = request.getFile(iterator.next());
        }

        HSSFWorkbook workbook = excelService.excelChangeFileProcess(file);

        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "변경된파일");

        return "ExcelDownloadViewHSSF";
    }
}
