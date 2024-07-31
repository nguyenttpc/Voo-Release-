package com.VooTreeVeeVuu.usecase.LogsUsecase.DeleteLogs;

import com.VooTreeVeeVuu.domain.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteLogsImpl implements DeleteLogs{
    @Autowired
    private LogsRepository logsRepository;

    @Transactional
    public void deleteLogs(Long id){logsRepository.deleteById(id);
    }
}
