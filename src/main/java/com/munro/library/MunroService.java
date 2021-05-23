package com.munro.library;

import com.munro.library.entity.Munro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Munro> getResult(List<Munro> munroList, Map<String,String> params) {
        String key;
        List<Munro> resultList = munroList;
        resultList.removeIf(munro -> EMPTY_STRING.equals(munro.getClassPost1997()));

        for(Map.Entry<String, String> entry : params.entrySet()){
            key = entry.getKey();
            if(key.equals(CATEGORY)){
                String value = entry.getValue();
                if (!value.equals(CATEGORY_BOTH))
                    resultList = select(munroList, having(on(Munro.class).getClassPost1997(), is(value)));
            }
        }

        return resultList;
    }
}
