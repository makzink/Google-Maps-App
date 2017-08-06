package com.kazmik.rapido.app.api_response.directions;

import java.util.List;

/**
 * Created by kazmik on 05/08/17.
 */

public class Routes_Content {
    private String summary;
    private List<Legs_content> legs;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Legs_content> getLegs() {
        return legs;
    }

    public void setLegs(List<Legs_content> legs) {
        this.legs = legs;
    }
}
