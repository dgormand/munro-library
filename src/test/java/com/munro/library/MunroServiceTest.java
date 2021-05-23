package com.munro.library;

import com.munro.library.entity.Munro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static com.munro.library.MunroService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MunroServiceTest {

    private final List<Munro> munroList = new ArrayList<>();
    private final MunroService munroService = new MunroService();

    private final Munro munro1 = mock(Munro.class);
    private final Munro munro2 = mock(Munro.class);
    private final Munro munro3 = mock(Munro.class);
    private final Munro munro4 = mock(Munro.class);
    private final Munro munro5 = mock(Munro.class);

    @BeforeAll
    private void setup() {
        when(munro1.getName()).thenReturn("AAA");
        when(munro1.getClassPost1997()).thenReturn(CATEGORY_MUN);
        when(munro1.getHeightMeters()).thenReturn(Double.parseDouble("1150"));

        when(munro2.getName()).thenReturn("BBB");
        when(munro2.getClassPost1997()).thenReturn(CATEGORY_TOP);
        when(munro2.getHeightMeters()).thenReturn(Double.parseDouble("850"));

        when(munro3.getName()).thenReturn("CCC");
        when(munro3.getClassPost1997()).thenReturn(CATEGORY_TOP);
        when(munro3.getHeightMeters()).thenReturn(Double.parseDouble("1900"));

        when(munro4.getName()).thenReturn("BBB");
        when(munro4.getClassPost1997()).thenReturn(CATEGORY_MUN);
        when(munro4.getHeightMeters()).thenReturn(Double.parseDouble("900"));

        when(munro5.getName()).thenReturn("ZZZ");
        when(munro5.getClassPost1997()).thenReturn("");
        when(munro5.getHeightMeters()).thenReturn(Double.parseDouble("1000"));

        munroList.addAll(Arrays.asList(munro1, munro2, munro3, munro4, munro5));
    }

    @Test
    public void categoryMunTest() {
        Map<String, String> params = new HashMap<>();
        params.put(CATEGORY, CATEGORY_MUN);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro4));
    }

    @Test
    public void categoryTopTest() {
        Map<String, String> params = new HashMap<>();
        params.put(CATEGORY, CATEGORY_TOP);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro2, munro3));
    }

    @Test
    public void categoryBothTest() {
        Map<String, String> params = new HashMap<>();
        params.put(CATEGORY, MunroService.CATEGORY_BOTH);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro2, munro3, munro4));
    }

}
