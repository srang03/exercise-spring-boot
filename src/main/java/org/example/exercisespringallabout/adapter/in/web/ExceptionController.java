package org.example.exercisespringallabout.adapter.in.web;

import lombok.Getter;
import org.example.exercisespringallabout.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
    // 1. 일반 Exception
    @GetMapping("/server-error")
    public String throwGenericException() {
        throw new RuntimeException("서버 내부에서 예기치 못한 오류가 발생했습니다.");
    }

    // 3. BusinessException (커스텀 예외)
    @GetMapping("/business-error")
    public String throwBusinessException() {
        throw new BusinessException("ERR-BUSINESS", "비즈니스 로직 오류 발생!");
    }
}
