package com.kazmik.rapido.app.api_response.places;

import java.util.List;

/**
 * Created by kazmik on 05/08/17.
 */

public class Places_Response {
    private String status;
    private String error_message;
    private List<Predictions_data> predictions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Predictions_data> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Predictions_data> predictions) {
        this.predictions = predictions;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
