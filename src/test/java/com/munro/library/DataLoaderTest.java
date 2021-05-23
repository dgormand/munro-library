package com.munro.library;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DataLoaderTest {

    @Test
    public void dataFromCsvIsPersisted() {
        DataLoader dataLoader = new DataLoader();
        Collection<Munro> munros = dataLoader.loadData();
        assertThat(munros.size(), is(10));
    }
}
