package br.com.senac.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistroDTO {
    private Integer id = 0;
    private String nomeCliente = "";
    private String cpfCliente = "";
    private String tipoSeguro = "Carro";
    private String dataVenda = "";
    private double valorApolice = 0.0;
    private boolean fiel = false;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    
    public RegistroDTO() {}

    public RegistroDTO(Integer id, String nomeCliente, String cpfCliente, String tipoSeguro, String dataVenda, double valorApolice, boolean fiel) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.tipoSeguro = tipoSeguro;
        this.dataVenda = dataVenda;
        this.valorApolice = valorApolice;
        this.fiel = fiel;
    }

    public Integer getId() {
        return this.id != null ? this.id : 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNomeCliente() {
        return this.nomeCliente != null ? this.nomeCliente : "";
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

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        try {
            if (dataVenda != null && !dataVenda.isEmpty()) {
                SDF.parse(dataVenda);
            }
            this.dataVenda = dataVenda;
        } 
        catch (ParseException e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy");
        }
    }

    public double getValorApolice() {
        return valorApolice;
    }

    public void setValorApolice(double valorApolice) {
        this.valorApolice = valorApolice;
    }

    public boolean isFiel() {
        return this.fiel;
    }

    public void setFiel(boolean fiel) {
        this.fiel = fiel;
    }
}
