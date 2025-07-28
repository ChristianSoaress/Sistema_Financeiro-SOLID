package br.com.senac.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "registro")
public class RegistroVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String nomeCliente;
    
    @Column(nullable = false)
    private String cpfCliente;
    
    @Column(nullable = false)
    private String tipoSeguro;
    
    @Column(nullable = false)
    private Date dataVenda;
    
    @Column(nullable = false)
    private double valorApolice;
    
    @Column(nullable = false)
    private boolean fiel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorApolice() {
        return valorApolice;
    }

    public void setValorApolice(double valorApolice) {
        this.valorApolice = valorApolice;
    }

    public boolean isFiel() {
        return fiel;
    }

    public void setFiel(boolean fiel) {
        this.fiel = fiel;
    }
}