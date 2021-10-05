import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import processor.AbstractProcessor;
import processor.DefaultProcessor;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    AbstractProcessor processor = new DefaultProcessor();

    @Test
    public void testGetName() {

        long number = 2222222222L;
        String NUMBER = "два миллиарда двести двадцать два миллиона двести двадцать две тысячи двести двадцать два";

        System.out.println("Test Program: test 1 - 2222222222");
        System.out.println(number + " = " + processor.getName(number));
        assertEquals("Error (test1): ", NUMBER, processor.getName(number));
    }

    @Test
    public void testGetNameZero() {

        int zero = 0;
        String ZERO = "ноль";

        System.out.println("Test Program: test 2 - 0");
        System.out.println(zero + " = " + processor.getName(zero));
        assertEquals("Error with 0 number (test2): ", ZERO, processor.getName(zero));
    }

    @Test
    public void testGetNameUnit() {

        String[] TOKENS = new String[]{"один", "два", "три", "четыре",
                "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать",
                "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

        System.out.println("Test Program: test 3 - Numbers 1 - 19");

        for (int i = 0; i < 19; i++) {
            System.out.println((i + 1) + " = " + processor.getName(i + 1));
            assertEquals("Error in numbers from 1 to 19 (test3): ", TOKENS[i], processor.getName(i + 1));
        }
    }

    @Test
    public void testGetNameTens() {

        int[] tokens = new int[]{21, 32, 44, 55, 66, 77, 88};
        String[] TOKENS = new String[]{"двадцать один", "тридцать два", "сорок четыре", "пятьдесят пять",
                "шестьдесят шесть", "семьдесят семь", "восемьдесят восемь"};

        System.out.println("Test Program: test 4 - Numbers larger than 20");

        for (int i = 0; i < 7; i++) {
            System.out.println(tokens[i] + " = " + processor.getName(tokens[i]));
            assertEquals("Error (test4): ", TOKENS[i], processor.getName(tokens[i]));
        }
    }

    @Test
    public void testGetNameXlsxFileData() throws IOException {

        System.out.println("Test Program: test 5 - Different numbers from .xlsx file");
        FileInputStream in = new FileInputStream("testXls/test.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(in);

        long inNumber = 0;
        String inString = null;

        XSSFSheet sheet = wb.getSheetAt(0);
        for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {

                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case NUMERIC:
                        System.out.print((inNumber = (long) cell.getNumericCellValue()) + " = ");
                        break;

                    case STRING:
                        System.out.print((inString = cell.getStringCellValue()));
                        break;
                }
            }
            assertEquals("Error in number (test5): " + inNumber, inString, processor.getName(inNumber));
            System.out.println();
        }

    }

}