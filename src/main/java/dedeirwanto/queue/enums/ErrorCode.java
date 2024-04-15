package dedeirwanto.queue.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {

    BAD_REQUEST(400), NOT_FOUND(404), FORBIDDEN(403), UNAUTHORIZED(401), NOT_IMPLEMENTED(501);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int code() {
        return code;
    }
}
