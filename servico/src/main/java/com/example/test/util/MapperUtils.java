package com.example.test.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils
{
    private static ModelMapper modelMapper = new ModelMapper();

    /**
     * Hide from public usage.
     */
    private MapperUtils() {
    }

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param destinationType DTO.class.
     *
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> destinationType) {
        return modelMapper.map(entity, destinationType);
    }

    /**
     * <p>Note: Recebe uma lista de objetos e retorna uma lista DTO.</p>
     *
     * @param <D> type of result object.
     * @param <T> type of source object to map from.
     * @param entityList Lista de objetos, entitys that needs to be mapped.
     * @param destinationType DTO.class.
     *
     * @return List of <code>destinationTypeList</code> type.
     */
    public static <D, T> List<D> map(List<T> entityList, Class<D> destinationType) {
        List<D> outList = new ArrayList<>();
        for (T entity : entityList) {
            outList.add(modelMapper.map(entity, destinationType));
        }
        return outList;
    }
}
