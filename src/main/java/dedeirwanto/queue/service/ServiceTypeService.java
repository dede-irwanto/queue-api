package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.ServiceTypeCreateRequestDTO;
import dedeirwanto.queue.dto.ServiceTypeResponseDTO;
import dedeirwanto.queue.dto.ServiceTypeUpdateRequestDTO;

import java.util.List;

public interface ServiceTypeService {
    ServiceTypeResponseDTO addServiceType(ServiceTypeCreateRequestDTO request);

    ServiceTypeResponseDTO updateServiceType(String serviceTypeId, ServiceTypeUpdateRequestDTO request);

    List<ServiceTypeResponseDTO> getAllServiceType();

    ServiceTypeResponseDTO getServiceType(String serviceTypeId);

    void deleteServiceType(String serviceTypeId);

    // Total service type for dashboard
    long serviceTypeCount();

}
