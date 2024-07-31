package com.VooTreeVeeVuu.usecase.AccountUseCase.UpdateAccount;

import com.VooTreeVeeVuu.dto.AccountDTO;

import java.util.Optional;

public interface UpdateStatus {
	Optional<AccountDTO> updateStatus(Long id, AccountDTO accountDTO);
}
