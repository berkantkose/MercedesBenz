package com.example.mercedes_benz.Modele;

import java.util.List;

public class RestMercedesResponse {

    private List<Mercedes> results;


    public List<Mercedes> getResults() {
        return results;
    }
    public void setResults(List<Mercedes> results) {
        this.results = results;
    }
}
