package com.munro.library;

import com.google.gson.Gson;
import com.munro.library.entity.MunroEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class MunroController {

    @GetMapping("/api/search")
    public ResponseEntity<String> search(@RequestParam LinkedHashMap<String, String> allParams) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        MunroService munroService = new MunroService();
        Gson gson = new Gson();

        try{
            return new ResponseEntity<>(gson.toJson(munroService.getResult(MunroEnum.INSTANCE.getMunroList(), allParams)),
                    httpHeaders, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()),httpHeaders, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(gson.toJson(e.getMessage()),httpHeaders, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
