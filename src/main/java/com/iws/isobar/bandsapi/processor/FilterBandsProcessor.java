package com.iws.isobar.bandsapi.processor;

import com.iws.isobar.bandsapi.models.Band;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterBandsProcessor {
    public List<Band> process(String bandName, List<Band> bands) {
        List<Band> filteredList = bands
                .stream()
                .filter(c -> StringUtils.containsIgnoreCase(c.getName(), bandName))
                .map(d -> buildBasicBand(d))
                .collect(Collectors.toList());
        return filteredList;
    }

    public List<Band> process(List<Band> bands) {
        List<Band> filteredList = bands
                .stream()
                .map(d -> buildBasicBand(d))
                .collect(Collectors.toList());
        return filteredList;
    }

    private Band buildBasicBand(Band band) {
        return Band.builder()
                .name(band.getName())
                .numPlays(band.getNumPlays())
                .id(band.getId())
                .image(band.image)
                .build();
    }
}
