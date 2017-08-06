package com.kazmik.rapido.app.api_response.directions;

/**
 * Created by kazmik on 06/08/17.
 */

public class Steps_Content {
    private LatLng start_location;
    private String html_instructions;

    public LatLng getStart_location() {
        return start_location;
    }

    public void setStart_location(LatLng start_location) {
        this.start_location = start_location;
    }

    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }
}
