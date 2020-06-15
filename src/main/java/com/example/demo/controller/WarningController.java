package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Warning;
import com.example.demo.entity.WorkSheet;
import com.example.demo.service.WarningService;
import com.example.demo.service.WorkSheetService;

@Controller
public class WarningController {
	@Autowired
	private WarningService warningService;
	@Autowired
	private WorkSheetService workSheetService;
	
	@RequestMapping("list")
	public String warningList() {
		return "warninginfo";
	}

	
	
	@ResponseBody
	@RequestMapping("page")
	public String list(HttpServletRequest req,HttpServletResponse res) {
		int start = Integer.parseInt(req.getParameter("start"));
		int limit = Integer.parseInt(req.getParameter("limit"));
		Page<Warning> w = warningService.listWarning(start/limit, limit);
		List<Warning> warning = w.getContent();
		int total = warningService.getWarning().size();
		Map<String, Object> ws = new HashMap<String, Object>();
		ws.put("warn", warning);
		ws.put("total", total);
//		PageEntity pe = new PageEntity();
//		pe.setPage(w);
//		pe.setTotalPage(total);
		JSONObject obj = new JSONObject();
		obj.put("warning", ws);
		return obj.toJSONString();
	}
	
	
	@ResponseBody
	@RequestMapping("effect")
	public void effect(@RequestParam Integer id) {
		warningService.changeEffect(id);
	}
	
	@ResponseBody
	@RequestMapping("del")//删除
	public void deleteWarning(@RequestParam Integer id) {
		warningService.delWarning(id);
	}
	
	@ResponseBody
	@RequestMapping("deal")//处理
	public void dealWarning(@RequestParam Integer id) {
		warningService.dealEffect(id);;
	}
	
	
	@ResponseBody
	@RequestMapping("addsheet")
	public void addWorkSheet(String sheetname,String sname,String equipmentname,
			String descr,String deadline) {
		
		try {
			SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yy");
			Date date;
			date = ft.parse(deadline);
			WorkSheet ws = new WorkSheet();
			ws.setSheetname(sheetname);
			ws.setSname(sname);
			ws.setEquipmentname(equipmentname);
			ws.setDescr(descr);
			ws.setDeadline(date);
			workSheetService.addsheet(ws);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@ResponseBody
	@RequestMapping("info")
	public String getInfo(@RequestParam Integer id) {
		Warning w = warningService.getWarningById(id);
		JSONObject obj = new JSONObject();
		obj.put("warninfo", w);
		return obj.toJSONString();
	}
	
	
}
