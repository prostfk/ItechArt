package by.itechart.contacts.config;

import by.itechart.contacts.model.entity.Address;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AddressHandlerMethodArgumentConfiguration implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Address.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Long id = Long.parseLong(nativeWebRequest.getParameter("id"));
        String country = nativeWebRequest.getParameter("country");
        String city = nativeWebRequest.getParameter("city");
        String street = nativeWebRequest.getParameter("street");
        String house = nativeWebRequest.getParameter("house");
        String flat = nativeWebRequest.getParameter("flat");
        String postIndex = nativeWebRequest.getParameter("postIndex");
        return new Address(id,country,city, street,house,flat,postIndex);
    }
}
