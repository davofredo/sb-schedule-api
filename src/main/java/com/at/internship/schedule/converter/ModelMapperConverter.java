package com.at.internship.schedule.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperConverter {
    private final ModelMapper modelMapper;

    public ModelMapperConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <S, T> T convertTo(S source, Class<T> targetClass){
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> convertListTo(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
