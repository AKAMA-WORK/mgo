package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "vehicle")
public class Vehicle extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehicle")
    private Integer idvehicle;
    @JoinColumn(name = "vehicleplace", referencedColumnName = "idvehicleplace")
    @ManyToOne
    private Vehicleplace vehicleplace;
    @JoinColumn(name = "vehicletype", referencedColumnName = "idvehicletype")
    @ManyToOne
    private Vehicletype vehicletype;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private Collection<Vehiclemappingplace> vehiclemappingplaceCollection;
    @OneToMany(mappedBy = "vehicle")
    private Collection<Departure> departureCollection;

    public Vehicle() {
    }

    public Vehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    public Integer getIdvehicle() {
        return idvehicle;
    }

    public void setIdvehicle(Integer idvehicle) {
        this.idvehicle = idvehicle;
    }

    public Vehicleplace getVehicleplace() {
        return vehicleplace;
    }

    public void setVehicleplace(Vehicleplace vehicleplace) {
        this.vehicleplace = vehicleplace;
    }

    public Vehicletype getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(Vehicletype vehicletype) {
        this.vehicletype = vehicletype;
    }

    public Collection<Vehiclemappingplace> getVehiclemappingplaceCollection() {
        return vehiclemappingplaceCollection;
    }

    public void setVehiclemappingplaceCollection(Collection<Vehiclemappingplace> vehiclemappingplaceCollection) {
        this.vehiclemappingplaceCollection = vehiclemappingplaceCollection;
    }

    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehicle != null ? idvehicle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.idvehicle == null && other.idvehicle != null) || (this.idvehicle != null && !this.idvehicle.equals(other.idvehicle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Vehicle[ idvehicle=" + idvehicle + " ]";
    }
    
}
