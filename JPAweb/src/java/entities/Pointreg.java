/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Angie_
 */
@Entity
@Table(name = "pointreg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pointreg.findAll", query = "SELECT p FROM Pointreg p"),
    @NamedQuery(name = "Pointreg.findByIdPointreg", query = "SELECT p FROM Pointreg p WHERE p.idPointreg = :idPointreg"),
    @NamedQuery(name = "Pointreg.findByPuntuacion", query = "SELECT p FROM Pointreg p WHERE p.puntuacion = :puntuacion"),
    @NamedQuery(name = "Pointreg.findByFechaIni", query = "SELECT p FROM Pointreg p WHERE p.fechaIni = :fechaIni"),
    @NamedQuery(name = "Pointreg.findByFechaFin", query = "SELECT p FROM Pointreg p WHERE p.fechaFin = :fechaFin")})
public class Pointreg implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pointreg")
    private Integer idPointreg;
    @Column(name = "puntuacion")
    private Integer puntuacion;
    @Column(name = "fecha_ini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIni;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private Angyusers idUser;

    public Pointreg() {
    }

    public Pointreg(Integer idPointreg) {
        this.idPointreg = idPointreg;
    }

    public Integer getIdPointreg() {
        return idPointreg;
    }

    public void setIdPointreg(Integer idPointreg) {
        this.idPointreg = idPointreg;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Angyusers getIdUser() {
        return idUser;
    }

    public void setIdUser(Angyusers idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPointreg != null ? idPointreg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pointreg)) {
            return false;
        }
        Pointreg other = (Pointreg) object;
        if ((this.idPointreg == null && other.idPointreg != null) || (this.idPointreg != null && !this.idPointreg.equals(other.idPointreg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "servlets.Pointreg[ idPointreg=" + idPointreg + " ]";
    }

}
