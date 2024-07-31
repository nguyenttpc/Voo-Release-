package com.VooTreeVeeVuu.usecase.AccountUseCase.GetAllAccounts;

import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import com.VooTreeVeeVuu.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllAccountsImpl implements GetAllAccounts {
	@Autowired
	private AccountRepository accountRepository;

	public List<AccountDTO> getAllAccounts () {
		return accountRepository.findAll().stream().map(this :: toDTO).collect(Collectors.toList());
	}

	private AccountDTO toDTO (Account account) {
		AccountDTO dto = new AccountDTO();
		dto.setId(account.getId());
		dto.setUsername(account.getUsername());
		dto.setPassword(account.getPassword());
		dto.setEmail(account.getEmail());
		dto.setPhoneNum(account.getPhoneNum());
		dto.setRoles(account.getRoles());
		dto.setEnabled(account.isEnabled());
		dto.setUser(account.getUser());
		return dto;
	}
}
