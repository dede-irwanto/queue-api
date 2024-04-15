package dedeirwanto.queue.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class Helper {
    public static String renameImage() {
        return Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes());
    }

    public static Boolean isImageFile(MultipartFile file) {
        // check type file
        String contentType = file.getContentType();
        return Objects.equals(contentType, "image/jpg") || Objects.equals(contentType, "image/jpeg") || Objects.equals(contentType, "image/png");
    }

    public static Boolean isImageSize(MultipartFile file, long size) {
        // cek image size
        long fileSize = file.getSize();
        return fileSize <= size;
    }

}
