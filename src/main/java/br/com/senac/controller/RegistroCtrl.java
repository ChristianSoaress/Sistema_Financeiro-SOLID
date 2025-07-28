package br.com.senac.controller;

import br.com.senac.model.RegistroVenda;
import br.com.senac.model.dto.RegistroDTO;
import br.com.senac.persistence.RegistroDAO;
import br.com.senac.service.FidelidadeServ;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegistroCtrl {
    private final RegistroDAO registroDAO;
    private final FidelidadeServ fidelidadeServ;
    
    public RegistroCtrl(RegistroDAO registroDAO, FidelidadeServ fidelidadeServ){
        this.registroDAO = new RegistroDAO();
        this.fidelidadeServ = new FidelidadeServ();
    }
    
    public void salvarRegistro(RegistroDTO dto){
        try{
            RegistroVenda registro = DTOparaEnt(dto);
            
            if(dto.isFiel()){
                double valorDesconto = fidelidadeServ.calcularValor(
                        dto.getTipoSeguro(), 
                        dto.getValorApolice(), 
                        true);
                registro.setValorApolice(valorDesconto);
            }
            
            if(dto.getId() == null){
                registroDAO.adicionar(registro);
            }
            else{
                registroDAO.atualizar(registro);
            }
        }
        catch(Exception e){
            throw new RuntimeException("Erro ao salvar registro: " + e.getMessage(), e);
        }
    }
    
    public RegistroDTO buscarPorId(Integer id) {
        RegistroVenda registro = registroDAO.buscarId(id);
        return EntparaDTO(registro);
    }
    
    private RegistroVenda DTOparaEnt(RegistroDTO dto) throws ParseException{
        RegistroVenda registro = new RegistroVenda();
        registro.setId(dto.getId());
        registro.setNomeCliente(dto.getNomeCliente());
        registro.setCpfCliente(dto.getCpfCliente());
        registro.setTipoSeguro(dto.getTipoSeguro());
        
        if (dto.getDataVenda() != null && !dto.getDataVenda().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            registro.setDataVenda(new java.sql.Date(sdf.parse(dto.getDataVenda()).getTime()));
        }
        
        registro.setValorApolice(dto.getValorApolice());
        registro.setFiel(dto.isFiel());
        
        return registro;
    }
    
    private RegistroDTO EntparaDTO(RegistroVenda registro){
        if(registro == null) return new RegistroDTO();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = registro.getDataVenda() != null 
            ? sdf.format(registro.getDataVenda()) 
            : "";
        
        return new RegistroDTO(
            registro.getId(),
            registro.getNomeCliente(),
            registro.getCpfCliente(),
            registro.getTipoSeguro(),
            dataFormatada,
            registro.getValorApolice(),
            registro.isFiel());
    }
}
