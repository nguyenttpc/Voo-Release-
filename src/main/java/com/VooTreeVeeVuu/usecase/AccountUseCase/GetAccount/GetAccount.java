package com.VooTreeVeeVuu.usecase.AccountUseCase.GetAccount;

import com.VooTreeVeeVuu.dto.AccountDTO;

import java.util.Optional;

public interface GetAccount {
	Optional<AccountDTO> getAccountById(Long id);
}
