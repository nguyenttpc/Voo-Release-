package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.LogsDTO;
import com.VooTreeVeeVuu.usecase.LogsUsecase.CreateLogs.CreateLogsImpl;
import com.VooTreeVeeVuu.usecase.LogsUsecase.DeleteLogs.DeleteLogsImpl;
import com.VooTreeVeeVuu.usecase.LogsUsecase.GetAllLogs.GetAllLogsImpl;
import com.VooTreeVeeVuu.usecase.LogsUsecase.GetLogs.GetLogsImpl;
import com.VooTreeVeeVuu.usecase.LogsUsecase.UpdateLogs.UpdateLogsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/logs")
public class LogsController {
	 @Autowired
	 private CreateLogsImpl createLogsUseCase;

    @Autowired
    private UpdateLogsImpl updateLogsUseCase;

    @Autowired
	private DeleteLogsImpl deleteLogsUseCase;

	@Autowired
	private GetAllLogsImpl getAllLogsUseCase;

	@Autowired
	private GetLogsImpl getLogsUseCase;

	@GetMapping ()
	public List<LogsDTO> getAllLogs(){
		return getAllLogsUseCase.getAllLogs();
	}

	@GetMapping ("/{id}")
	public Optional<LogsDTO> getLogsById (@PathVariable Long id){
		return getLogsUseCase.getLogsById(id);
	}

	@PostMapping
	public LogsDTO createLogs(@RequestBody LogsDTO dto) {
		return createLogsUseCase.createLogs(dto);
	}

	@PutMapping ("/update/{id}")
	public Optional<LogsDTO> updateLogs (@RequestBody LogsDTO dto, @PathVariable Long id) {
		return updateLogsUseCase.updateLogs(id,dto);
	}

	@DeleteMapping ("/delete/{id}")
	public void deleteLogs (@PathVariable Long id) {
		deleteLogsUseCase.deleteLogs(id);
	}

}
