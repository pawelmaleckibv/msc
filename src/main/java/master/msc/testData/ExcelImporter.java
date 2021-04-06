package master.msc.testData;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ExcelImporter<T> {
    private String fileName;

    public ExcelImporter(String fileName) {
        this.fileName = fileName;
    }

    protected abstract T handleLine(List<String> cellsInRow);

    protected InputStream readFileByName(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<T> read(String sheetName) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(readFileByName(fileName));
        Sheet sheet = workbook.getSheet(sheetName);
        List<T> data = read(sheet);
        workbook.close();
        return data;
    }

     public List<T> read(int sheetIndex) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(readFileByName(fileName));
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        List<T> data = read(sheet);
        workbook.close();
        return data;
    }

    public List<T> read() throws IOException, InvalidFormatException {
        InputStream inp = readFileByName(fileName);
        Workbook workbook = WorkbookFactory.create(inp);
        Sheet sheet = workbook.getSheetAt(0);
        List<T> data = read(sheet);
        workbook.close();
        return data;
    }


    public List<T> read(Sheet sheet) throws IOException, InvalidFormatException {
        List<T> objList = new ArrayList<>();
        List<String> cellsInRow;



        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {

            cellsInRow = new ArrayList<>();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellTypeEnum()) {
                    case BOOLEAN:
                        cellsInRow.add(String.valueOf(cell.getBooleanCellValue()));
                        break;
                    case STRING:
                        cellsInRow.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            cellsInRow.add(cell.getDateCellValue().toString());
                        } else {
                            cellsInRow.add(String.valueOf(cell.getNumericCellValue()));
                        }
                        break;
                    case FORMULA:
                        break;
                    case BLANK:
                        cellsInRow.add("BLANK");
                        break;
                    default:
                        cellsInRow.add("DEFAULT");
                }
            }
            T obj = handleLine(cellsInRow);
            if(obj != null) {
                objList.add(obj);
            }
        }
        return objList;
    }
}