package com.iws.isobar.bandsapi.controller;

import com.iws.isobar.bandsapi.models.Band;
import com.iws.isobar.bandsapi.models.SortingEnum;
import com.iws.isobar.bandsapi.service.BandService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping("/bands/service/v1")
@RestController
public class BandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BandController.class);
    @Autowired
    BandService bandService;

    @RequestMapping(value = "/bands", method = GET)
    @ResponseBody
    public List<Band> getBands(@RequestParam(value = "name", required = false) String name, @RequestHeader("type.sort") SortingEnum sortingEnum) {
        if (StringUtils.isNotBlank(name)) {
            LOGGER.info("Request received at /bands?name=");
            return bandService.getBandsByFilterByName(name, sortingEnum);
        } else {
            LOGGER.info("Request received at /bands");
            return bandService.getAllBands(sortingEnum);
        }
    }

    @RequestMapping(value = "/bands/{id}", method = GET)
    @ResponseBody
    public Band getBandsById(@PathVariable String id) {
        LOGGER.info("Request received at /bands/{id}");
        return bandService.getBandById(id);
    }
}
