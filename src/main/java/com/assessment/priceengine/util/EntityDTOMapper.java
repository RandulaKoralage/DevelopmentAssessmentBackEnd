package com.assessment.priceengine.util;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDTOMapper {
    private static ModelMapper modelMapper ;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public static <D, T> D map(final T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public static <D, T> List<D> mapList(Collection<T> entityList, Class<D> dtoClass) {
        System.out.println(entityList.size());
        return entityList.stream().map(entity -> map(entity, dtoClass)).collect(Collectors.toList());
    }
}
