/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Angie_
 */
@Entity
@Table(name = "angyusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Angyusers.findAll", query = "SELECT a FROM Angyusers a"),
    @NamedQuery(name = "Angyusers.findByIdUser", query = "SELECT a FROM Angyusers a WHERE a.idUser = :idUser"),
    @NamedQuery(name = "Angyusers.findByUsuario", query = "SELECT a FROM Angyusers a WHERE a.usuario = :usuario"),
    @NamedQuery(name = "Angyusers.findByContras", query = "SELECT a FROM Angyusers a WHERE a.contras = :contras")})
public class Angyusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "contras")
    private String contras;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Pointreg> pointregCollection;

    public Angyusers() {
    }

    public Angyusers(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContras() {
        return contras;
    }

    public void setContras(String contras) {
        this.contras = contras;
    }

    @XmlTransient
    public Collection<Pointreg> getPointregCollection() {
        return pointregCollection;
    }

    public void setPointregCollection(Collection<Pointreg> pointregCollection) {
        this.pointregCollection = pointregCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Angyusers)) {
            return false;
        }
        Angyusers other = (Angyusers) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "servlets.Angyusers[ idUser=" + idUser + " ]";
    }

}
