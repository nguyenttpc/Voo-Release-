package com.VooTreeVeeVuu.usecase.AccountUseCase.GetUserEmail;

import com.VooTreeVeeVuu.domain.entity.Account;
import com.VooTreeVeeVuu.domain.repository.AccountRepository;
import com.VooTreeVeeVuu.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetEmailByUserIdImpl implements GetEmailByUserId {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<AccountDTO> getEmailByUserId(Long id) {

        return accountRepository.findByUserId(id).map(this::toDTO);
    }

    private AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setEmail(account.getEmail());
        return dto;
    }
}
