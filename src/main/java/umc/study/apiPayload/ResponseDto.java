package umc.study.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.apiPayload.code.BaseCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class ResponseDto<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // 성공 응답
    public static <T> ResponseDto<T> onSuccess(T data, BaseCode code) {
        return new ResponseDto<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), data);
    }

    // 실패 응답
    public static <T> ResponseDto<T> onFailure(String code, String message) {
        return new ResponseDto<>(false, code, message, null);
    }
}
