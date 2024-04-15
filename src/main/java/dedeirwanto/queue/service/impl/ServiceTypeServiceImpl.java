package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.ServiceTypeCreateRequestDTO;
import dedeirwanto.queue.dto.ServiceTypeResponseDTO;
import dedeirwanto.queue.dto.ServiceTypeUpdateRequestDTO;
import dedeirwanto.queue.entity.ServiceType;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.repository.ServiceTypeRepository;
import dedeirwanto.queue.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public ServiceTypeResponseDTO addServiceType(ServiceTypeCreateRequestDTO request) {
        ServiceType serviceType = new ServiceType();
        serviceType.setId(UUID.randomUUID().toString());
        serviceType.setName(request.getName());
        serviceType.setDescription(request.getDescription());
        serviceType.setCode(request.getCode());

        try {
            serviceTypeRepository.save(serviceType);
        } catch (Exception e) {
            throw new BadRequestException("Duplicate code!");
        }

        return ServiceTypeResponseDTO.builder()
                .id(serviceType.getId())
                .name(serviceType.getName())
                .description(serviceType.getDescription())
                .code(serviceType.getCode())
                .build();
    }

    @Override
    public ServiceTypeResponseDTO updateServiceType(String serviceTypeId, ServiceTypeUpdateRequestDTO request) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service " +
                "type!"));

        String name = (request.getName() == null) ? serviceType.getName() : request.getName();
        String description = (request.getDescription() == null) ? serviceType.getDescription() : request.getDescription();
        String code = (request.getCode() == null) ? serviceType.getCode() : request.getCode();

        serviceType.setName(name);
        serviceType.setDescription(description);
        serviceType.setCode(code);

        try {
            serviceTypeRepository.save(serviceType);
        } catch (Exception e) {
            throw new BadRequestException("Duplicate code!");
        }

        return ServiceTypeResponseDTO.builder()
                .id(serviceType.getId())
                .name(serviceType.getName())
                .description(serviceType.getDescription())
                .code(serviceType.getCode())
                .build();
    }

    @Override
    public List<ServiceTypeResponseDTO> getAllServiceType() {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();

        return serviceTypes.stream().map((s) -> {
            ServiceTypeResponseDTO dto = new ServiceTypeResponseDTO();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setDescription(s.getDescription());
            dto.setCode(s.getCode());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ServiceTypeResponseDTO getServiceType(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type!"));

        return ServiceTypeResponseDTO.builder()
                .id(serviceType.getId())
                .name(serviceType.getName())
                .description(serviceType.getDescription())
                .code(serviceType.getCode())
                .build();
    }

    @Override
    public void deleteServiceType(String serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new BadRequestException("Invalid service type!"));
        serviceTypeRepository.delete(serviceType);
    }

    @Override
    public long serviceTypeCount() {
        return serviceTypeRepository.count();
    }
}
