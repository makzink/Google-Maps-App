package com.kazmik.rapido.app.api_response.directions;

import java.util.List;

/**
 * Created by kazmik on 05/08/17.
 */

public class Directions_Response {
    private String status;
    private String error_message;
    private List<Routes_Content> routes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public List<Routes_Content> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes_Content> routes) {
        this.routes = routes;
    }
}
