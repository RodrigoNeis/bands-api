package com.iws.isobar.bandsapi.service.impl;

import com.iws.isobar.bandsapi.models.Band;
import com.iws.isobar.bandsapi.models.SortingEnum;
import com.iws.isobar.bandsapi.processor.FilterBandByIdProcessor;
import com.iws.isobar.bandsapi.processor.FilterBandsProcessor;
import com.iws.isobar.bandsapi.service.BandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of {@link BandService}
 */
@Service
@CacheConfig(cacheNames = {"bands"})
public class BandServiceImpl implements BandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BandServiceImpl.class);

    private RestTemplate restTemplate;
    private FilterBandsProcessor filterBandsProcessor;
    private FilterBandByIdProcessor filterBandByIdProcessor;

    @Value("${external.api.bands.uri}")
    private String apiUrl;

    @Autowired
    public BandServiceImpl(RestTemplate restTemplate, FilterBandsProcessor filterBandsProcessor, FilterBandByIdProcessor filterBandByIdProcessor) {
        this.restTemplate = restTemplate;
        this.filterBandsProcessor = filterBandsProcessor;
        this.filterBandByIdProcessor = filterBandByIdProcessor;
    }

    @Cacheable
    @Override
    public List<Band> getAllBands(SortingEnum sortingEnum) {
        return sortingEnum.applySorting(filterBandsProcessor.process(getBandsFromExternalService()));
    }

    @Cacheable
    @Override
    public List<Band> getBandsByFilterByName(String bandName, SortingEnum sortingEnum) {
        return sortingEnum.applySorting(filterBandsProcessor.process(bandName, getBandsFromExternalService()));
    }

    @Cacheable
    @Override
    public Band getBandById(String id) {
        return filterBandByIdProcessor.process(id, getBandsFromExternalService());
    }

    private List<Band> getBandsFromExternalService() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        List<Band> bands = new ArrayList<Band>();

        try {
            LOGGER.info("Going to call the url = [{}]", apiUrl);
            ResponseEntity<List<Band>> result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<Band>>() {
                    });

            bands = result.getBody();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info(bands.toString());
        return bands;
    }
}
