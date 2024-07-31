package com.VooTreeVeeVuu.usecase.AccountUseCase.UpdateAccount;

import com.VooTreeVeeVuu.dto.AccountDTO;
import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateStatusImpl implements UpdateStatus {
	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public Optional<AccountDTO> updateStatus (Long id, AccountDTO accountDTO) {
		return accountRepository.findById(id).map(account -> {
			account.setStatus(accountDTO.isEnabled());
			return toDTO(account);
		});
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
		return dto;
	}
}
