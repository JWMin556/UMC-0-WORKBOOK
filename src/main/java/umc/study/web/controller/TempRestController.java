package umc.study.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.code.status.SuccessStatus;
import umc.study.converter.TempConverter;
import umc.study.service.TempService.TempQueryService;
import umc.study.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
public class TempRestController {

    private final TempQueryService tempQueryService;

    public TempRestController(@Qualifier("tempQueryServiceImpl") TempQueryService tempQueryService) {
        this.tempQueryService = tempQueryService;
    }

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}
