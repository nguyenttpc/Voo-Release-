package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.AccountDTO;
import com.VooTreeVeeVuu.dto.PassChangeDTO;
import com.VooTreeVeeVuu.services.AccountService;
import com.VooTreeVeeVuu.usecase.AccountUseCase.GetAccount.GetAccountImpl;
import com.VooTreeVeeVuu.usecase.AccountUseCase.GetAllAccounts.GetAllAccountsImpl;
import com.VooTreeVeeVuu.usecase.AccountUseCase.GetUserEmail.GetEmailByUserId;
import com.VooTreeVeeVuu.usecase.AccountUseCase.UpdateAccount.UpdateStatusImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private GetAllAccountsImpl getAllAccountsUseCase;

    @Autowired
    private GetAccountImpl getAccountUseCase;

    @Autowired
    private UpdateStatusImpl updateStatusUseCase;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GetEmailByUserId getEmailByUserId;

    @GetMapping()
    public List<AccountDTO> getAllAccounts() {
        return getAllAccountsUseCase.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Optional<AccountDTO> getAccountById(@PathVariable Long id) {
        return getAccountUseCase.getAccountById(id);
    }

    @GetMapping("/email/{id}")
    public Optional<AccountDTO> getEmailByUserId(@PathVariable Long id) {
        return getEmailByUserId.getEmailByUserId(id);
    }

    @PutMapping("/update/{id}")
    public Optional<AccountDTO> updateAccountStatus(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return updateStatusUseCase.updateStatus(id, accountDTO);
    }

    @PutMapping("/{id}/change-pass")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody PassChangeDTO passChangeDTO) {
        accountService.changePassword(id, passChangeDTO);
        return ResponseEntity.ok("Password has been changed successfully");
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam("avatar") MultipartFile file) {
        try {
            accountService.updateAvatar(id, file);
            return ResponseEntity.ok("Avatar uploaded successfully");
        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        try {
            byte[] avatar = accountService.getAvatar(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Adjust based on the image type stored
                    .body(avatar);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
