package com.mgo.models;

import java.text.SimpleDateFormat;

import com.mgo.entities.Booking;
import com.mgo.entities.Client;
import com.mgo.entities.Companyemployee;
import com.mgo.entities.Departure;
import com.mgo.entities.Departuremanifold;
import com.mgo.util.Strings;

import io.quarkus.qute.TemplateData;

@TemplateData
public class DeparturemanifoldPlaceInfo extends PlaceInfo {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Integer iddeparturemanifold;
    private String civility;
    private String fname;
    private String lname;
    private String address;
    private String idnumber;
    private String idissuedate;
    private String idissuelocation;
    private String idduplicatadate;
    private String idduplicatalocation;
    private String contactcivility;
    private String contactfname;
    private String contactlname;
    private String contactaddress;
    private String contactphone;
    private String contactphone1;
    private String daty;
    private Integer idtype;

    private String fullname;
    private String idfullinfo;
    private String idshortinfo;
    private String contactfullname;
    private String contactallphones;

    public DeparturemanifoldPlaceInfo() {
    }

    public DeparturemanifoldPlaceInfo(
            PlaceInfo placeInfo,
            Departuremanifold departuremanifold,
            Booking booking, Departure departure) {

        this.setLine(placeInfo.getLine());
        this.setColumn(placeInfo.getColumn());
        this.setX(placeInfo.getX());
        this.setY(placeInfo.getY());
        this.setPlace(placeInfo.getPlace());
        this.setOccuped(placeInfo.isOccuped());

        if (this.place == -2) {
            if (departure != null) {
                Companyemployee driver = departure.getDriver();
                // TODO : Driver 2
                if (driver != null) {
                    this.setFname(driver.getName());
                }
            }

        }

        else if (this.isOccuped) {

            if (departuremanifold != null) {
                this.setIddeparturemanifold(departuremanifold.getIddeparturemanifold());

                this.setCivility(departuremanifold.getCivility());
                this.setFname(departuremanifold.getFname());
                this.setLname(departuremanifold.getLname());
                this.setAddress(departuremanifold.getAddress());
                this.setIdnumber(departuremanifold.getIdnumber());
                this.setIdissuedate(
                        departuremanifold.getIdissuedate() != null
                                ? dateFormat.format(departuremanifold.getIdissuedate())
                                : "");
                this.setIdissuelocation(departuremanifold.getIdissuelocation());
                this.setIdduplicatadate(departuremanifold.getIdduplicatadate() != null
                        ? dateFormat.format(departuremanifold.getIdduplicatadate())
                        : "");
                this.setIdduplicatalocation(departuremanifold.getIdduplicatalocation());
                this.setContactcivility(departuremanifold.getContactcivility());
                this.setContactfname(departuremanifold.getContactfname());
                this.setContactlname(departuremanifold.getContactlname());
                this.setContactaddress(departuremanifold.getContactaddress());
                this.setContactphone(departuremanifold.getContactphone());
                this.setContactphone1(departuremanifold.getContactphone1());
                this.setDaty(departuremanifold.getDaty());
                this.setIdtype(
                        departuremanifold.getIdtype() != null ? departuremanifold.getIdtype().getIdidtype() : null);

            } else if (booking != null) {
                Client client = booking.getClient();
                if (client != null) {
                    this.setCivility(client.getCivility());
                    this.setFname(client.getFname());
                    this.setLname(client.getLname());
                }

            }

        }

        this.initIdFullInfo(
                departuremanifold != null && departuremanifold.getIdtype() != null
                        ? departuremanifold.getIdtype().getIdtypecol()
                        : null);
        this.initFullname();
        this.initContactFullname();
        this.initContactAllPhones();
    }

    private void initContactAllPhones() {
        this.contactallphones = concatStrings(this.contactphone, this.contactphone1, "/");
    }

    private void initFullname() {
        this.fullname = this.civility != null ? this.civility + " " + concatStrings(this.lname, this.fname, " ")
                : concatStrings(this.lname, this.fname, " ");
    }

    private void initContactFullname() {
        this.contactfullname = this.contactcivility != null
                ? this.contactcivility + " " + concatStrings(this.contactlname, this.contactfname, " ")
                : concatStrings(this.contactlname, this.contactfname, " ");

    }

