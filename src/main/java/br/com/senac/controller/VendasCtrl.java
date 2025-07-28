package br.com.senac.controller;

import br.com.senac.model.RegistroVenda;
import br.com.senac.model.dto.RegistroDTO;
import br.com.senac.persistence.RegistroDAO;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class VendasCtrl {
    private final RegistroDAO registroDAO;
    
    public VendasCtrl(){
        this.registroDAO = new RegistroDAO();
    }
    
    public List<RegistroDTO> listarVendas(){
        return ListParaDTO(registroDAO.listar());
    }
    
    public void excluirVenda(Integer id){
        registroDAO.excluir(id);
    }
    
    private List<RegistroDTO> ListParaDTO(List<RegistroVenda> vendas){
        return vendas.stream().map(venda -> new RegistroDTO(
            venda.getId(),
            venda.getNomeCliente(),
            venda.getCpfCliente(),
            venda.getTipoSeguro(),
            new SimpleDateFormat("dd/MM/yyyy").format(venda.getDataVenda()),
            venda.getValorApolice(),
            venda.isFiel()
        )).collect(Collectors.toList());
    }
    
    public RegistroDTO buscarVendaId(Integer id){
        RegistroVenda venda = registroDAO.buscarId(id);
        
        if(venda == null){
            throw new IllegalArgumentException("Registro não encontrado para o ID: " + id);
        }
        
        RegistroDTO dto = new RegistroDTO();
        dto.setId(venda.getId());
        dto.setNomeCliente(venda.getNomeCliente() != null ? venda.getNomeCliente() : "");
        dto.setCpfCliente(venda.getCpfCliente() != null ? venda.getCpfCliente() : "");
        dto.setTipoSeguro(venda.getTipoSeguro() != null ? venda.getTipoSeguro() : "Carro");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dto.setDataVenda(venda.getDataVenda() != null ? sdf.format(venda.getDataVenda()) : "");

        dto.setValorApolice(venda.getValorApolice());
        dto.setFiel(venda.isFiel());
        
        return dto;
    }
}
