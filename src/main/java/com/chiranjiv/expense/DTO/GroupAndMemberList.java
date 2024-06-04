package com.chiranjiv.expense.DTO;

import java.util.List;

import com.chiranjiv.expense.entity.Group;
import com.chiranjiv.expense.entity.GroupMembers;

import lombok.Data;

@Data
public class GroupAndMemberList {
	
	private Group group;
	private List<GroupMembers> memberList;
	
}
