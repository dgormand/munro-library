package com.munro.library;

import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Collection;

public class DataLoader implements CommandLineRunner {
    public Collection<Munro> loadData() {

        return new ArrayList<>();
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
