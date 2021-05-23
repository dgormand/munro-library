package com.munro.library;

import ch.lambdaj.function.compare.ArgumentComparator;
import com.munro.library.entity.Munro;

import java.util.*;

import static ch.lambdaj.Lambda.*;
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
    final Comparator<Munro> byName = new ArgumentComparator<>(on(Munro.class).getName());
    final Comparator<Munro> byHeight = new ArgumentComparator<>(on(Munro.class).getHeightMeters());

    public List<Munro> getResult(List<Munro> munroList, Map<String,String> params) {
        String key;
        List<Munro> resultList = munroList;
        resultList.removeIf(munro -> EMPTY_STRING.equals(munro.getClassPost1997()));

        for(Map.Entry<String, String> entry : params.entrySet()){
            key = entry.getKey();
            if(key.equals(CATEGORY)){
                String value = entry.getValue();
                if (!value.equals(CATEGORY_BOTH))
                    resultList = select(resultList, having(on(Munro.class).getClassPost1997(), is(value)));
            }

            resultList = applySort(key, resultList, entry, ORDER_BY_NAME, byName);

            resultList = applySort(key, resultList, entry, ORDER_BY_HEIGHT, byHeight);
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
