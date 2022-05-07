package br.com.customer.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public record ConvertUtils<T>(ModelMapperConfiguration modelMapperConfiguration) {

    public T convertRequestToEntity(T request, Class<T> type) {
        return modelMapperConfiguration.modelMapper().map(request, type);
    }

    public T convertEntityToRequest(T entity, Class<T> type) {
        return modelMapperConfiguration.modelMapper().map(entity, type);
    }

    public List<T> convertToListResponse(List<T> response, Class<T> type) {
        return response
                .stream()
                .map(responses -> modelMapperConfiguration.modelMapper().map(responses, type))
                .collect(Collectors.toList());

    }


}
