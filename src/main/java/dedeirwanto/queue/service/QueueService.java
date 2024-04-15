package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.QueueResponseDTO;

public interface QueueService {
    QueueResponseDTO queuePrint(String serviceTypeId);

    QueueResponseDTO queueCall(String counterId);

    Integer queueTotal(String serviceTypeId);

    Integer queueRemaining(String serviceTypeId);

    Integer queueCompleted(String serviceTypeId);

    QueueResponseDTO getLastQueueByCounter(String counterId);

    QueueResponseDTO getQueueCalled();

}
