package DataProvider;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvider_Excel {

    @DataProvider(name="Saucedemo")
    public Object[][] Saucedemo()
    {
        Object[][] arrayObject = getExcelData("./testdata.xls", "ui_test_data");
        return arrayObject;
    }

    @DataProvider(name="ExchangeRateApi")
    public Object[][] ExchangeRateApi()
    {
        Object[][] arrayObject = getExcelData("./testdata.xls", "api_test_data");
        return arrayObject;
    }


    //Method to extract data from excel sheets//
    public String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sh = wb.getSheet(sheetName);

            int totalNoOfCols = sh.getColumns();
            int totalNoOfRows = sh.getRows();

            arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

            for (int i= 1 ; i < totalNoOfRows; i++) {

                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }



}