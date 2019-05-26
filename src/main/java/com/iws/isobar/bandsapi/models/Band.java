
package com.iws.isobar.bandsapi.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "image",
        "genre",
        "biography",
        "numPlays",
        "albums",
        "id",
        "albumList"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Band {

    @JsonProperty("name")
    public String name;
    @JsonProperty("image")
    public String image;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("biography")
    public String biography;
    @JsonProperty("numPlays")
    public Integer numPlays;
    @JsonProperty("albums")
    public List<String> albums = null;
    @JsonProperty("id")
    public String id;
    @JsonProperty("albumList")
    public List<List<AlbumList>> albumList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
