package com.munro.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Munro {
    @JsonProperty("Running No")
    private Integer id;

    @JsonProperty("DoBIH Number")
    private String dobIhNumber;

    @JsonProperty("Streetmap")
    private String streetMap;

    @JsonProperty("Geograph")
    private String geolink;

    @JsonProperty("Hill-bagging")
    private String hillBagging;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("SMC Section")
    private String smcSection;

    @JsonProperty("RHB Section")
    private String rhbSection;

    @JsonProperty("_Section")
    private String _section;

    @JsonProperty("Height (m)")
    private Double heightMeters;

    @JsonProperty("Height (ft)")
    private Double heightFeet;

    @JsonProperty("Map 1:50")
    private String mapScale50;

    @JsonProperty("Map 1:25")
    private String mapScale25;

    @JsonProperty("Grid Ref")
    private String gridRef;

    @JsonProperty("GridRefXY")
    private String gridRefXY;

    @JsonProperty("xcoord")
    private String xCoord;

    @JsonProperty("ycoord")
    private String yCoord;

    @JsonProperty("1891")
    private String class1891;

    @JsonProperty("1921")
    private String class1921;

    @JsonProperty("1933")
    private String class1933;

    @JsonProperty("1953")
    private String class1953;

    @JsonProperty("1969")
    private String class1969;

    @JsonProperty("1974")
    private String class1974;

    @JsonProperty("1981")
    private String class1981;

    @JsonProperty("1984")
    private String class1984;

    @JsonProperty("1990")
    private String class1990;

    @JsonProperty("1997")
    private String class1997;

    @JsonProperty("Post 1997")
    private String classPost1997;

    @JsonProperty("Comments")
    private String comments;

}
