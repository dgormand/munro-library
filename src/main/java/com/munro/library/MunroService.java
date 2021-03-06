package com.munro.library;

import ch.lambdaj.function.compare.ArgumentComparator;
import com.munro.library.entity.Munro;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.*;

public class MunroService {
    protected static final String DESC = "DESC";
    protected static final String ASC = "ASC";
    protected static final String ORDER_BY_NAME = "orderByName";
    protected static final String ORDER_BY_HEIGHT = "orderByHeight";
    protected static final String LIMIT = "limit";
    protected static final String CATEGORY = "category";
    protected static final String CATEGORY_MUN = "MUN";
    protected static final String CATEGORY_BOTH = "BOTH";
    protected static final String MIN_HEIGHT = "minHeight";
    protected static final String MAX_HEIGHT = "maxHeight";
    protected static final String CATEGORY_TOP = "TOP";
    protected static final String EMPTY_STRING = "";
    protected static final String SMALLER_THAN_MIN_HEIGHT = "Max height cannot be smaller than min height";
    protected static final String GREATER_THAN_0 = "Limit value needs to be greater than 0";
    protected static final String LIMIT_NEEDS_TO_BE_A_NUMBER = "Limit needs to be a number";
    protected static final String HEIGHT_NEEDS_TO_BE_NUMBER = "Height needs to be number";
    final Comparator<Munro> byName = new ArgumentComparator<>(on(Munro.class).getName());
    final Comparator<Munro> byHeight = new ArgumentComparator<>(on(Munro.class).getHeightMeters());

    public List<Munro> getResult(List<Munro> munroList, Map<String, String> params) {
        List<Munro> resultList = munroList;
        resultList.removeIf(munro -> EMPTY_STRING.equals(munro.getClassPost1997()));

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            resultList = filterCategory(resultList, key, value);

            resultList = applySort(key, resultList, entry, ORDER_BY_NAME, byName);

            resultList = applySort(key, resultList, entry, ORDER_BY_HEIGHT, byHeight);

            resultList = filterMinHeight(params, resultList, key, value);

            resultList = filterMaxHeight(params, resultList, key, value);

            resultList = limitResults(resultList, key, value);

        }

        return resultList;
    }

    private List<Munro> limitResults(List<Munro> resultList, String key, String value) {
        if (key.equals(LIMIT)) {
            int elemNo;
            try {
                elemNo = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(LIMIT_NEEDS_TO_BE_A_NUMBER);
            }
            if (elemNo <= 0)
                throw new IllegalArgumentException(GREATER_THAN_0);
            resultList = resultList.subList(0, elemNo);
        }
        return resultList;
    }

    private List<Munro> filterMaxHeight(Map<String, String> params, List<Munro> resultList, String key, String value) {
        if (key.equals(MAX_HEIGHT)) {
            String minVal = params.get(MIN_HEIGHT);
            validateHeightParameters(value, minVal);
            resultList = select(resultList, having(on(Munro.class).getHeightMeters(),
                    lessThanOrEqualTo(Double.parseDouble(value))));
        }
        return resultList;
    }

    private void validateHeightParameters(String maxValue, String minValue) {
        Double min;
        Double max;
        try {
            min = minValue !=null ? Double.parseDouble(minValue): null;
            max = maxValue !=null ? Double.parseDouble(maxValue): null;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(HEIGHT_NEEDS_TO_BE_NUMBER);
        }
        if (min != null && max != null && min > max) {
            throw new IllegalArgumentException(SMALLER_THAN_MIN_HEIGHT);
        }
    }

    private List<Munro> filterCategory(List<Munro> resultList, String key, String value) {
        if (key.equals(CATEGORY)) {
            if (!value.equals(CATEGORY_BOTH))
                resultList = select(resultList, having(on(Munro.class).getClassPost1997(), is(value.toUpperCase())));
        }
        return resultList;
    }

    private List<Munro> filterMinHeight(Map<String, String> params, List<Munro> resultList, String key, String value) {
        if (key.equals(MIN_HEIGHT)) {
            String maxVal = params.get(MAX_HEIGHT);
            validateHeightParameters(maxVal, value);
            resultList = select(resultList, having(on(Munro.class).getHeightMeters(),
                    greaterThanOrEqualTo(Double.parseDouble(value))));
        }
        return resultList;
    }

    private List<Munro> applySort(String key, List<Munro> resultList, Map.Entry<String, String> entry, String fieldName, Comparator<Munro> comparator) {
        if (key.equals(fieldName)) {
            String value = entry.getValue()==null ? ASC :  entry.getValue();
            if (ASC.equals(value.toUpperCase()))
                resultList = sort(resultList, on(Munro.class), comparator);
            else if (DESC.equals(value.toUpperCase()))
                resultList = sort(resultList, on(Munro.class), comparator.reversed());
        }
        return resultList;
    }
}
