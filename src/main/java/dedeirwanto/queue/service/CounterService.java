package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.CounterCreateRequestDTO;
import dedeirwanto.queue.dto.CounterResponseDTO;
import dedeirwanto.queue.dto.CounterUpdateRequestDTO;

import java.util.List;

public interface CounterService {
    CounterResponseDTO addCounter(CounterCreateRequestDTO request);

    CounterResponseDTO updateCounter(String counterId, CounterUpdateRequestDTO request);

    List<CounterResponseDTO> getAllCounter();

    CounterResponseDTO getCounter(String counterId);

    void deleteCounter(String counterId);

    // Total counter for dashboard
    long counterCount();
}
