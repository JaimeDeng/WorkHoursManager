package com.example.WorkHoursManager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.entity.*;

@Repository
@Qualifier("accountDao")
public interface AccountDao extends JpaRepository<Account, String> {
	
	Account getAccountByAccountId(String accountId);
	Account getAccountByEmployeeInfo(EmployeeInfo employeeInfo);
	
}
