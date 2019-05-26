package com.iws.isobar.bandsapi.processor;

import com.iws.isobar.bandsapi.models.Band;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterBandByIdProcessor {

    public Band process(String id, List<Band> bands) {
        Band filteredBand = bands
                .stream()
                .filter(c -> StringUtils.equals(c.getId(), id))
                .findFirst()
                .orElse(null);
        return filteredBand;
    }
}
