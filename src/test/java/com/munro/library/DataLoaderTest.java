package com.munro.library;

import com.munro.library.entity.Munro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DataLoaderTest {
    private static List<Munro> munroList;

    @BeforeAll
    public static void setup() {
        DataLoader dataLoader = new DataLoader();
        ReflectionTestUtils.setField(dataLoader, "charset", StandardCharsets.ISO_8859_1.toString());
        munroList = new ArrayList<>(dataLoader.loadData("csvData/munrotab_v6.2.csv"));
    }

    @Test
    public void dataFromCsvIsPersisted() {
        assertThat(munroList.size(), is(10));
    }

    @Test
    public void checkIfFirstRowIsCorrect() {
        Munro first = munroList.get(0);
        assertThat(first.getName(), is("Ben Chonzie"));
        assertThat(first.getHeightMeters(), is(Double.parseDouble("931")));
        assertThat(first.getClassPost1997(), is("MUN"));
        assertThat(first.getGridRef(), is("NN773308"));
    }

    @Test
    public void checkIfLastRowIsCorrect() {
        Munro last = munroList.get(9);
        assertThat(last.getName(), is("Cruach Ardrain SW Top"));
        assertThat(last.getHeightMeters(), is(Double.parseDouble("1044.9")));
        assertThat(last.getClassPost1997(), is(""));
        assertThat(last.getGridRef(), is("NN408211"));
    }

}
