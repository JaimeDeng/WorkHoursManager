package com.example.WorkHoursManager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.WorkHoursManager.Dto.AccountDto;
import com.example.WorkHoursManager.entity.*;

@Repository
@Qualifier("accountDao")
public interface AccountDao extends JpaRepository<Account, String> {
	
	Account getAccountByAccountId(String accountId);
	Account getAccountByEmployeeInfo(EmployeeInfo employeeInfo);
	
	//獲取指定主管ID的未審核日工時表
	@Transactional
	@Query("SELECT new com.example.WorkHoursManager.Dto.AccountDto(a.accountId, e.phone, "
			+ "e.email, e.employeeId) FROM Account a JOIN a.employeeInfo e ")
	List<AccountDto> getAllAccountInfoForVerify();
	
}
