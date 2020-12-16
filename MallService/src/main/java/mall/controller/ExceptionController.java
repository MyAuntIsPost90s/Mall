package mall.controller;

import com.ch.web.gateway.session.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author cch
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public void handler(Exception e) {
        log.error("捕获到未处理的系统异常", e);
        SessionHolder.currentSessionHolder().fail("系统发生异常，操作失败");
    }
}
