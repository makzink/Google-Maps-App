package com.kazmik.rapido.app.home;

import com.google.android.gms.maps.model.Polyline;
import com.kazmik.rapido.app.api_response.directions.Routes_Content;
import com.kazmik.rapido.app.api_response.directions.Steps_Content;

import java.util.List;

/**
 * Created by kazmik on 06/08/17.
 */

public class Polylines_Steps {
    private Polyline polyline;
    private Routes_Content route;

    public Polyline getPolyline() {
        return polyline;
    }

    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    public Routes_Content getRoute() {
        return route;
    }

    public void setRoute(Routes_Content route) {
        this.route = route;
    }
}
