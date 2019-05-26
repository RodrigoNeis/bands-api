package com.iws.isobar.bandsapi.service;

import com.iws.isobar.bandsapi.models.Band;
import com.iws.isobar.bandsapi.models.SortingEnum;

import java.util.List;

/**
 * Service responsible to communicate with the external APIs
 */
public interface BandService {
    /**
     * Gets all available band through the  heroku API
     *
     * @param sortingEnum enum to sort the results
     * @return a list of {@link Band}
     */
    List<Band> getAllBands(SortingEnum sortingEnum);

    /**
     * gets all the bands that match with the filter
     *
     * @param bandName    the name of the band
     * @param sortingEnum enum to sort the results
     * @return a list of {@link Band}
     */
    List<Band> getBandsByFilterByName(String bandName, SortingEnum sortingEnum);

    /**
     * Gets a band with the id
     *
     * @param id unique if of a band
     * @return {@link Band}
     */
    Band getBandById(String id);
}
