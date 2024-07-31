package com.VooTreeVeeVuu.usecase.LogsUsecase.UpdateLogs;

import com.VooTreeVeeVuu.dto.LogsDTO;
import com.VooTreeVeeVuu.domain.entity.Logs;
import com.VooTreeVeeVuu.domain.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateLogsImpl implements UpdateLogs{
    @Autowired
    private LogsRepository logsRepository;

    @Transactional
    public Optional<LogsDTO> updateLogs(Long id, LogsDTO logsDTO){
        return logsRepository.findById(id).map(logs -> {
            logs.setAction(logsDTO.getAction());
            logs.setDate(logsDTO.getDate());
            logs.setHotel(logsDTO.getHotel());
            logs.setUser(logsDTO.getUser());
            Logs updated = logsRepository.save(logs);
            return toDTO(updated);
        });
    }

    private LogsDTO toDTO(Logs logs) {
        LogsDTO dto = new LogsDTO();
        dto.setAction(logs.getAction());
        dto.setDate(logs.getDate());
        dto.setHotel(logs.getHotel());
        dto.setUser(logs.getUser());
        return dto;
    }
}
