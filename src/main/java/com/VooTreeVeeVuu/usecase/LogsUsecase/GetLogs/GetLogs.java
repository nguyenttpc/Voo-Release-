package com.VooTreeVeeVuu.usecase.LogsUsecase.GetLogs;

import com.VooTreeVeeVuu.dto.LogsDTO;

import java.util.Optional;

public interface GetLogs {
	Optional<LogsDTO> getLogsById(Long id);
}
