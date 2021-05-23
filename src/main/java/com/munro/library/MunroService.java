package com.munro.library;

import ch.lambdaj.function.compare.ArgumentComparator;
import com.munro.library.entity.Munro;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

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
    protected static final String SMALLER_THAN_MIN_HEIGHT = "Max Height cannot be smaller than min height";
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

            resultList = filterHeight(params, resultList, key, value);

        }

        return resultList;
    }

    private List<Munro> filterCategory(List<Munro> resultList, String key, String value) {
        if (key.equals(CATEGORY)) {
            if (!value.equals(CATEGORY_BOTH))
                resultList = select(resultList, having(on(Munro.class).getClassPost1997(), is(value)));
        }
        return resultList;
    }

    private List<Munro> filterHeight(Map<String, String> params, List<Munro> resultList, String key, String value) {
        if (key.equals(MIN_HEIGHT)) {
            String maxVal = params.get(MAX_HEIGHT);
            if (maxVal != null && Double.parseDouble(maxVal) < Double.parseDouble(value)) {
                throw new IllegalArgumentException(SMALLER_THAN_MIN_HEIGHT);
            }
            resultList = select(resultList, having(on(Munro.class).getHeightMeters(),
                    greaterThanOrEqualTo(Double.parseDouble(value))));
        }
        return resultList;
    }

    private List<Munro> applySort(String key, List<Munro> resultList, Map.Entry<String, String> entry, String fieldName, Comparator<Munro> comparator) {
        if (key.equals(fieldName)) {
            if (ASC.equals(entry.getValue()))
                resultList = sort(resultList, on(Munro.class), comparator);
            else if (DESC.equals(entry.getValue()))
                resultList = sort(resultList, on(Munro.class), comparator.reversed());
        }
        return resultList;
    }
}
