/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SelectedFileCountDisplayer.java
 *
 * Created on 22.Kas.2009, 00:54:20
 */

package com.pegaa.uploader.ui.filelist;

import com.pegaa.uploader.config.ConfigHolder;
import com.pegaa.uploader.event.SelectedFileListListener;
import com.pegaa.uploader.ui.filelist.item.ListItem;
import com.pegaa.uploader.ui.selectedfilelist.SelectedFileListModel;
import java.awt.Color;

/**
 *
 * @author typhoon
 */
public class SelectedFileCountBar extends javax.swing.JPanel implements SelectedFileListListener{

    private ConfigHolder configHolder = null;

    /** Creates new form SelectedFileCountDisplayer */
    public SelectedFileCountBar() {
        initComponents();
    }

    public void setConfigHolder(ConfigHolder configHolder)
    {
        this.configHolder = configHolder;

        /* if there is a file upload limit show the max */
        String max = (String)this.configHolder.getObject("global.fileUploadLimit");
        if(max != null)
        {
            this.jProgressBar1.setMaximum(Integer.parseInt(max));
            this.jProgressBar1.setValue(0);
            this.textArea.setText("0 file(s) / max : " + max);
            SelectedFileListModel sflm = (SelectedFileListModel)this.configHolder.getObject("global.selected-file-list-model");
            sflm.addSelectedFileListListener(this);
        }
    }

    public void setLabel(String s)
    {
        this.textArea.setText(s);
    }

    public void fileAdded(ListItem f) {
        int max = this.jProgressBar1.getMaximum();
        int currentVal = this.jProgressBar1.getValue();
        this.jProgressBar1.setValue(currentVal + 1);
        if((currentVal + 1) == max){
            this.jProgressBar1.setForeground(Color.RED);
        }
        this.textArea.setText((currentVal + 1) + " file(s) / max : " + max);
    }

    public void fileRemoved(ListItem f) {
        jProgressBar1.setForeground(java.awt.Color.green);
        int max = this.jProgressBar1.getMaximum();
        int currentVal = this.jProgressBar1.getValue();
        this.jProgressBar1.setValue(currentVal - 1);
        this.textArea.setText((currentVal - 1) + " file(s) / max : " + max);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        textArea = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jProgressBar1.setForeground(java.awt.Color.green);
        jProgressBar1.setValue(50);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 10));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(150, 40));

        textArea.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(textArea, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel textArea;
    // End of variables declaration//GEN-END:variables



}
