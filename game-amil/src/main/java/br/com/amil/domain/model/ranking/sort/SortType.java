package br.com.amil.domain.model.ranking.sort;

import sun.misc.Sort;

public enum SortType {
    ASC("ASC"),
    DESC("DESC");

    private String value;

    private SortType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
