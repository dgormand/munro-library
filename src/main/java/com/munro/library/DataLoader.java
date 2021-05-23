package com.munro.library;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.munro.library.entity.Munro;
import com.munro.library.entity.MunroEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private static final String EMPTY_STRING = "";

    private Collection<Munro> parseCsv(String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();

            mapper.enable(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS);
            mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
            mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);
            File file = new ClassPathResource(fileName).getFile();

            InputStreamReader in = new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1);
            MappingIterator<Munro> readValues = mapper.readerFor(Munro.class).with(bootstrapSchema).readValues(in);

            return readValues.readAll();


        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Collection<Munro> loadData(String fileName) {
        List<Munro> munroList = new ArrayList<>(parseCsv(fileName));
        munroList.removeIf(munro -> EMPTY_STRING.equals(munro.getName()));

        return munroList;
    }

    @Override
    public void run(String... args) {
        MunroEnum munroEnum = MunroEnum.INSTANCE;

        List<Munro> munroList = new ArrayList<>(loadData("csvData/munrotab_v6.2.csv"));
        munroEnum.setMunroList(munroList);
    }
}
