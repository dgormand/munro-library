package com.munro.library;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = MunroApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("fixedport")
public class IntegrationTest {
    @Test
    public void givenUrl_limitOne_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?limit=1").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(1));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.name, is("Ben Chonzie"));
        assertThat(munroResponseObject.gridRef, is("NN773308"));
        assertThat(munroResponseObject.classPost1997, is("MUN"));
        assertThat(munroResponseObject.heightMeters, is(Double.parseDouble("931")));
    }

    @Test
    public void givenUrl_CatTop_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?category=top").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(2));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.name, is("Stob Binnein - Stob Coire an Lochain"));
        assertThat(munroResponseObject.gridRef, is("NN438220"));
        assertThat(munroResponseObject.classPost1997, is("TOP"));
        assertThat(munroResponseObject.heightMeters, is(Double.parseDouble("1068")));
    }

    @Test
    public void givenUrl_MaxHeight_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?maxHeight=1000").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(5));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.name, is("Ben Chonzie"));
    }

    @Test
    public void givenUrl_MinHeight_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?minHeight=1000").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(3));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.name, is("Ben More"));
    }

    @Test
    public void givenUrl_SortNameDesc_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?orderByName=desc").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(8));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.name, is("Stuc a' Chroin"));
    }

    @Test
    public void givenUrl_SortHeightAsc_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?orderByHeight=asc").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(8));
        MunroResponseObject munroResponseObject = munroResponseArray[7];
        assertThat(munroResponseObject.heightMeters, is(Double.parseDouble("1174")));
    }

    @Test
    public void givenUrl_SortHeightAscLimitThree_thenCorrect() {
        MunroResponseObject[] munroResponseArray = given().when().get("/api/search?orderByHeight=asc&limit=3&minHeight=950").as(MunroResponseObject[].class);
        assertThat(munroResponseArray.length, is(2));
        MunroResponseObject munroResponseObject = munroResponseArray[0];
        assertThat(munroResponseObject.heightMeters, is(Double.parseDouble("966")));
    }

}
