package com.learn.jasper.util;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * @author g-bug
 * @date 2020/7/27 上午9:37
 */
public class PoiTest {

    public static void main(String[] args) {
        /*String path = "/home/g-bug/11.xls";
        File file = new File(path);
        HSSFWorkbook workbook;
        FormulaEvaluator formulaEvaluator;
        try (InputStream in = new FileInputStream(file)) {
            workbook = new HSSFWorkbook(in);
            formulaEvaluator = new HSSFFormulaEvaluator(workbook);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.println(getCellValue(cell) + "**" + getCellValueFormula(cell, formulaEvaluator));
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("G4B-1表内信用风险加权资产计算表(权重法）");
        Row row = sheet.createRow(0);
        Cell A1 = row.createCell(0);
        Cell B1 = row.createCell(1);
        Cell C1 = row.createCell(2);
        A1.setCellValue("1");
        B1.setCellValue("2");
        C1.setCellFormula("A1+B1");
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
        row.forEach(o -> {
            if (o.getCellType() == Cell.CELL_TYPE_FORMULA) {
                System.out.println(o.getNumericCellValue());
            } else {
                System.out.println(o.getStringCellValue());
            }
        });
    }

    // 原公式
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//非线程安全
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    // 处理公式的输出
    public static String getCellValueFormula(Cell cell, FormulaEvaluator formulaEvaluator) {
        if (cell == null || formulaEvaluator == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
        }
        return getCellValue(cell);
    }

}
