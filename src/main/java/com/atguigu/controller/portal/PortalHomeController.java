package com.atguigu.controller.portal;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * PortalHome
 * @author
 */
@Controller
@RequestMapping("/PortalHome")
public class PortalHomeController {
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping("/videoPage")
	public String videoPage(HttpSession session) throws Exception{

		return "portal/subject/video";

	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping("/InsShowPage")
	public String InsShowPage(HttpSession session) throws Exception{
		
		return "portal/subject/InsShowPage";

	}
	
	/**
	 * 	onuse	20200103	检查
	 * */
	@RequestMapping("/FridayPage")
	public String FridayPage(HttpSession session) throws Exception{

		return "portal/subject/friday";

	}
	
	/**
	 * 	zsh	20201102
	 * */
	@RequestMapping("/WholesalePage")
	public String WholesalePage(HttpSession session) throws Exception{
		
		return "portal/subject/wholesalePage";

	}
	
	/**
	 * 	zsh	20201102
	 * */
	@RequestMapping("/ChristmasPage")
	public String ChristmasPage(HttpSession session) throws Exception{
		
		return "portal/subject/christmasPage";

	}
	
	/**
	 * 	zsh	20210723
	 * */
	@RequestMapping("/FAQuestionPage")
	public String FAQuestionPage(HttpSession session) throws Exception{
		
		return "portal/subject/fAQuestionPage";

	}

}
