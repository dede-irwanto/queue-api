package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.QueueResponseDTO;
import dedeirwanto.queue.entity.Counter;
import dedeirwanto.queue.entity.Queue;
import dedeirwanto.queue.entity.ServiceType;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.repository.CounterRepository;
import dedeirwanto.queue.repository.QueueRepository;
import dedeirwanto.queue.repository.ServiceTypeRepository;
import dedeirwanto.queue.service.QueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class QueueServiceImpl implements QueueService {
    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private CounterRepository counterRepository;

    private boolean isDateToday(String numberLike) {
        return queueRepository.existsByCreatedAtAndNumberLikeOrderByPrintedAtDesc(LocalDate.now(), "%" + numberLike + "%");
    }

    private String getNumber(String numberLike) {
        if (isDateToday(numberLike)) {
            Queue queue = queueRepository.findFirstByCreatedAtAndNumberLikeOrderByPrintedAtDesc(LocalDate.now(), "%" + numberLike + "%");
            String number = queue.getNumber().replace(numberLike, "");
            int i = Integer.parseInt(number);
            i += 1;
            if (i < 10) {
                return numberLike + "00" + i;
            } else if (i < 100) {
                return numberLike + "0" + i;
            } else {
                return numberLike + i;
            }
        }
        return numberLike + "001";
    }

    @Override

    public QueueResponseDTO queuePrint(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service " +
                "type!"));

        String number = getNumber(serviceType.getCode());

        Queue queue = new Queue();
        queue.setId(UUID.randomUUID().toString());
        queue.setNumber(number);
        queue.setCreatedAt(LocalDate.now());
        queue.setPrintedAt(System.currentTimeMillis());
        queue.setServiceType(serviceType.getName());
        queue.setServiceTypeDescription(serviceType.getDescription());
        queue.setIsCalled(false);
        queueRepository.save(queue);

        return QueueResponseDTO.builder()
                .number(queue.getNumber())
                .serviceTypeDescription(queue.getServiceTypeDescription())
                .printedAt(queue.getPrintedAt())
                .build();
    }

    @Override
    public QueueResponseDTO queueCall(String counterId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> new BadRequestException("Invalid counter!"));

        ServiceType serviceType = serviceTypeRepository.findById(counter.getServiceType().getId()).orElseThrow(() -> new BadRequestException("Invalid service " +
                "type!"));

        String code = "%" + serviceType.getCode() + "%";

        Queue queue = queueRepository.findFirstByCreatedAtAndNumberLikeAndIsCalledIsFalseOrderByPrintedAtAsc(LocalDate.now(), code);
        queue.setIsCalled(true);
        queue.setCounter(counter.getName());
        queueRepository.save(queue);

        return QueueResponseDTO.builder()
                .number(queue.getNumber())
                .printedAt(queue.getPrintedAt())
                .serviceTypeDescription(queue.getServiceTypeDescription())
                .counter(queue.getCounter())
                .build();
    }

    @Override
    public Integer queueTotal(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type"));
        String numberLike = "%" + serviceType.getCode() + "%";
        return queueRepository.countByCreatedAtAndNumberLike(LocalDate.now(), numberLike);
    }

    @Override
    public Integer queueRemaining(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type"));
        String numberLike = "%" + serviceType.getCode() + "%";
        return queueRepository.countByCreatedAtAndNumberLikeAndIsCalledIsFalse(LocalDate.now(), numberLike);
    }

    @Override
    public Integer queueCompleted(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type"));
        String numberLike = "%" + serviceType.getCode() + "%";
        return queueRepository.countByCreatedAtAndNumberLikeAndIsCalledIsTrue(LocalDate.now(), numberLike);
    }

    @Override
    public QueueResponseDTO getLastQueueByCounter(String counterId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow(() -> new BadRequestException("Invalid counter!"));
        Queue queue =
                queueRepository.findFirstByCreatedAtAndCounterAndIsCalledIsTrueOrderByPrintedAtDesc(LocalDate.now(),
                        counter.getName());

        return QueueResponseDTO.builder()
                .number(queue.getNumber())
                .serviceTypeDescription(queue.getServiceTypeDescription())
                .printedAt(queue.getPrintedAt())
                .counter(queue.getCounter())
                .build();
    }

    @Override
    public QueueResponseDTO getQueueCalled() {
        Queue queue = queueRepository.findFirstByCreatedAtAndIsCalledIsTrueOrderByPrintedAtDesc(LocalDate.now());
        return QueueResponseDTO.builder()
                .number(queue.getNumber())
                .serviceTypeDescription(queue.getServiceTypeDescription())
                .printedAt(queue.getPrintedAt())
                .counter(queue.getCounter())
                .build();
    }

    @Override
    public List<Object> getCharts() {
        return queueRepository.getCharts();
    }
}
