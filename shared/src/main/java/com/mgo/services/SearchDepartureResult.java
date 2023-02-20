package com.mgo.services;

import java.util.Collection;
import java.util.Date;


public class SearchDepartureResult {
    //private Collection<DepartureAdapter> departures;
    private Date dateStart;
    private Date dateEnd;

    public SearchDepartureResult() {
    }

    /*public SearchDepartureResult(Collection<DepartureAdapter> departures, Date dateStart, Date dateEnd) {
        this.departures = departures;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }*/

   /* public Collection<DepartureAdapter> getDepartures() {
        return departures;
    }

    public void setDepartures(Collection<DepartureAdapter> departures) {
        this.departures = departures;
    }
*/
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
