/*
 * Trabalhodesenvolvido para disciplina de ISS - 2016
 */
package visao;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author i7
 */
public class ViewGerarPDf extends javax.swing.JFrame {

    /**
     * Creates new form ViewGerarPDf
     */
    public ViewGerarPDf( ) {
        try {
            this.m = Class.forName("com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewGerarPDf.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
    }
    
    Class m;
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GerarPDF");
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/2016310201648211836_pdf-icon.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Gerar PDF");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton2)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        Document document = new Document(); 

        try {
            PdfWriter.getInstance(document, new FileOutputStream("relatorio.pdf"));

            document.open();
            document.add(new Paragraph("Relatório Sibi"+"\n\n"));
            PdfPTable tabela = new PdfPTable(3);
            PdfPCell cabecalho  = new PdfPCell(new Paragraph ("Materiais bibliograficos no inventario:"));
            cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
            cabecalho.setBorder(PdfPCell.NO_BORDER);
            cabecalho.setBackgroundColor(new BaseColor(100, 150, 200));
            cabecalho.setColspan(3);
            tabela.addCell(cabecalho);
            tabela.addCell("ISBN");
            tabela.addCell("Nome");
            tabela.addCell("Quantidade no inventário");
            document.add(tabela);
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sibi?zeroDateTimeBehavior=convertToNull", "root", "4874524");
            Statement stmt = conn.createStatement();
            ResultSet  query_set = stmt.executeQuery("SELECT id, nome , quantidadeInventario FROM Material");
            
            PdfPCell tabelaCelula;
            
        
            while (query_set.next()) {                
                                /*String id = query_set.getString("id");
                                tabelaCelula = new PdfPCell(new Phrase(id));
                                tabela.addCell(tabelaCelula);
                                
                                String nome = query_set.getString("nome ");
                                tabelaCelula = new PdfPCell(new Phrase(nome));
                                tabela.addCell(tabelaCelula);
                                
                                String quantidadeInventario = query_set.getString("quantidadeInventario");
                                tabelaCelula = new PdfPCell(new Phrase(quantidadeInventario));
                                tabela.addCell(tabelaCelula);*/
                                
                                tabela.addCell(query_set.getString("id"));
                                tabela.addCell(query_set.getString("nome"));
                                tabela.addCell(query_set.getString("quantidadeInventario"));
                                
            }
            
            document.add(tabela);
            
                    
        } catch (DocumentException | FileNotFoundException | SQLException ex) {
            System.err.println("Error: " + ex);
        }finally{
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("relatorio.pdf"));
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewGerarPDf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewGerarPDf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewGerarPDf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewGerarPDf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewGerarPDf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
