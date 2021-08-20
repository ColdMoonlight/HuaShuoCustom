package com.atguigu.controller.back;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.atguigu.utils.DateUtil;
import com.atguigu.utils.ReviewRandomUtil;

@Controller
@RequestMapping("/ExcleReviewTimeDownload")
public class ExcleReviewTimeDownloadController {
	
	/**
	 * 下载timeRandom数据
	 * */
	@RequestMapping(value="/exportRandomTime",method=RequestMethod.GET)
	public void exportUserEmailBydate(HttpServletResponse rep,HttpServletRequest res,@RequestParam(value = "year") Integer year,
			@RequestParam(value = "month") Integer month,@RequestParam(value = "number") Integer number,HttpSession session){
		
		rep.setContentType("application/octet-stream");
		
		String nowTime = DateUtil.strTime14();
		rep.setHeader("Content-Disposition", "attachment;filename="+nowTime+"timeRandom.xls");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet0");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		
		cell.setCellValue("num_id");
	    cell = row.createCell(1);
		cell.setCellValue("trueTime");
	    cell = row.createCell(2);
	    
	    String yearStr = year+"";
	    String monthStr = month+"" ;
	    
	    for (int i = 0; i < number; i++) {
	        row = sheet.createRow(i+1);
	        String timeHHMMSS= ReviewRandomUtil.getHourMinuteSecond();
	        String dayStr = ReviewRandomUtil.getRandom30();
	        String allTimeStr = ReviewRandomUtil.getAllTimeStr(yearStr,monthStr,dayStr,timeHHMMSS);
	        row.createCell(0).setCellValue(i);//num_id
	        row.createCell(1).setCellValue(allTimeStr+"");//放入时间参数
	    }
		try {
			OutputStream out =rep.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载timeRandomDIY数据-年-月-日-开始-结束-条数
	 * */
	@RequestMapping(value="/exportRandomDIYTime",method=RequestMethod.GET)
	public void exportRandomDIYTime(HttpServletResponse rep,HttpServletRequest res,@RequestParam(value = "year") Integer year,
			@RequestParam(value = "month") Integer month,@RequestParam(value = "number") Integer number,
			@RequestParam(value = "startday") Integer startday,@RequestParam(value = "endday") Integer endday,HttpSession session){
		
		rep.setContentType("application/octet-stream");
		
		String nowTime = DateUtil.strTime14();
		rep.setHeader("Content-Disposition", "attachment;filename="+nowTime+"timeRandom.xls");
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet0");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		
		cell.setCellValue("num_id");
	    cell = row.createCell(1);
		cell.setCellValue("trueTime");
	    cell = row.createCell(2);
	    
	    String yearStr = year+"";
	    String monthStr = month+"" ;
	    
	    for (int i = 0; i < number; i++) {
	        row = sheet.createRow(i+1);
	        String timeHHMMSS= ReviewRandomUtil.getHourMinuteSecond();
	        String dayStr = ReviewRandomUtil.getRandomDiy(startday, endday);
	        String allTimeStr = ReviewRandomUtil.getAllTimeStr(yearStr,monthStr,dayStr,timeHHMMSS);
	        row.createCell(0).setCellValue(i);//num_id
	        row.createCell(1).setCellValue(allTimeStr+"");//放入时间参数
	    }
		try {
			OutputStream out =rep.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
