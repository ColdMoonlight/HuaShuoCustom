package com.atguigu.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import com.atguigu.vo.StringTwoVO;

public class SMSUtilshtml {
	
	public static void main(String[] args) throws Exception {

        String host = "https://api.globalsent.com/send?";
        
//        Map parmas = new HashMap();
//
//        parmas.put("access_key","c81e728d9d4c2f63");
//        parmas.put("mobile","8613800138000");
//        parmas.put("content","您的验证码是1234，请查收！");
//
//        ArrayList request = new ArrayList();
//        for(String st:(String)parmas.keySet())
//        {
//            request.add(URLEncoder.encode(st,"UTF-8") + "=" + URLEncoder.encode(parmas.get(st),"UTF-8"));
//        }
        List<StringTwoVO> aaaList =new ArrayList<StringTwoVO>();
        
        StringTwoVO StringTwoVOa = new StringTwoVO("access_key","8a6da0fbaf92680e");
        StringTwoVO StringTwoVOb = new StringTwoVO("mobile","8617600209637");
//        StringTwoVO StringTwoVOc = new StringTwoVO("content","https://www.megalook.com");
        
        StringTwoVO StringTwoVOc = new StringTwoVO("content","Megalook Mall Hair Happy Valentine's Day, Best for her!.https://www.megalook.com");
        
        
        
        aaaList.add(StringTwoVOa);
        aaaList.add(StringTwoVOb);
        aaaList.add(StringTwoVOc);
        
//        List<String> request = new ArrayList<String>();
        
        ArrayList request = new ArrayList();
        
        
        String lastStr = "";
        for(StringTwoVO StringTwoVOone:aaaList){
        	
        	String keyone = URLEncoder.encode(StringTwoVOone.getKeyString(),"UTF-8") + "=" + URLEncoder.encode(StringTwoVOone.getKeyValue(),"UTF-8");
        	
        	request.add(URLEncoder.encode(StringTwoVOone.getKeyString(),"UTF-8") + "=" + URLEncoder.encode(StringTwoVOone.getKeyValue(),"UTF-8"));
        	
        	lastStr= lastStr +keyone+"&";
        }

        System.out.println("lastStr:"+lastStr);
        
        String finalStr = lastStr.substring(0,lastStr.length()-1);
        
        System.out.println("finalStr:"+finalStr);
        
        String sendStr = host+finalStr;
        
        System.out.println("sendStr:"+sendStr);
        
        URL url = new URL(sendStr);

        HttpURLConnection con = (HttpURLConnection)url.openConnection();

        con.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

        String lines;
        String result = "";

        while((lines = reader.readLine()) != null)
        {
            result = result + lines;
        }

        reader.close();

        System.out.println(result);
    }
	
	
	/**
	 * 随机生成id
	 * @return
	 */
	public static String sendSMS(String chekoutUrl,String telephone)  throws Exception{

		
		String host = "https://api.globalsent.com/send?";
        
//      Map parmas = new HashMap();
//
//      parmas.put("access_key","c81e728d9d4c2f63");
//      parmas.put("mobile","8613800138000");
//      parmas.put("content","您的验证码是1234，请查收！");
//
//      ArrayList request = new ArrayList();
//
//      for(String st:(String)parmas.keySet())
//      {
//          request.add(URLEncoder.encode(st,"UTF-8") + "=" + URLEncoder.encode(parmas.get(st),"UTF-8"));
//      }
      List<StringTwoVO> aaaList =new ArrayList<StringTwoVO>();
      
      StringTwoVO StringTwoVOa = new StringTwoVO("access_key","8a6da0fbaf92680e");
      StringTwoVO StringTwoVOb = new StringTwoVO("mobile",telephone);
      StringTwoVO StringTwoVOc = new StringTwoVO("content",chekoutUrl);
      
      aaaList.add(StringTwoVOa);
      aaaList.add(StringTwoVOb);
      aaaList.add(StringTwoVOc);
      
      ArrayList request = new ArrayList();
      
      String lastStr = "";
      for(StringTwoVO StringTwoVOone:aaaList){
      	
      	String keyone = URLEncoder.encode(StringTwoVOone.getKeyString(),"UTF-8") + "=" + URLEncoder.encode(StringTwoVOone.getKeyValue(),"UTF-8");
      	
      	request.add(URLEncoder.encode(StringTwoVOone.getKeyString(),"UTF-8") + "=" + URLEncoder.encode(StringTwoVOone.getKeyValue(),"UTF-8"));
      	
      	lastStr= lastStr +keyone+"&";
      }

      System.out.println("lastStr:"+lastStr);
      
      String finalStr = lastStr.substring(0,lastStr.length()-1);
      
      System.out.println("finalStr:"+finalStr);
      
      String sendStr = host+finalStr;
      
      System.out.println("sendStr:"+sendStr);
      
      URL url = new URL(sendStr);

      HttpURLConnection con = (HttpURLConnection)url.openConnection();

      con.connect();

      BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

      String lines;
      String result = "";

      while((lines = reader.readLine()) != null)
      {
          result = result + lines;
      }

      reader.close();

      System.out.println(result);
		
	return result;
	}
	
}
