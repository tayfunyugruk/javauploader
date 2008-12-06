/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegaa.uploader.config.policy;

import com.pegaa.uploader.config.ConfigHolder;
import com.pegaa.uploader.common.CustomFileFilter;
import com.pegaa.uploader.sender.InputStreamInfo;
import com.pegaa.uploader.tools.CustomLog;
import com.pegaa.uploader.tools.ImageFuncs;
import com.pegaa.uploader.ui.filelist.item.ImageItem;
import com.pegaa.uploader.ui.filelist.item.ListItem;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * This class is the default policy which treats all items as images, so we can
 * apply compression, make items resized before uploading.
 * 
 * @author tayfun
 */
public class ImageUploadPolicy extends UploadPolicy{

    /* ImageUpload  policy type flag */
    public static final int POLICY_TYPE_IMG = 1;
    
    public ImageUploadPolicy(ConfigHolder configHolder)
    {
        super (configHolder);
    }
    
    /**
     *   Creates an InputStream object and returns it. Before creating an 
     * InputStream checks if max image size given, image compression quality 
     * given.
     * @param f
     * @return
     */
    @Override
    public InputStreamInfo getInputStream(ListItem item) 
                           throws FileNotFoundException 
    {
        
        BufferedImage image = null;
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream bais = null;
        InputStreamInfo info = null;
        
        ImageItem imgItem = (ImageItem)item;
        
        try {
            image = javax.imageio.ImageIO.read(item.getFile());
            image = getScaledImageAndRotated(image, imgItem.getRotationStatus());
            baos = ImageFuncs.createImageOutputStream(image);
            byte[] resultImageAsRawBytes = baos.toByteArray();
            bais = new ByteArrayInputStream(resultImageAsRawBytes, 0, resultImageAsRawBytes.length);
            
            info = new InputStreamInfo((InputStream)bais, resultImageAsRawBytes.length);
            /**/
            return info;
        } catch (Exception ex) {
            ex.printStackTrace();  
            return null;
        }finally{
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    /**
     * Default image format is JPG. If you want to use other formats
     * you must set <b>fileExtensions</b> applet parameter with desired
     * formats separated by comma like <i>jpeg,png,gif</i>
     * @return
     */
    @Override
    public FileFilter getFileFilter() 
    {        
         if(filter != null){
             return filter;
         }
         
         filter = new CustomFileFilter();
         String fileExtensions = applet.getParameter("fileExtensions");
         
         if(fileExtensions != null){
             
            String[] extensions = fileExtensions.split(",");
            for(int i=0; i<20; i++){
                filter.addExtension(extensions[i]);
            }
            
         }else{
            filter.addExtension("jpg");
            filter.addExtension("jpeg");
         }
         
         return filter;
    } 
    
   
    /**
     *      Returns rotated and scaled (according to parameters) image of given
     * BufferedImage
     * 
     * @param orgImage
     * @return
     */
    private BufferedImage getScaledImageAndRotated(BufferedImage orgImage, int rotation)
    {
        int maxWidth = orgImage.getWidth();
        int maxHeight = orgImage.getHeight();
        
        String maxWidthStr = this.applet.getParameter("maxWidth");
        String maxHeightStr = this.applet.getParameter("maxHeight");
        
        CustomLog.log("ImageUploadPolicy.getScaledImageAndRotated maxWidthStr = " + maxWidthStr);
        CustomLog.log("ImageUploadPolicy.getScaledImageAndRotated maxHeightStr = " + maxHeightStr);
        CustomLog.log("ImageUploadPolicy.getScaledImageAndRotated rotation = " + rotation);
        
        
        if(maxWidthStr != null){
            try{
                maxWidth = Integer.parseInt(maxWidthStr);
                if(maxWidth <= 0){
                    maxWidth = orgImage.getWidth();
                }
            }catch(Exception e){
                maxWidth = orgImage.getWidth();
            }
        }
        if(maxHeightStr != null){
            try{
                maxHeight = Integer.parseInt(maxHeightStr);
                if(maxHeight <= 0){
                    maxHeight = orgImage.getHeight();
                }
            }catch(Exception e){
                maxHeight = orgImage.getHeight();
            }            
        }
        
        return ImageFuncs.getScaledAndRotatedImage(orgImage, maxWidth, maxHeight, rotation, false);
    }
    
    @Override
    public int getPolicyType()
    {
        return POLICY_TYPE_IMG;
    }   
    
    /*
    @Override
    public String getPostURL(String targetID)
    {
        String targetHandlerUrl = applet.getParameter("targetURL");
        String targetHandlerName = applet.getParameter("targetName");
        if(targetID == null || targetID.equals("")){
            return targetHandlerUrl;
        }else if(targetHandlerName != null){
            return targetHandlerUrl + "?" + targetHandlerName + "=" + targetID;
        }
        else{
            return targetHandlerUrl + "?target=" + targetID;
        }
    }
    */
}
