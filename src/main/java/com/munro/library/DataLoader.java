package com.munro.library;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.munro.library.entity.Munro;
import com.munro.library.entity.MunroEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private static final String EMPTY_STRING = "";
    @Value("${file.filename}")
    private String filename;
    @Value("${file.charset}")
    private String charset;

    private Collection<Munro> parseCsv(String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();

            mapper.enable(CsvParser.Feature.INSERT_NULLS_FOR_MISSING_COLUMNS);
            mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
            mapper.enable(CsvParser.Feature.IGNORE_TRAILING_UNMAPPABLE);

            InputStreamReader in = new InputStreamReader(getClass().getResourceAsStream(fileName), charset);
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

        List<Munro> munroList = new ArrayList<>(loadData(filename));
        munroEnum.setMunroList(munroList);
    }
}
