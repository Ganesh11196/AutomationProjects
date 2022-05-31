package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	static String data=null;
	public static void CaptureScreenShot(WebDriver driver, int id) throws IOException
	{
		Date date=new Date();
	    DateFormat date_Format=new SimpleDateFormat("dd-MM-yyyy hh mm ss");
	    String date_string =date_Format.format(date);
	    TakesScreenshot t=(TakesScreenshot)driver;
		File source=t.getScreenshotAs(OutputType.FILE);
		File dest=new File("test-output\\ScreenShots\\test-"+id+" "+date_string+".jpg");
		FileHandler.copy(source, dest);
	}
	
	public static String getDataFromExcel(int x, int y) throws EncryptedDocumentException, IOException
	{
		 String path="src\\main\\resources\\testData\\testing.xlsx";
		 FileInputStream File=new FileInputStream(path);
		 Workbook book=WorkbookFactory.create(File);
		 try {
		     data= book.getSheet("Excel").getRow(x).getCell(y).getStringCellValue();
		 }
		 catch(IllegalStateException j)
		 {
			 double d= book.getSheet("Excel").getRow(x).getCell(y).getNumericCellValue();
			 String s=Double.toString(d);
			 data=s;
		 }
		return data;
	}

}
