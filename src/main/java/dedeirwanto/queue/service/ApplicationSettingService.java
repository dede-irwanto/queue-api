package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.ApplicationSettingCreateRequestDTO;
import dedeirwanto.queue.dto.ApplicationSettingResponseDTO;
import dedeirwanto.queue.dto.ApplicationSettingUpdateRequestDTO;
import dedeirwanto.queue.entity.Background;
import dedeirwanto.queue.entity.Logo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ApplicationSettingService {
    ApplicationSettingResponseDTO addApplicationSetting(ApplicationSettingCreateRequestDTO request);

    ApplicationSettingResponseDTO updateApplicationSetting(ApplicationSettingUpdateRequestDTO request);

    ApplicationSettingResponseDTO getApplicationSetting();

    void addLogo(MultipartFile file) throws IOException;

    void updateLogo(MultipartFile file) throws IOException;

    Logo getLogo();

    void addBackground(MultipartFile file) throws IOException;

    void updateBackground(MultipartFile file) throws IOException;

    Background getBackground();

}
