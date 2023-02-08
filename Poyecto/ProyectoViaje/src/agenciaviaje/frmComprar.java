/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package agenciaviaje;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class frmComprar extends javax.swing.JInternalFrame {

    ResultSet rs;
    PreparedStatement pr;
    Connection cx;
    Conexion con = new Conexion();
    frmFactura factu = null;
    frmInvitado invitado = new frmInvitado();
    frmAgregarUsuario usuario = new frmAgregarUsuario();

    public frmComprar() {
        initComponents();
        this.setLocation(30, 35);
        cx = con.ConexionDB();
       
        Cargar2(cmbHoteles, "IDHotel", "Hotel");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
     public void CompletarTabla(JTable tbl,String IDSucursal) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            tbl.setModel(modelo);
            tbl.setEnabled(false);

            tbl.setRowSelectionAllowed(true);
            PreparedStatement ps = null;
            switch (IDSucursal) {
                case "Viaje Estelar":
               IDSucursal="1";   
                    break;
                case "Explorando el Mundo":
               IDSucursal="2";   
                    break;
                case "Viaja Feliz":
                IDSucursal="3";       
                    break;
                case "Viajeros por el mundo":
                IDSucursal="4";     
                    break;
                case "Viviendo Fantasias":
                IDSucursal="6";       
                    break;
            }
            
            String sql = "SELECT Vuelo.IDVuelo,Vuelo.CostoPasaje,Vuelo.FechaDia,Vuelo.FechaMes,Vuelo.FechaHora,Vuelo.Origen,Vuelo.Destino FROM Vuelo "
                    + "INNER JOIN Sucursal ON Vuelo.IDVuelo=IDVuelo where IDVuelo = "+cmbVuelos.getSelectedItem().toString()+" GROUP BY (Vuelo.IDVuelo)";

            pr = cx.prepareStatement(sql);
            rs = pr.executeQuery();
            

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Costo");
            modelo.addColumn("Dia");
            modelo.addColumn("Mes");
            modelo.addColumn("Hora");
            modelo.addColumn("Origen");
            modelo.addColumn("Destino");

            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error"+ex);
        }
    }
     private void Cargar(JComboBox combo, String Campo, String Tabla,String Sucursal) {
        combo.removeAllItems();
        switch (Sucursal) {
            case "Viaje Estelar":
                Sucursal = "1";
                break;
            case "Explorando el Mundo":

                Sucursal= "2";
                break;
            case "Viaja Feliz":
                Sucursal = "3";
                break;
            case "Viajeros por el mundo":
                Sucursal= "4";
                break;
            case "Viviendo Fantasias":
                Sucursal = "6";
                break;
        }
        try {
            Statement leer = cx.createStatement();
            rs = leer.executeQuery("select " + Campo + " from " + Tabla + " WHERE IDSucursal=" + Sucursal + ";");
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Error en la consulta" + ex);
        }

    }
     public void CompletarTabla2(JTable tbl,String IDSucursal) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            tbl.setModel(modelo);
            tbl.setEnabled(false);

            tbl.setRowSelectionAllowed(true);
            PreparedStatement ps = null;
            switch (IDSucursal) {
                case "Viaje Estelar":
               IDSucursal="1";   
                    break;
                case "Explorando el Mundo":

                 IDSucursal="2";   
                    break;
                case "Viaja Feliz":
                IDSucursal="3";       
                    break;
                case "Viajeros por el mundo":
                IDSucursal="4";     
                    break;
                case "Viviendo Fantasias":
                IDSucursal="6";       
                    break;
            }
            
            String sql = "SELECT Hotel.IDHotel,Hotel.Nombre,Hotel.Direccion,Hotel.Ciudad,Hotel.Telefono,Hotel.NumHabitaciones,Hotel.TipoHabitaciones,Hotel.Precios FROM Hotel "
                    + "INNER JOIN Sucursal ON Hotel.IDSucursal=IDSucursal WHERE Hotel.IDHotel='"+cmbHoteles.getSelectedItem().toString()+"' GROUP BY (Hotel.IDHotel) ";

            pr = cx.prepareStatement(sql);
            rs = pr.executeQuery();
            

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Direccion");
            modelo.addColumn("Ciudad");
            modelo.addColumn("Telefono");
            modelo.addColumn("Numero de Habitaciones");
            modelo.addColumn("Tipo Habitacion");
            modelo.addColumn("Precio");
            String[] factura = null ;
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
              

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error"+ex);
        }
       
      
    }
     public String Conseguir(String Campo,String IDTurista,String Tabla){
        String campo=""; 
         try{
                Statement leer = cx.createStatement();
                rs = leer.executeQuery("SELECT "+Campo+ " FROM "+Tabla+";");
                while (rs.next()) {
                   campo=rs.getString(1);
                }
            
            }catch(SQLException ex){
                System.out.println("Error en la consulta"+ ex);
            }
            return campo;  
                }
      public String ConseguirID(String Campo,String IDTurista,String Tabla){
        String campo=""; 
         try{
                Statement leer = cx.createStatement();
                rs = leer.executeQuery("SELECT "+Campo+ " FROM "+Tabla+" WHERE IDVuelo="+cmbVuelos.getSelectedItem());
                while (rs.next()) {
                   campo=rs.getString(1);
                }
            
            }catch(SQLException ex){
                System.out.println("Error en la consulta"+ ex);
            }
            return campo;  
                }
       public String ConseguirHotel(String Campo,String IDTurista,String Tabla){
        String campo=""; 
         try{
                Statement leer = cx.createStatement();
                rs = leer.executeQuery("SELECT "+Campo+ " FROM "+Tabla+" WHERE IDHotel="+cmbHoteles.getSelectedItem());
                while (rs.next()) {
                   campo=rs.getString(1);
                }
            
            }catch(SQLException ex){
                System.out.println("Error en la consulta"+ ex);
            }
            return campo;  
                }

    private void Cargar2(JComboBox combo, String Campo, String Tabla) {
        combo.removeAllItems();
        try {
            Statement leer = cx.createStatement();
            rs = leer.executeQuery("select " + Campo + " from " + Tabla + ";");
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Error en la consulta" + ex);
        }

    }
    private String[] Comprar1(){
        String Factura []={cmbClase.getSelectedItem().toString(),
        txtNHabitaciones.getText(),
        cmbRegimen.getSelectedItem().toString(),
        Conseguir("Nombre",cmbVuelos.getSelectedItem().toString(),"Turista"),
        Conseguir("Apellido",cmbVuelos.getSelectedItem().toString(),"Turista"),
        Conseguir("Direccion",cmbVuelos.getSelectedItem().toString(),"Turista"),
        Conseguir("Telefono",cmbVuelos.getSelectedItem().toString(),"Turista"),
        Conseguir("FechaNacimiento",cmbVuelos.getSelectedItem().toString(),"Turista"),
        ConseguirID("IDVuelo",cmbHoteles.getSelectedItem().toString(),"Vuelo"),
        ConseguirID("CostoPasaje",cmbHoteles.getSelectedItem().toString(),"Vuelo"),
        ConseguirHotel("Nombre",cmbHoteles.getSelectedItem().toString(),"Hotel"),
        ConseguirHotel("Precios",cmbHoteles.getSelectedItem().toString(),"Hotel")
        };
        
        JOptionPane.showMessageDialog(null, Factura);
       return Factura;
    }
    


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbVuelos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTlf = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btnBuscarHotel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHotel = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVuelo = new javax.swing.JTable();
        btnComprar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cmbSucursal = new javax.swing.JComboBox<>();
        btnElegir = new javax.swing.JButton();
        cmbHoteles = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbClase = new javax.swing.JComboBox<>();
        txtNHabitaciones = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbRegimen = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("COMPRAR ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imangenes/page_website.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 730, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 80));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cmbVuelos.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setText("Sucursal");

        jLabel4.setText("Nº Habitaciones");

        txtTlf.setBorder(null);

        btnBuscarHotel.setText("Buscar");
        btnBuscarHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHotelActionPerformed(evt);
            }
        });

        tblHotel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblHotel);

        tblVuelo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblVuelo);

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        jLabel5.setText("ID VUELO");

        cmbSucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Viaje Estelar", "Explorando el Mundo", "Viaja Feliz", "Viajeros por el mundo", "Viviendo fantasias" }));

        btnElegir.setText("Elegir");
        btnElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirActionPerformed(evt);
            }
        });

        cmbHoteles.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setText("ID HOTEL");

        jLabel7.setText("Clase");

        cmbClase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Turista", "Primera" }));

        jLabel8.setText("REGIMEN");

        cmbRegimen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media pago", "Pago completo" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbHoteles, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbVuelos, javax.swing.GroupLayout.Alignment.LEADING, 0, 118, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jLabel3))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(cmbRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(99, 99, 99))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnBuscarHotel, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(65, 65, 65)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnElegir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator4)
                    .addComponent(txtTlf, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnElegir))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbVuelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbHoteles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(btnBuscarHotel)))
                        .addGap(35, 35, 35)
                        .addComponent(btnComprar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 970, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHotelActionPerformed
        CompletarTabla(tblVuelo,cmbVuelos.getSelectedItem().toString());
        CompletarTabla2(tblHotel, cmbHoteles.getSelectedItem().toString());
    }//GEN-LAST:event_btnBuscarHotelActionPerformed

    private void btnElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirActionPerformed
         Cargar(cmbVuelos, "IDVuelo", "Vuelo",cmbSucursal.getSelectedItem().toString());
    }//GEN-LAST:event_btnElegirActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        Comprar1();
       
    }//GEN-LAST:event_btnComprarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarHotel;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnElegir;
    private javax.swing.JComboBox<String> cmbClase;
    private javax.swing.JComboBox<String> cmbHoteles;
    private javax.swing.JComboBox<String> cmbRegimen;
    private javax.swing.JComboBox<String> cmbSucursal;
    private javax.swing.JComboBox<String> cmbVuelos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tblHotel;
    private javax.swing.JTable tblVuelo;
    private javax.swing.JTextField txtNHabitaciones;
    private javax.swing.JTextField txtTlf;
    // End of variables declaration//GEN-END:variables
}