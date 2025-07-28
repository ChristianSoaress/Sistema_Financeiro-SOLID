package br.com.senac.view;

import br.com.senac.controller.RegistroCtrl;
import br.com.senac.model.Usuario;
import br.com.senac.model.dto.RegistroDTO;
import br.com.senac.persistence.RegistroDAO;
import br.com.senac.service.FidelidadeServ;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;

public class TelaRegistro extends javax.swing.JFrame {

    private final RegistroCtrl controller;
    private final Usuario usuarioAutenticado;
    private boolean modoEdicao;
    private Integer idEdicao;

    public TelaRegistro(Usuario usuarioAutenticado, RegistroDTO registro) {
        RegistroDAO registroDAO = new RegistroDAO();
        FidelidadeServ fidelidadeServ = new FidelidadeServ();
        this.controller = new RegistroCtrl(registroDAO, fidelidadeServ);
        this.usuarioAutenticado = usuarioAutenticado;
        initComponents();
        
        if(registro != null){
            this.modoEdicao = true;
            this.idEdicao = registro.getId();
            carregarDados(registro);
        }
        else{
            this.modoEdicao = false;
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja voltar para o menu principal?", "Voltar", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    MenuPrincipal menuPrincipal = new MenuPrincipal(usuarioAutenticado);
                    menuPrincipal.setVisible(true);
                    dispose();
                }
            }
        });
        
        
    }
    
    public TelaRegistro(Usuario usuarioAutenticado){
        this(usuarioAutenticado, null);
    }

    private void carregarDados(RegistroDTO registro) {
        try {
            txtNomeCliente.setText(registro.getNomeCliente() != null ? registro.getNomeCliente() : "");
            txtCPF.setText(registro.getCpfCliente() != null ? registro.getCpfCliente() : "");
            txtDataVenda.setText(registro.getDataVenda() != null ? registro.getDataVenda() : "");
            DecimalFormat df = new DecimalFormat("0.00");
            df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
            df.setGroupingUsed(false);
            txtValorApolice.setText(df.format(registro.getValorApolice()));
            boolean encontrado = false;
            for (int i = 0; i < cbxTipoSeguro.getItemCount(); i++) {
                if (cbxTipoSeguro.getItemAt(i).toString().equalsIgnoreCase(registro.getTipoSeguro())) {
                    cbxTipoSeguro.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado && cbxTipoSeguro.getItemCount() > 0) {
                cbxTipoSeguro.setSelectedIndex(0);
            }
            
            rbtnSim.setSelected(registro.isFiel());
            rbtnNao.setSelected(!registro.isFiel());
        } 
        catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNomeCliente.setText("");
        txtCPF.setText("");
        txtDataVenda.setText("");
        txtValorApolice.setText("");
        rbtnSim.setSelected(false);
        rbtnNao.setSelected(false);
    }

    private void validarCampos() {
        StringBuilder erros = new StringBuilder();

        if (txtNomeCliente.getText().trim().isEmpty()) {
            erros.append("- Nome do cliente é obrigatório.\n");
        }
        String cpf = txtCPF.getText().replaceAll("[^0-9]", "");
        if (cpf.isEmpty() || cpf.length() != 11) {
            erros.append("- CPF inválido.\n");
        }
        if (txtDataVenda.getText().trim().isEmpty()) {
            erros.append("- Data da venda é obrigatória.\n");
        }
        if(txtValorApolice.getText().trim().isEmpty()){
            erros.append("- Valor da apólice é obrigatório.\n");
        }
        if (!rbtnSim.isSelected() && !rbtnNao.isSelected()) {
            erros.append("- Cliente é fiel?\n");
        }

        if (!txtValorApolice.getText().trim().isEmpty()) {
            try {
                double valor = Double.parseDouble(txtValorApolice.getText().trim());
                if (valor <= 0) {
                    erros.append("- Valor da apólice deve ser positivo.\n");
                }
            } 
            catch (NumberFormatException e) {
                erros.append("- Valor inválido (use números com pontos para decimal como 100.00).\n");
            }
        }

        if (erros.length() > 0) {
            throw new IllegalArgumentException("Erros encontrados:\n" + erros.toString());
        }
    }
    
    private double converterValor(String valorStr){
        try{
            return Double.parseDouble(valorStr.replace(",", "."));
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Valor inválido. Use números com ponto decimal (Ex: 68450.00).\n");
        }
    }
    
    private RegistroDTO criarDTO(double valor){
        RegistroDTO dto = new RegistroDTO();
        dto.setNomeCliente(txtNomeCliente.getText().trim());
        dto.setCpfCliente(txtCPF.getText().trim());
        dto.setTipoSeguro((String) cbxTipoSeguro.getSelectedItem());
        dto.setDataVenda(txtDataVenda.getText().trim());
        dto.setValorApolice(valor);
        dto.setFiel(rbtnSim.isSelected());
        return dto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtngFiel = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataVenda = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxTipoSeguro = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtValorApolice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rbtnSim = new javax.swing.JRadioButton();
        rbtnNao = new javax.swing.JRadioButton();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Registro de Venda");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 7, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Nome do Cliente:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 88, -1, -1));

        txtNomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(txtNomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 83, 225, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("CPF do Cliente:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 142, -1, -1));

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(txtCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 137, 225, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Data da Venda:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 196, -1, -1));

        txtDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtDataVenda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(txtDataVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 191, 225, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Tipo de Seguro:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(104, 250, -1, -1));

        cbxTipoSeguro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbxTipoSeguro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carro", "Casa", "Vida" }));
        jPanel1.add(cbxTipoSeguro, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 245, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Valor da Apólice:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 304, -1, -1));

        txtValorApolice.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel1.add(txtValorApolice, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 299, 225, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Cliente Fiel?");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 353, -1, -1));

        rbtngFiel.add(rbtnSim);
        rbtnSim.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rbtnSim.setText("Sim");
        jPanel1.add(rbtnSim, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 397, -1, -1));

        rbtngFiel.add(rbtnNao);
        rbtnNao.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rbtnNao.setText("Não");
        jPanel1.add(rbtnNao, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 397, -1, -1));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(409, 391, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            validarCampos();
            
            double valor = converterValor(txtValorApolice.getText());
            
            RegistroDTO dto = criarDTO(valor);
            
            if(modoEdicao){
                dto.setId(idEdicao);
                controller.salvarRegistro(dto);
            }
            else{
                controller.salvarRegistro(dto);
            }

            JOptionPane.showMessageDialog(
                    this,
                    modoEdicao ? "Registro Atualizado com Sucesso." : "Registro Salvo com Sucesso.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            if (modoEdicao) {
                new TelaListaVendas(usuarioAutenticado).setVisible(true);
                dispose();
            }
            else{
                limparCampos();
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    this, 
                    "Formato de valor inválido: " + e.getMessage() + "\n" + 
                    "Use (exemplo) 1500,00 ou 1500.00.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ocorreu um erro: " + e.getMessage(),
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbxTipoSeguro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbtnNao;
    private javax.swing.JRadioButton rbtnSim;
    private javax.swing.ButtonGroup rbtngFiel;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JFormattedTextField txtDataVenda;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtValorApolice;
    // End of variables declaration//GEN-END:variables
}
