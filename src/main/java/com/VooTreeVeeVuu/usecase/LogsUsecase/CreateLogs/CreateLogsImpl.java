package com.VooTreeVeeVuu.usecase.LogsUsecase.CreateLogs;


import com.VooTreeVeeVuu.dto.LogsDTO;
import com.VooTreeVeeVuu.domain.entity.Logs;
import com.VooTreeVeeVuu.domain.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateLogsImpl implements CreateLogs{
    @Autowired
    private LogsRepository logsRepository;

    @Transactional
    public LogsDTO createLogs (LogsDTO logsDTO){
         Logs logs = toEntity(logsDTO);
         Logs saved = logsRepository.save(logs);
        return toDTO(saved);
    }

    private LogsDTO toDTO(Logs logs) {
       LogsDTO dto = new LogsDTO();
        dto.setAction(logs.getAction());
        dto.setDate(logs.getDate());
        dto.setHotel(logs.getHotel());
        dto.setUser(logs.getUser());
        return dto;
    }

    private Logs toEntity(LogsDTO logsDTO) {
        Logs logs = new Logs();
        logs.setAction(logsDTO.getAction());
        logs.setDate(logsDTO.getDate());
        logs.setHotel(logsDTO.getHotel());
        logs.setUser(logsDTO.getUser());
        return logs;
    }
}
