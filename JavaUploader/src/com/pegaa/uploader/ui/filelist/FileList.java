/*
 * FileList.java
 *
 * Created on 12 Haziran 2008 Perşembe, 21:12
 */
package com.pegaa.uploader.ui.filelist;

import com.pegaa.uploader.common.CustomGridLayout;
import com.pegaa.uploader.ui.fileselection.EmptyListMessage;
import com.pegaa.uploader.common.StackLayout;
import com.pegaa.uploader.config.ConfigHolder;
import com.pegaa.uploader.event.FileListingListener;
import com.pegaa.uploader.ui.filelist.item.ListItemUI;
import java.io.File;
import javax.swing.JPanel;

/**
 *
 * @author  tayfun
 */
public class FileList extends javax.swing.JPanel implements FileListingListener {

    private JPanel fileList = null;
    private EmptyListMessage emptyListMessage = null;
    private StackLayout layout = null;
    private ConfigHolder configHolder = null;
    /* actual file lister object, find files and adds them to the UI
     * 
     */
    private FileLister fileLister = null;
    /* Loads thumb images of original images if mode is image mode */
    //private ThumbLoaderThread thumbLoaderThread = null;
    private boolean canList = true;

    /** Creates new form ListContainer */
    public FileList() {
        initComponents();
        updateComponents();
    }

    /**
     *      Sets the internal configHolder, we use this function to pass configHolder
     * because Netbeans IDE does not let you to use gui components in visual editor 
     * which has different constructors. At least i could not find any other way.
     * 
     * @param configHolder
     */
    public void setConfigHolder(ConfigHolder configHolder) {
        this.configHolder = configHolder;
        this.fileLister = new FileLister(this.configHolder, this.fileList, this);
        initFileList();
    }

    private void initFileList() {
        updateComponents();
        updateStrings();
        initListeners();
    }

    private void initListeners() {
        this.fileLister.addFileListingListener(this);
    }

    private void updateComponents() {
        this.layout = new StackLayout();

        this.setLayout(this.layout);

        this.fileList = new JPanel();
        fileList.setLayout(new CustomGridLayout(165, 145));

        this.add(this.fileList);

        this.emptyListMessage = new EmptyListMessage();
        this.add(this.emptyListMessage);

        this.layout.showComponent(this.emptyListMessage, this);

    }

    /**
     *     Updates the strings provided by Lan object
     */
    private void updateStrings() {
        this.emptyListMessage.setText("", "");
    }

    /**
     *  Selects all files from the active directory
     * 
     */
    public void selectAll() {
        this.fileLister.selectAll();
    }

    /**
     *  Removes all selected files
     */
    public void removeAllSelected() {
        this.fileLister.removeAllSelected();
    }

    /**
     *      Request to list directory
     * 
     * @param dir
     */
    public void listDirectory(File dir) {
        this.fileList.removeAll();
        this.fileLister.listDirectory(dir);
    }

    /**
     *      is currently available for list dir.
     * 
     * @return
     */
    public boolean isCanList() {
        return this.canList;
    }

    /**
     * Handler of directory listing finished event.
     * 
     * @param count listed file count
     */
    public void listingFinished(int filecount) {
        this.canList = true;
        if (filecount == 0) {
            this.layout.showComponent(this.emptyListMessage, this);
        } else {
            this.layout.showComponent(fileList, this);
        }
        this.fileList.revalidate();
        this.fileList.repaint();
    }

    /**
     *  Handler of directory listing started event
     */
    public void listingStarted() {
        this.canList = false;
    }

    /**
     *     Invoked when a file's itemUI added by the lister thread.
     * 
     * @param c
     */
    public void listItemAdded(ListItemUI c) {
        this.fileList.add(c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(1)
            //.addGap(0, 515, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(1)
            //.addGap(0, 293, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
