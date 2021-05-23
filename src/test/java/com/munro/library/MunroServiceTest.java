package com.munro.library;

import com.munro.library.entity.Munro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static com.munro.library.MunroService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MunroServiceTest {

    protected static final String INVALID = "Invalid";
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

    @Test
    public void emptyCategoryMunrosRemoved_defaultOrder() {
        Map<String, String> params = new HashMap<>();
        List<Munro> munroResult = munroService.getResult(munroList, params);
        assertEquals(munroResult, Arrays.asList(munro1, munro2, munro3, munro4));
    }

    @Test
    public void inNameOrderTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ORDER_BY_NAME, ASC);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro2, munro4, munro3));
    }

    @Test
    public void inNameOrderReverseTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ORDER_BY_NAME, DESC);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro3, munro2, munro4, munro1));
    }

    @Test
    public void inNameOrderInvalidTest() {
        Map<String, String> params = new HashMap<>();
        params.put(ORDER_BY_NAME, INVALID);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro2, munro3, munro4));
    }

    @Test
    public void inHeightOrderTest() {
        Map<String,String> params = new HashMap<>();
        params.put(ORDER_BY_HEIGHT, ASC);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro2, munro4, munro1, munro3));
    }

    @Test
    public void inHeightOrderReverseTest() {
        Map<String,String> params = new HashMap<>();
        params.put(ORDER_BY_HEIGHT, DESC);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro3, munro1, munro4, munro2));
    }

    @Test
    public void inHeightOrderInvalidTest() {
        Map<String,String> params = new HashMap<>();
        params.put(ORDER_BY_HEIGHT, INVALID);
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro2, munro3, munro4));
    }

    @Test
    public void heightMinTest() {
        Map<String,String> params = new HashMap<>();
        params.put(MIN_HEIGHT, "1000");
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro1, munro3));
    }

    @Test
    public void heightMinGreaterThanMaxExceptionTest() {
        Map<String,String> params = new HashMap<>();
        params.put(MIN_HEIGHT, "1000");
        params.put(MAX_HEIGHT, "10");
        assertThrows(IllegalArgumentException.class, () ->munroService.getResult(munroList, params),
                SMALLER_THAN_MIN_HEIGHT);
    }

    @Test
    public void heightMaxTest() {
        Map<String,String> params = new HashMap<>();
        params.put(MAX_HEIGHT, "1000");
        assertEquals(munroService.getResult(munroList, params), Arrays.asList(munro2,munro4));
    }

    @Test
    public void heightMaxArgumentBeforeMinExceptionTest() {
        Map<String,String> params = new HashMap<>();
        params.put(MAX_HEIGHT, "10");
        params.put(MIN_HEIGHT, "1000");
        assertThrows(IllegalArgumentException.class, () ->munroService.getResult(munroList, params),
                SMALLER_THAN_MIN_HEIGHT);
    }

}
