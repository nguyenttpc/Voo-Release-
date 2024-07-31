package com.VooTreeVeeVuu.usecase.LogsUsecase.UpdateLogs;

import com.VooTreeVeeVuu.dto.LogsDTO;

import java.util.Optional;

public interface UpdateLogs {
	Optional<LogsDTO> updateLogs(Long id, LogsDTO logsDTO);
}
