package com.wl.uniformresponseformat.advice;

import com.wl.uniformresponseformat.annotation.ExcludeFlag;
import com.wl.uniformresponseformat.response.BaseResult;
import com.wl.uniformresponseformat.response.ResultBuilder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/11 14:52
 */
@ControllerAdvice(basePackages = {"com.wl.uniformresponseformat.controller"})
public class ResponseResultWrapper implements ResponseBodyAdvice<Object> {

    /**
     * 是否需要处理,那么这里我们通过接口方法上是否有上一步定义的@ExcludeFlag注解来判断
     * @param returnType
     * @param converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.requireNonNull(returnType.getMethod()).getAnnotation(ExcludeFlag.class) == null;
    }

    /**
     * 表示具体的处理，body表示原有的方法返回值，这里我们对其包裹一层，就实现了统一的格式返回。
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return Object
     */
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof BaseResult) {
            return body;
        }
        return ResultBuilder.success(body);
    }
}
