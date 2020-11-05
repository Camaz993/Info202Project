/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAOException;
import dao.ProductData;
import dao.daoManager;
import domain.Product;
import gui.helpers.SimpleListModel;
import gui.helpers.ValidationHelper;
import java.awt.Window;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author mazca993
 */
public class ProductEditor extends javax.swing.JDialog {
    private ProductData dao ; 
    //static ProductList dao = new ProductList();
    private SimpleListModel myModel = new SimpleListModel();
    private Product p = new Product();
    private ValidationHelper validHelp = new ValidationHelper();
    
    /**
     * Creates new form ProductEditor
     * @param parent
     * @param modal
     */
    public ProductEditor(Window parent, boolean modal, ProductData dao ) {
        super(parent);
        super.setModal(modal);
        this.dao = dao;
        initComponents();   
        txtCategory.setEditable(true);
        myModel.updateItems(dao.getCategories());
        txtCategory.setModel(myModel);
        
        // add a formatter to the price text field
        validHelp.addTypeFormatter(txtPrice, "#0.00", BigDecimal.class);
        validHelp.addTypeFormatter(txtStock, "#0", Integer.class);
        validHelp.addTypeFormatter(txtID, "#0", Integer.class);
        
        txtID.setName("txtID");
        txtName.setName("txtName");
        txtDescription.setName("txtDescription");
        txtPrice.setName("txtPrice");
        txtStock.setName("txtStock");
        txtCategory.setName("txtCategory");
        SaveButton.setName("saveButton");
        this.setName("productView");
    }
    
    public ProductEditor(Window parent, boolean modal, Product productToEdit,ProductData dao) {
        // call other constructor
        this(parent, modal, dao);
        // assign the product we are editing to the product field
        this.p = productToEdit;
        
        txtID.setValue(productToEdit.getProduct_Id());
        txtName.setText(productToEdit.getName());
        txtPrice.setValue(productToEdit.getList_price());
        txtStock.setValue(productToEdit.getStock());
        txtCategory.setSelectedItem(String.valueOf(productToEdit.getCategories()));
        txtDescription.setText(productToEdit.getDescription());
        
        txtID.setEditable(false);
		  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      txtName = new javax.swing.JTextField();
      jLabel3 = new javax.swing.JLabel();
      scrollPane = new javax.swing.JScrollPane();
      txtDescription = new javax.swing.JTextArea();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jLabel6 = new javax.swing.JLabel();
      SaveButton = new javax.swing.JButton();
      CancelButton = new javax.swing.JButton();
      txtCategory = new javax.swing.JComboBox<>();
      txtID = new javax.swing.JFormattedTextField();
      txtPrice = new javax.swing.JFormattedTextField();
      txtStock = new javax.swing.JFormattedTextField();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      jLabel1.setText("Product ID");

      jLabel2.setText("Name");

      jLabel3.setText("Description");

      txtDescription.setColumns(20);
      txtDescription.setRows(5);
      scrollPane.setViewportView(txtDescription);

      jLabel4.setText("Category");

      jLabel5.setText("Price");

      jLabel6.setText("Quantity In Stock");

      SaveButton.setText("Save");
      SaveButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            SaveButtonActionPerformed(evt);
         }
      });

      CancelButton.setText("Cancel");
      CancelButton.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            CancelButtonActionPerformed(evt);
         }
      });

      txtCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      txtCategory.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtCategoryActionPerformed(evt);
         }
      });

      txtPrice.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtPriceActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(SaveButton)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(CancelButton))
               .addComponent(txtName)
               .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
               .addComponent(txtCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(txtID)
               .addComponent(txtPrice)
               .addComponent(txtStock))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel2)
               .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel3)
               .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(7, 7, 7)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel4)
               .addComponent(txtCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel5)
               .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel6)
               .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(SaveButton)
               .addComponent(CancelButton))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        try {
        Integer id = ((Integer)txtID.getValue()) ;
        String name = txtName.getText();
        BigDecimal price = ((BigDecimal)txtPrice.getValue());
        Integer stock = ((Integer)txtStock.getValue());
        String item = (String)txtCategory.getSelectedItem();
        String description = txtDescription.getText();    
        

       // myModel.updateItems(dao.getCategories());
       // txtCategory.setModel(myModel);
        
        p.setProduct_Id(id);
        p.setName(name);
        p.setDescription(description);
        p.setCategories(item);
        p.setList_price(price);
        p.setStock(stock);
       // String getCategory = p.getCategories();       
        
        //int result = JOptionPane.showMessageDialog(this, "You Cannot Add this Product");
        if(id != null){
        if (dao.searchID(id) != null && txtID.isEditable()) {
            JOptionPane.showMessageDialog(this, "Product ID Already Exists"); 
        }else{
            if(validHelp.isObjectValid(p)){
                 dao.add(p);
                 dispose();
                }
            }
        }
        else{
           JOptionPane.showMessageDialog(this, "Product ID Must Be Entered"); 
        }
        }
        catch(DAOException exception){
            JOptionPane.showMessageDialog(this, exception, "Data Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void txtCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryActionPerformed

   private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_txtPriceActionPerformed



   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton CancelButton;
   private javax.swing.JButton SaveButton;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JScrollPane scrollPane;
   private javax.swing.JComboBox<String> txtCategory;
   private javax.swing.JTextArea txtDescription;
   private javax.swing.JFormattedTextField txtID;
   private javax.swing.JTextField txtName;
   private javax.swing.JFormattedTextField txtPrice;
   private javax.swing.JFormattedTextField txtStock;
   // End of variables declaration//GEN-END:variables
}
