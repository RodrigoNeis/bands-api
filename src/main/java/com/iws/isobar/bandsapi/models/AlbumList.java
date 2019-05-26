
package com.iws.isobar.bandsapi.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "image",
        "releasedDate",
        "band",
        "tracks",
        "id"
})

public class AlbumList {

    @JsonProperty("name")
    public String name;
    @JsonProperty("image")
    public String image;
    @JsonProperty("releasedDate")
    public String releasedDate;
    @JsonProperty("band")
    public String band;
    @JsonProperty("tracks")
    public List<Track> tracks = null;
    @JsonProperty("id")
    public String id;
}
