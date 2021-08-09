/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge
 */
@Entity
@Table(name = "cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByNumerodecuenta", query = "SELECT c FROM Cuenta c WHERE c.numerodecuenta = :numerodecuenta")
    , @NamedQuery(name = "Cuenta.findByUsername", query = "SELECT c FROM Cuenta c WHERE c.username = :username")
    , @NamedQuery(name = "Cuenta.findByNip", query = "SELECT c FROM Cuenta c WHERE c.nip = :nip")
    , @NamedQuery(name = "Cuenta.findBySaldo", query = "SELECT c FROM Cuenta c WHERE c.saldo = :saldo")
    , @NamedQuery(name = "Cuenta.findByNipNumerodecuenta", query = "SELECT c FROM Cuenta c WHERE c.numerodecuenta = :numerodecuenta and c.nip =:nip")
    , @NamedQuery(name = "Cuenta.updateSaldo", query = "UPDATE  Cuenta c SET c.saldo = :saldo WHERE c.numerodecuenta = :numerodecuenta")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerodecuenta")
    private Integer numerodecuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "nip")
    private String nip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private float saldo;

    public Cuenta() {
    }
    
    public Cuenta(Integer numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public Cuenta(Integer numerodecuenta, String username, String nip, float saldo) {
        this.numerodecuenta = numerodecuenta;
        this.username = username;
        this.nip = nip;
        this.saldo = saldo;
    }

    public Integer getNumerodecuenta() {
        return numerodecuenta;
    }

    public void setNumerodecuenta(Integer numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerodecuenta != null ? numerodecuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.numerodecuenta == null && other.numerodecuenta != null) || (this.numerodecuenta != null && !this.numerodecuenta.equals(other.numerodecuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Cuenta[ numerodecuenta=" + numerodecuenta + " ]";
    }
    
}
