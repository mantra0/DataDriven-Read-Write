import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class dataProvider {

    public static XSSFSheet excelSheet;
    private static XSSFWorkbook excelWBook;
    private static XSSFCell cell;
    private static XSSFRow row;
    public static int index=0;
    public static String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\path\\Book1.xlsx";

    public static String getCellData(int rowNum, int colNum){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            excelWBook = new XSSFWorkbook(fis);
            excelSheet = excelWBook.getSheetAt(index);
            row = excelSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            String cellData = cell.getStringCellValue();
            return cellData;
        }
        catch (Exception e){
            return "";
        }
    }

    public static void setCellData(String result, int rowNum, int colNum){
        try{
            row = excelSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if(cell == null){
                cell = row.createCell(colNum);
                cell.setCellValue(result);
            }
            else
                cell.setCellValue(result);

            FileOutputStream fos = new FileOutputStream(filePath);
            excelWBook.write(fos);
            fos.flush();
        }
        catch (Exception e){

        }
    }

    public static int getRowCount(){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            excelWBook = new XSSFWorkbook(fis);
            excelSheet = excelWBook.getSheetAt(index);
            int rowCount = excelSheet.getPhysicalNumberOfRows();
            return rowCount;
        }
        catch (Exception e){
            return 0;
        }
    }


}