    private void initIdFullInfo(
            String idTypeCol) {

        this.idfullinfo = "";
        if (!Strings.isEmptyOrNull(idTypeCol) && !Strings.isEmptyOrNull(this.idnumber)) {
            StringBuffer idFullinfoBuffer = new StringBuffer();

            this.idshortinfo = idTypeCol + " N° " + this.idnumber;

            idFullinfoBuffer.append(idTypeCol).append("N° ").append(this.idnumber).append(" ");

            if (!Strings.isEmptyOrNull(this.idissuelocation) || !Strings.isEmptyOrNull(this.idissuedate)) {
                idFullinfoBuffer.append("fait ");

                if (!Strings.isEmptyOrNull(this.idissuelocation)) {
                    idFullinfoBuffer.append("à ").append(this.idissuelocation).append(" ");
                }

                if (!Strings.isEmptyOrNull(this.idissuedate)) {
                    idFullinfoBuffer.append("le ").append(this.idissuedate).append(" ");
                }
            }

            if (!Strings.isEmptyOrNull(this.idduplicatalocation) || !Strings.isEmptyOrNull(this.idduplicatadate)) {
                idFullinfoBuffer.append("duplicata delivré ");

                if (!Strings.isEmptyOrNull(this.idduplicatalocation)) {
                    idFullinfoBuffer.append("à ").append(this.idduplicatalocation).append(" ");
                }

                if (!Strings.isEmptyOrNull(this.idduplicatadate)) {
                    idFullinfoBuffer.append("le ").append(this.idduplicatadate).append(" ");
                }
            }

            this.idfullinfo = idFullinfoBuffer.toString().trim();

        }

    }

    private static String concatStrings(String lname, String fname, String separator) {

        if (!Strings.isEmptyOrNull(lname) && !Strings.isEmptyOrNull(fname)) {
            return lname + separator + fname;
        }

        return Strings.isEmptyOrNull(lname) ? fname : lname;

    }

    public Integer getIddeparturemanifold() {
        return iddeparturemanifold;
    }

    public void setIddeparturemanifold(Integer iddeparturemanifold) {
        this.iddeparturemanifold = iddeparturemanifold;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getIdissuedate() {
        return idissuedate;
    }

    public void setIdissuedate(String idissuedate) {
        this.idissuedate = idissuedate;
    }

    public String getIdissuelocation() {
        return idissuelocation;
    }

    public void setIdissuelocation(String idissuelocation) {
        this.idissuelocation = idissuelocation;
    }

    public String getIdduplicatadate() {
        return idduplicatadate;
    }

    public void setIdduplicatadate(String idduplicatadate) {
        this.idduplicatadate = idduplicatadate;
    }

    public String getIdduplicatalocation() {
        return idduplicatalocation;
    }

    public void setIdduplicatalocation(String idduplicatalocation) {
        this.idduplicatalocation = idduplicatalocation;
    }

    public String getContactcivility() {
        return contactcivility;
    }

    public void setContactcivility(String contactcivility) {
        this.contactcivility = contactcivility;
    }

    public String getContactfname() {
        return contactfname;
    }

    public void setContactfname(String contactfname) {
        this.contactfname = contactfname;
    }

    public String getContactlname() {
        return contactlname;
    }

    public void setContactlname(String contactlname) {
        this.contactlname = contactlname;
    }

    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getContactphone1() {
        return contactphone1;
    }

    public void setContactphone1(String contactphone1) {
        this.contactphone1 = contactphone1;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }

    public Integer getIdtype() {
        return idtype;
    }

    public void setIdtype(Integer idtype) {
        this.idtype = idtype;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdfullinfo() {
        return idfullinfo;
    }

    public void setIdfullinfo(String idfullinfo) {
        this.idfullinfo = idfullinfo;
    }

    public String getContactfullname() {
        return contactfullname;
    }

    public void setContactfullname(String contactfullname) {
        this.contactfullname = contactfullname;
    }

    public String getContactallphones() {
        return contactallphones;
    }

    public void setContactallphones(String contactallphones) {
        this.contactallphones = contactallphones;
    }

    public String getIdshortinfo() {
        return idshortinfo;
    }

    public void setIdshortinfo(String idshortinfo) {
        this.idshortinfo = idshortinfo;
    }

    


    
}
