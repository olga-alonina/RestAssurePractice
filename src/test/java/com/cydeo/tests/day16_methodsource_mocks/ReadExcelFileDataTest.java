package com.cydeo.tests.day16_methodsource_mocks;

import com.cydeo.utils.ExcelUtil;
import org.junit.jupiter.api.Test;

public class ReadExcelFileDataTest {

    @Test
    public void readBookItUsersTest() {
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath,"QA3");
        System.out.println("columns = " + excelUtil.getColumnsNames());
    }

}