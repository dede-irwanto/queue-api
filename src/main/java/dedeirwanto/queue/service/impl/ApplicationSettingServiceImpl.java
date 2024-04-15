package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.ApplicationSettingCreateRequestDTO;
import dedeirwanto.queue.dto.ApplicationSettingResponseDTO;
import dedeirwanto.queue.dto.ApplicationSettingUpdateRequestDTO;
import dedeirwanto.queue.entity.ApplicationSetting;
import dedeirwanto.queue.entity.Background;
import dedeirwanto.queue.entity.Logo;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.repository.ApplicationSettingRepository;
import dedeirwanto.queue.repository.BackgroundRepository;
import dedeirwanto.queue.repository.LogoRepository;
import dedeirwanto.queue.service.ApplicationSettingService;
import dedeirwanto.queue.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class ApplicationSettingServiceImpl implements ApplicationSettingService {
    @Autowired
    private ApplicationSettingRepository applicationSettingRepository;
    @Autowired
    private LogoRepository logoRepository;
    @Autowired
    private BackgroundRepository backgroundRepository;

    private ApplicationSettingResponseDTO toApplicationSettingResponseDTO(ApplicationSetting applicationSetting) {
        return ApplicationSettingResponseDTO.builder()
                .id(applicationSetting.getId())
                .instituteName(applicationSetting.getInstituteName())
                .footer(applicationSetting.getFooter())
                .build();
    }

    private Boolean isApplicationSettingExists() {
        long count = applicationSettingRepository.count();
        return count > 0;
    }

    private Boolean isLogoExists() {
        long count = logoRepository.count();
        return count > 0;
    }

    private Boolean isBackgroundExists() {
        long count = backgroundRepository.count();
        return count > 0;
    }

    @Override
    public ApplicationSettingResponseDTO addApplicationSetting(ApplicationSettingCreateRequestDTO request) {
        if (isApplicationSettingExists()) {
            throw new BadRequestException("Application setting are configured!");
        }
        ApplicationSetting applicationSetting = new ApplicationSetting();
        applicationSetting.setId(UUID.randomUUID().toString());
        applicationSetting.setInstituteName(request.getInstituteName());
        applicationSetting.setFooter(request.getFooter());
        applicationSettingRepository.save(applicationSetting);
        return toApplicationSettingResponseDTO(applicationSetting);

    }

    @Override
    public ApplicationSettingResponseDTO updateApplicationSetting(ApplicationSettingUpdateRequestDTO request) {
        if (!isApplicationSettingExists()) {
            throw new BadRequestException("Application setting has not been configured. Please configure it first!");
        }
        ApplicationSetting applicationSetting = applicationSettingRepository.findAll().getFirst();
        String instituteName = (request.getInstituteName() == null) ? applicationSetting.getInstituteName() : request.getInstituteName();
        String footer = (request.getFooter() == null) ? applicationSetting.getFooter() : request.getFooter();

        applicationSetting.setInstituteName(instituteName);
        applicationSetting.setFooter(footer);
        applicationSettingRepository.save(applicationSetting);
        return toApplicationSettingResponseDTO(applicationSetting);
    }

    @Override
    public ApplicationSettingResponseDTO getApplicationSetting() {
        ApplicationSetting applicationSetting = applicationSettingRepository.findAll().getFirst();
        return toApplicationSettingResponseDTO(applicationSetting);
    }

    @Override
    public void addLogo(MultipartFile file) throws IOException {
        if (isLogoExists()) {
            throw new BadRequestException("Logo is already exists!");
        }

        if (!Helper.isImageFile(file)) {
            throw new BadRequestException("Logo must be jpg or png!");
        }

        if (!Helper.isImageSize(file, 50_000)) {
            throw new BadRequestException("Logo must be less than or equals to 50 KB");
        }

        ApplicationSetting applicationSetting = applicationSettingRepository.findAll().getFirst();
        String fileName = Helper.renameImage();

        Logo logo = new Logo();
        logo.setId(UUID.randomUUID().toString());
        logo.setName(fileName);
        logo.setType(file.getContentType());
        logo.setData(file.getBytes());
        logo.setApplicationSetting(applicationSetting);
        logoRepository.save(logo);
    }

    @Override
    public void updateLogo(MultipartFile file) throws IOException {
        if (!Helper.isImageFile(file)) {
            throw new BadRequestException("Logo must be jpg or png!");
        }

        if (!Helper.isImageSize(file, 50_000)) {
            throw new BadRequestException("Logo must be less than or equals to 50 KB");
        }

        Logo logo = logoRepository.findAll().getFirst();

        logo.setName(Helper.renameImage());
        logo.setType(file.getContentType());
        logo.setData(file.getBytes());
        logoRepository.save(logo);

    }

    @Override
    public Logo getLogo() {
        return logoRepository.findAll().getFirst();
    }

    @Override
    public void addBackground(MultipartFile file) throws IOException {
        if (isBackgroundExists()) {
            throw new BadRequestException("Background is already exists!");
        }

        if (!Helper.isImageFile(file)) {
            throw new BadRequestException("Background must be jpg or png!");
        }

        if (!Helper.isImageSize(file, 1000_000)) {
            throw new BadRequestException("Background must be less than or equals to 1 MB");
        }

        ApplicationSetting applicationSetting = applicationSettingRepository.findAll().getFirst();
        String fileName = Helper.renameImage();

        Background background = new Background();
        background.setId(UUID.randomUUID().toString());
        background.setName(fileName);
        background.setType(file.getContentType());
        background.setData(file.getBytes());
        background.setApplicationSetting(applicationSetting);
        backgroundRepository.save(background);
    }

    @Override
    public void updateBackground(MultipartFile file) throws IOException {
        if (!Helper.isImageFile(file)) {
            throw new BadRequestException("Background must be jpg or png!");
        }

        if (!Helper.isImageSize(file, 1000_000)) {
            throw new BadRequestException("Background must be less than or equals to 1 MB");
        }

        Background background = backgroundRepository.findAll().getFirst();

        background.setName(Helper.renameImage());
        background.setType(file.getContentType());
        background.setData(file.getBytes());
        backgroundRepository.save(background);
    }

    @Override
    public Background getBackground() {
        return backgroundRepository.findAll().getFirst();
    }
}
