package com.chiranjiv.expense.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiranjiv.expense.entity.ConstData;
import com.chiranjiv.expense.utils.CommonUtils;

@RestController
@RequestMapping("common")
public class CommonController {
	
	@Autowired private CommonUtils commonUtils;
	
	@GetMapping(value="/getConstantList")
	@ResponseBody
	public ResponseEntity<List<ConstData>> getConstantList(@RequestParam List<String> groupName) {
		List<ConstData> resultList = new ArrayList<>();
		try {
			resultList = commonUtils.getConstantListfromGroupNameList(groupName,resultList);
			return new ResponseEntity<List<ConstData>>(resultList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<ConstData>>(resultList,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
