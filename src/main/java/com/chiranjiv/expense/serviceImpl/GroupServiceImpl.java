package com.chiranjiv.expense.serviceImpl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiranjiv.expense.entity.Group;
import com.chiranjiv.expense.entity.GroupMembers;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.GroupMembersRepo;
import com.chiranjiv.expense.repo.GroupRepo;
import com.chiranjiv.expense.repo.UserRepo;
import com.chiranjiv.expense.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired private GroupRepo groupRepo;
	
	@Autowired private GroupMembersRepo  groupMembersRepo;
	
	@Autowired private UserRepo userRepo;

	@Override
	public void groupDataEdit(Users user, Group group, Integer userId, String identifier, Map<String, Object> responseMap) {
		try {
			if(identifier.equalsIgnoreCase("G")) {
				if(group.getGroupId() != null) {
					Group existingGroup = groupRepo.findByGroupId(group.getGroupId());
					existingGroup.setGroupName(group.getGroupName());
					groupRepo.save(existingGroup);
					responseMap.put("group", existingGroup);
				}else {
					groupRepo.save(group);
					responseMap.put("group", group);
				}
			}else if(identifier.equalsIgnoreCase("U")) {
				GroupMembers member = groupMembersRepo.findByUserIdAndGroupId(userId,group.getGroupId());
				if(member != null) {
					member.setIsActive("Y");
				}else {
					member = new GroupMembers();
					member.setGroupId(group.getGroupId());
					member.setUserId(userId);
					Users existingUser = userRepo.findByUserIdAndIsActive(userId,"Y");
					//existingUser.set
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
