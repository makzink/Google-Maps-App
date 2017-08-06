package com.kazmik.rapido.app.api_response.directions;

import java.util.List;

/**
 * Created by kazmik on 06/08/17.
 */

public class Legs_content {
    private LatLng end_location;
    private String end_address;
    private LatLng start_location;
    private String start_address;
    private List<Steps_Content> steps;

    public LatLng getEnd_location() {
        return end_location;
    }

    public void setEnd_location(LatLng end_location) {
        this.end_location = end_location;
    }

    public LatLng getStart_location() {
        return start_location;
    }

    public void setStart_location(LatLng start_location) {
        this.start_location = start_location;
    }

    public String getEnd_address() {
        return end_address;
    }

    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public List<Steps_Content> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps_Content> steps) {
        this.steps = steps;
    }
}
