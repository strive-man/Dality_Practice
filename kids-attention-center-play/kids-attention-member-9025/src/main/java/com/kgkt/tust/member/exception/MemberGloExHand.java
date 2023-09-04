package com.kgkt.tust.member.exception;

import com.kgkt.tust.common.exception.GlobalExceptionHand;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zgq
 * @description 对于整个模块所有异常集中处理的异常处理类
 */
@RestControllerAdvice
public class MemberGloExHand extends GlobalExceptionHand {
}
