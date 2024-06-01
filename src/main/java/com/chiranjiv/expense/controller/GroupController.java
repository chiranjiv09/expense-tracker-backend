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
    public ResponseEntity<Map<String, Object>> addMonthlyExpenses(
            @RequestBody Group group, 
            @RequestParam(required = false) Integer userId, 
            @RequestParam String identifier) {

        Map<String, Object> responseMap = new HashMap<>();
        try {
            Users user = UserHelperUtils.getLoggedInUser();
            if (user != null) {
                groupService.groupDataEdit(user, group, userId,identifier, responseMap);
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
}
