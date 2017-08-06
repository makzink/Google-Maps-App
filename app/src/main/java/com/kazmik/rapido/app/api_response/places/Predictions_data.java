package com.kazmik.rapido.app.api_response.places;

/**
 * Created by kazmik on 05/08/17.
 */

public class Predictions_data {
    private String description;
    private String place_id;
    private Predication_Structured_data structured_formatting;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public Predication_Structured_data getStructured_formatting() {
        return structured_formatting;
    }

    public void setStructured_formatting(Predication_Structured_data structured_formatting) {
        this.structured_formatting = structured_formatting;
    }
}
