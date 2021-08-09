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
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByNumerodecuenta", query = "SELECT r FROM Registro r WHERE r.numerodecuenta = :numerodecuenta")
    , @NamedQuery(name = "Registro.findByNip", query = "SELECT r FROM Registro r WHERE r.nip = :nip")
    , @NamedQuery(name = "Registro.findByUso", query = "SELECT r FROM Registro r WHERE r.uso = :uso")
    , @NamedQuery(name = "Registro.findBySaldo", query = "SELECT r FROM Registro r WHERE r.saldo = :saldo")
    , @NamedQuery(name = "Registro.findByNipNumerodecuenta", query = "SELECT r FROM Registro r WHERE r.numerodecuenta = :numerodecuenta and r.nip =:nip")
    , @NamedQuery(name = "Registro.updateSaldo", query = "UPDATE  Registro r SET r.saldo = :saldo WHERE r.numerodecuenta = :numerodecuenta")
    , @NamedQuery(name = "Registro.updateUso", query = "UPDATE  Registro r SET r.uso = true WHERE r.numerodecuenta = :numerodecuenta")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numerodecuenta")
    private Integer numerodecuenta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "nip")
    private String nip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uso")
    private boolean uso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private float saldo;

    public Registro() {
    }

    public Registro(Integer numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public Registro(Integer numerodecuenta, String nip, boolean uso, float saldo) {
        this.numerodecuenta = numerodecuenta;
        this.nip = nip;
        this.uso = uso;
        this.saldo = saldo;
    }

    public Integer getNumerodecuenta() {
        return numerodecuenta;
    }

    public void setNumerodecuenta(Integer numerodecuenta) {
        this.numerodecuenta = numerodecuenta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public boolean getUso() {
        return uso;
    }

    public void setUso(boolean uso) {
        this.uso = uso;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
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
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.numerodecuenta == null && other.numerodecuenta != null) || (this.numerodecuenta != null && !this.numerodecuenta.equals(other.numerodecuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Registro[ numerodecuenta=" + numerodecuenta + " ]";
    }
    
}
