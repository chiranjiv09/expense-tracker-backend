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

	
	// need to add loggers as well
	@Override
	public void groupDataEdit(Users user, Group group, String mobile, String identifier, String isDeleted, Map<String, Object> responseMap) {
		try {
			if(identifier.equalsIgnoreCase("G")) {
					if(group.getGroupId() != null) {
						if(user.getUserId().equals(group.getSuperAdmin())) {
							Group existingGroup = groupRepo.findByGroupId(group.getGroupId());
							if(isDeleted.equalsIgnoreCase("Y")) {
								existingGroup.setIsActive("N");
								groupRepo.save(existingGroup);
								responseMap.put("group", existingGroup);
								responseMap.put("status", true);
								responseMap.put("message", "group is been successfully updated");
							}else {
								existingGroup.setGroupName(group.getGroupName());
								groupRepo.save(existingGroup);
								responseMap.put("group", existingGroup);
								responseMap.put("status", true);
								responseMap.put("message", "group is been successfully updated");
							}
						}else {
							responseMap.put("status", false);
							responseMap.put("message", "user id is not matching with super admin");
						}
					}else {
						group.setSuperAdmin(user.getUserId());
						group.setIsActive("Y");
						groupRepo.save(group);
						responseMap.put("group", group);
						responseMap.put("status", true);
						responseMap.put("message", "group is been successfully created");
					}
			}else if(identifier.equalsIgnoreCase("U")) {
				if(user.getUserId().equals(group.getSuperAdmin())) {
					Users existingUser = userRepo.findByUserNameAndIsActive(mobile,"Y");
					if(existingUser != null) {
						if(isDeleted.equalsIgnoreCase("Y")) {
							// here we need to check if all expenses clear from the group than only we can remove
							groupMembersRepo.updateIsActiveOfGroupMember(existingUser.getUserId(), group.getGroupId(), isDeleted);
							responseMap.put("status", true);
							responseMap.put("message", "user is successfully deleted from the group ");
						}else {
							GroupMembers member = groupMembersRepo.findByUserIdAndGroupIdAndIsActive(existingUser.getUserId(),group.getGroupId(),"Y");
							if(member != null) {
								responseMap.put("status", false);
								responseMap.put("message", "user is already added with mobile :-"+ mobile);
							}else {
								member = new GroupMembers();
								member.setUserId(existingUser.getUserId());
								member.setUserName(existingUser.getUsername());
								member.setGroupId(group.getGroupId());
								member.setIsActive("Y");
								groupMembersRepo.save(member);
								responseMap.put("status", true);
								responseMap.put("member", member);
								responseMap.put("message", "user is successfully added in the group ");
							}
						}
					}else {
						responseMap.put("status", false);
						responseMap.put("message", "user is not found with mobile :-"+ mobile);
					}
				}else {
					responseMap.put("status", false);
					responseMap.put("message", "user id is not matching with super admin");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put("status", false);
			responseMap.put("message", "exception occur while while editing the group data");
		}
	}
}
