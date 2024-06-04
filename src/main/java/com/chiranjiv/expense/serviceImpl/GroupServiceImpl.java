package com.chiranjiv.expense.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiranjiv.expense.DTO.ExpenseSplitDto;
import com.chiranjiv.expense.DTO.GroupAndMemberList;
import com.chiranjiv.expense.entity.Expense;
import com.chiranjiv.expense.entity.Group;
import com.chiranjiv.expense.entity.GroupMembers;
import com.chiranjiv.expense.entity.Users;
import com.chiranjiv.expense.repo.ExpenseRepo;
import com.chiranjiv.expense.repo.GroupMembersRepo;
import com.chiranjiv.expense.repo.GroupRepo;
import com.chiranjiv.expense.repo.UserRepo;
import com.chiranjiv.expense.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	
	@Autowired private GroupRepo groupRepo;
	
	@Autowired private GroupMembersRepo  groupMembersRepo;
	
	@Autowired private UserRepo userRepo;
	
	@Autowired ExpenseRepo expenseRepo;

	
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
	
	
	@Override
	public void getGroupOverviewData(Users user, Map<String, Object> responseMap) {
		List<Integer> groupIds = groupMembersRepo.findByUserIdAndIsActive(user.getUserId(),"Y");
		List<Group> listOfGroup = groupRepo.findByListOfGroupIds(groupIds,"Y");
		
		List<GroupAndMemberList> resultList =  new ArrayList<>();
		for (Group group : listOfGroup) {
			GroupAndMemberList groupAndMember =  new GroupAndMemberList();
			groupAndMember.setGroup(group);
			List<GroupMembers> membersList = groupMembersRepo.findByGroupIdAndIsActive(group.getGroupId(),"Y");
			groupAndMember.setMemberList(membersList);
			resultList.add(groupAndMember);
		}
		responseMap.put("status", true);
		responseMap.put("resultList", resultList);
		
	}


	@Override
	public void addingGroupExpenses(Users user, Expense expense, String isDeleted, Map<String, Object> responseMap) {
		if(expense.getGroupId() != null) {
			GroupMembers member = groupMembersRepo.findByUserIdAndGroupIdAndIsActive(user.getUserId(),expense.getGroupId(),"Y");
			if(member != null) {
				if(isDeleted != null && isDeleted.equalsIgnoreCase("Y")) {
					expense.setIsActive("N");
				}else {
					expense.setIsActive("Y");
					expense.setUserId(user.getUserId());
				}
				expenseRepo.save(expense);
			}else {
				responseMap.put("status", false);
				responseMap.put("message", "active user is not found in group");
			}
		}
	}


	@Override
	public void getGroupExpenseData(Users user, Integer groupId, Map<String, Object> responseMap) {
		
		if(groupId != null) {
			GroupMembers member = groupMembersRepo.findByUserIdAndGroupIdAndIsActive(user.getUserId(),groupId,"Y");
			if(member != null) {
				List<Expense> groupExpense = expenseRepo.findAllByGroupIdAndIsActive(groupId,"Y");
				if(groupExpense!= null && groupExpense.size() > 0) {
					createExpenseOwnedData(groupExpense,responseMap);
				}
			}else {
				responseMap.put("status", false);
				responseMap.put("message", "active user is not found in group");
			}
		}
		
	}


	private void createExpenseOwnedData(List<Expense> groupExpense, Map<String, Object> responseMap) {
		
		Map<Integer, Double> userTotalExpenses = new HashMap<>();
        double totalExpense = 0.0;

        for (Expense expense : groupExpense) {
            userTotalExpenses.put(expense.getUserId(), userTotalExpenses.getOrDefault(expense.getUserId(), 0.0) + expense.getPrice());
            totalExpense += expense.getPrice();
        }

        int numberOfUsers = userTotalExpenses.size();
        double averageExpense = totalExpense / numberOfUsers;

        List<ExpenseSplitDto> userBalances = userTotalExpenses.entrySet().stream()
            .map(entry -> new ExpenseSplitDto(entry.getKey(), entry.getValue() - averageExpense))
            .collect(Collectors.toList());

        userBalances.sort(Comparator.comparingDouble(ub -> ub.getBalance()));

        List<String> transactions = new ArrayList<>();
        int i = 0, j = userBalances.size() - 1;

        while (i < j) {
            ExpenseSplitDto creditor = userBalances.get(i);
            ExpenseSplitDto debtor = userBalances.get(j);
            double amount = Math.min(-creditor.getBalance(), debtor.getBalance());

            transactions.add("User " + creditor.getUserId() + " needs to give $" + amount + " to User " + debtor.getUserId());

            creditor.setBalance(creditor.getBalance() + amount);
            debtor.setBalance(debtor.getBalance() - amount);
            
            if (creditor.getBalance() == 0) i++;
            if (debtor.getBalance() == 0) j--;
        }

        for (String transaction : transactions) {
            System.out.println(transaction);
        }
	}


	
	
	
	
	
	
	
	
	
}
