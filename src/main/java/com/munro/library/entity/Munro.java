package com.munro.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
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
    private transient Integer id;

    @JsonProperty("DoBIH Number")
    private transient String dobIhNumber;

    @JsonProperty("Streetmap")
    private transient String streetMap;

    @JsonProperty("Geograph")
    private transient String geolink;

    @JsonProperty("Hill-bagging")
    private transient String hillBagging;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("SMC Section")
    private transient String smcSection;

    @JsonProperty("RHB Section")
    private transient String rhbSection;

    @JsonProperty("_Section")
    private transient String section;

    @JsonProperty("Height (m)")
    private Double heightMeters;

    @JsonProperty("Height (ft)")
    private transient Double heightFeet;

    @JsonProperty("Map 1:50")
    private transient String mapScale50;

    @JsonProperty("Map 1:25")
    private transient String mapScale25;

    @JsonProperty("Grid Ref")
    private String gridRef;

    @JsonProperty("GridRefXY")
    private transient String gridRefXY;

    @JsonProperty("xcoord")
    private transient String xCoord;

    @JsonProperty("ycoord")
    private transient String yCoord;

    @JsonProperty("1891")
    private transient String class1891;

    @JsonProperty("1921")
    private transient String class1921;

    @JsonProperty("1933")
    private transient String class1933;

    @JsonProperty("1953")
    private transient String class1953;

    @JsonProperty("1969")
    private transient String class1969;

    @JsonProperty("1974")
    private transient String class1974;

    @JsonProperty("1981")
    private transient String class1981;

    @JsonProperty("1984")
    private transient String class1984;

    @JsonProperty("1990")
    private transient String class1990;

    @JsonProperty("1997")
    private transient String class1997;

    @JsonProperty("Post 1997")
    private String classPost1997;

    @JsonProperty("Comments")
    private transient String comments;

}
