package com.VooTreeVeeVuu.usecase.AccountUseCase.GetUserEmail;

import com.VooTreeVeeVuu.dto.AccountDTO;

import java.util.Optional;

public interface GetEmailByUserId {
    Optional<AccountDTO> getEmailByUserId(Long id);
}
