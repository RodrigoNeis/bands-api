package com.iws.isobar.bandsapi.models;

import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
public enum SortingEnum {
    ALPHABETICAL_ORDER_ASC(1) {
        public List<Band> applySorting(List<Band> bands) {
            bands.sort(Comparator.comparing(Band::getName));
            return bands;
        }
    },
    ALPHABETICAL_ORDER_DESC(2) {
        public List<Band> applySorting(List<Band> bands) {
            bands.sort(Comparator.comparing(Band::getName).reversed());
            return bands;
        }
    },
    POPULARITY_ODER_ASC(3) {
        public List<Band> applySorting(List<Band> bands) {
            bands.sort(Comparator.comparing(Band::getNumPlays));
            return bands;
        }
    },
    POPULARITY_ODER_DESC(4) {
        public List<Band> applySorting(List<Band> bands) {
            bands.sort(Comparator.comparing(Band::getNumPlays).reversed());
            return bands;
        }
    };
    private int code;

    SortingEnum(int code) {
        this.code = code;
    }

    public abstract List<Band> applySorting(List<Band> bands);
}
