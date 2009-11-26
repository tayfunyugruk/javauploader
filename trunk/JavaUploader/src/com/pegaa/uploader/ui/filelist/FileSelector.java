/*
 * FileSelector.java
 *
 * Created on 15 Haziran 2008 Pazar, 13:33
 */

package com.pegaa.uploader.ui.filelist;

import com.pegaa.uploader.config.ConfigHolder;
import com.pegaa.uploader.lang.Lang;
import com.yugruk.chooser.DirectorySelectionListener;
import java.awt.Dimension;
import java.io.File;


/**
 *
 * @author  tayfun
 */
public class FileSelector extends javax.swing.JPanel {
    
    private ConfigHolder configHolder = null;
    
    /** Creates new form FileSelector */
    public FileSelector() {
       initComponents();
    }
       
    public void setConfigHolder(ConfigHolder configHolder)
    {
        this.configHolder = configHolder;
        initFileSelector();
        updateStrings();
        initDirectoryChooser();
    }
        
    private void updateStrings()
    {
        Lang lang = (Lang)this.configHolder.getObject("global.lang");
        this.buttonSelectAll.setText(lang.get("fileselector.selectall"));
        this.buttonRemoveSelected.setText(lang.get("fileselector.removeselected"));
    }
    
    private void initFileSelector()
    {
         this.fileList1.setConfigHolder(configHolder);
         initSelectedFileCountBar();
         initEventListeners();       
    }

    /**
     * If file upload limit is set we should show the indicator bar.
     */
    private void initSelectedFileCountBar()
    {
         if(this.configHolder.getObject("global.fileUploadLimit") != null)
         {
             SelectedFileCountBar sfcd = new SelectedFileCountBar();
             sfcd.setConfigHolder(configHolder);
             jPanel2.add(sfcd, java.awt.BorderLayout.PAGE_END);
             jPanel2.setPreferredSize(new Dimension(120, 80));
             sfcd.setPreferredSize(new Dimension(120, 30));
             sfcd.setVisible(true);
         }
    }

    private void initDirectoryChooser()
    {
        this.directoryTree1.setConfigHolder(configHolder);
    }
    
    private void initEventListeners()
    {
        this.directoryTree1.addDirectorySelectionListener(new DirectorySelectionListener(){

            public void directorySelected(File dir) {
                    FileSelector.this.listDirectory(dir);
            }
            
        }); 
    }
    
    /**
     *  Requests fileList to list directory.
     * 
     * @param dir
     */
    private void listDirectory(File dir)
    {
        if(this.fileList1.isCanList()){
            this.jScrollPane2.getHorizontalScrollBar().setValue(0);
            this.jScrollPane2.getVerticalScrollBar().setValue(0);
            this.fileList1.listDirectory(dir);
        }
    }
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        directoryTree1 = new com.yugruk.chooser.DirectoryTree();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileList1 = new com.pegaa.uploader.ui.filelist.FileList();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        buttonSelectAll = new javax.swing.JButton();
        buttonRemoveSelected = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(200);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(275, 275));
        jScrollPane1.setViewportView(directoryTree1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setViewportView(fileList1);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.setMaximumSize(new java.awt.Dimension(100, 50));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(100, 19));
        jPanel3.setPreferredSize(new java.awt.Dimension(120, 40));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        buttonSelectAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSelectAllMouseClicked(evt);
            }
        });
        jPanel3.add(buttonSelectAll);

        buttonRemoveSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonRemoveSelectedMouseClicked(evt);
            }
        });
        jPanel3.add(buttonRemoveSelected);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jSplitPane1.setRightComponent(jPanel1);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSelectAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSelectAllMouseClicked
        this.fileList1.selectAll();
}//GEN-LAST:event_buttonSelectAllMouseClicked

    private void buttonRemoveSelectedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRemoveSelectedMouseClicked
        this.fileList1.removeAllSelected();
}//GEN-LAST:event_buttonRemoveSelectedMouseClicked
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRemoveSelected;
    private javax.swing.JButton buttonSelectAll;
    private com.yugruk.chooser.DirectoryTree directoryTree1;
    private com.pegaa.uploader.ui.filelist.FileList fileList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
    
}
