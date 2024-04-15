package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.CounterCreateRequestDTO;
import dedeirwanto.queue.dto.CounterResponseDTO;
import dedeirwanto.queue.dto.CounterUpdateRequestDTO;
import dedeirwanto.queue.entity.Counter;
import dedeirwanto.queue.entity.ServiceType;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.repository.CounterRepository;
import dedeirwanto.queue.repository.ServiceTypeRepository;
import dedeirwanto.queue.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public CounterResponseDTO addCounter(CounterCreateRequestDTO request) {
        ServiceType serviceType = serviceTypeRepository.findById(request.getServiceTypeId()).orElseThrow(() -> new BadRequestException("Invalid service type"));

        if (counterRepository.existsByName(request.getName())) {
            throw new BadRequestException("Counter name already exists");
        }

        Counter counter = new Counter();
        counter.setId(UUID.randomUUID().toString());
        counter.setName(request.getName());
        counter.setServiceType(serviceType);

        counterRepository.save(counter);

        return CounterResponseDTO.builder()
                .id(counter.getId())
                .name(counter.getName())
                .serviceType(counter.getServiceType())
                .build();
    }

    @Override
    public CounterResponseDTO updateCounter(String counterId, CounterUpdateRequestDTO request) {

        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> new BadRequestException("Invalid counter!"));

        String name = (request.getName() == null) ? counter.getName() : request.getName();
        String serviceTypeId = (request.getServiceTypeId() == null) ? counter.getServiceType().getId() : request.getServiceTypeId();

        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type!"));

        counter.setName(name);
        counter.setServiceType(serviceType);

        counterRepository.save(counter);

        return CounterResponseDTO.builder()
                .id(counter.getId())
                .name(counter.getName())
                .serviceType(counter.getServiceType())
                .build();
    }

    @Override
    public List<CounterResponseDTO> getAllCounter() {
        List<Counter> counters = counterRepository.findAll(Sort.by("name"));

        return counters.stream().map((c) -> {
            CounterResponseDTO dto = new CounterResponseDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setServiceType(c.getServiceType());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CounterResponseDTO getCounter(String counterId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> new BadRequestException("Invalid counter!"));

        return CounterResponseDTO.builder()
                .id(counter.getId())
                .name(counter.getName())
                .serviceType(counter.getServiceType())
                .build();
    }

    @Override
    public void deleteCounter(String counterId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> new BadRequestException("Invalid counter!"));
        counterRepository.delete(counter);
    }

    @Override
    public long counterCount() {
        return counterRepository.count();
    }
}
