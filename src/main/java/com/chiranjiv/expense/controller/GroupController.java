package com.chiranjiv.expense.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiranjiv.expense.entity.Group; // Ensure this is the correct import
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.service.GroupService;
import com.chiranjiv.expense.utils.UserHelperUtils;

@RestController
@RequestMapping("group")
public class GroupController {
    
    @Autowired private GroupService groupService;

    @PostMapping("/edit-group")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editGroupOverViewData(
            @RequestBody Group group, 
            @RequestParam String identifier,
            @RequestParam(required = false) String mobile, 
            @RequestParam(required = false) String isDeleted) {

        Map<String, Object> responseMap = new HashMap<>();
        try {
            Users user = UserHelperUtils.getLoggedInUser();
            if (user != null) {
                groupService.groupDataEdit(user, group, mobile,identifier,isDeleted, responseMap);
                return new ResponseEntity<>(responseMap, HttpStatus.OK);
            } else {
                responseMap.put("status", false);
                responseMap.put("message", "User is not logged-in");
                return new ResponseEntity<>(responseMap, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            responseMap.put("status", false);
            responseMap.put("message", "Exception while adding monthly expense");
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
//    @PostMapping("/add/group-expense")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> addGroupExpense(
//    		@RequestParam(required = false) Integer groupId,
//    		@RequestParam(required = false) String userId,
//    		@RequestParam(required = false) String isDeleted,
//    		@RequestParam(required = false) String isDeleted ) {
//
//        Map<String, Object> responseMap = new HashMap<>();
//        try {
//            Users user = UserHelperUtils.getLoggedInUser();
//            if (user != null) {
//                groupService.groupDataEdit(user, group, mobile,identifier,isDeleted, responseMap);
//                return new ResponseEntity<>(responseMap, HttpStatus.OK);
//            } else {
//                responseMap.put("status", false);
//                responseMap.put("message", "User is not logged-in");
//                return new ResponseEntity<>(responseMap, HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            responseMap.put("status", false);
//            responseMap.put("message", "Exception while adding monthly expense");
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
}
