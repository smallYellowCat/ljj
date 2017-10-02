package com.hzdz.ls.common;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {

    /**
     * 图片压缩
     * @param path
     * @param savePath
     * @return
     */
    public static String compress(String path, String savePath){
        return null;
    }

    /**
     * 图片打水印，文字
     * @param path
     * @param frameModePath
     * @return
     */
    public static String addFrame(String path, String frameModePath){
        String imageUrl = "";
        try {
            BufferedImage image = ImageIO.read(new File(path));
            Graphics2D g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            //shadowGraphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            FontRenderContext frc = g2.getFontRenderContext();

            TextLayout tl = new TextLayout("网址：", new Font("宋体", Font.PLAIN,50), frc);
            Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(5,50));
            g2.setStroke(new BasicStroke(2.0f));
            g2.setColor(Color.WHITE);
            g2.draw(sha);
            g2.setColor(Color.BLACK);
            g2.fill(sha);
            g2.dispose();
            ImageIO.write(image,"jpg", new FileOutputStream(new File("E:/bb.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两张图片进行合并
     * @param image1
     * @param image2
     * @return
     */
    public static String mergeImage(String image1, String image2) throws IOException {
        BufferedImage bi1 = ImageIO.read(new File(image1));
        BufferedImage bi2 = ImageIO.read(new File(image2));
        Graphics2D g1 = (Graphics2D) bi1.getGraphics();

        int startX = (bi1.getWidth() - bi2.getWidth()) / 2;
        int startY = (bi1.getHeight() - bi2.getHeight()) / 2;

        g1.drawImage(bi2, startX, startY,  bi2.getWidth(), bi2.getHeight(), null);
        g1.dispose();
        ImageIO.write(bi1, "jpg", new FileOutputStream(new File("E://cc.jpg")));
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        //自动生成main
        mergeImage("E:/IntelijIdea_workspace/ljj/target/ljj/upload/manager/bbb/20170929145354535.jpg",
                "E://dd.jpg");
        //equalRatioScale("E:/IntelijIdea_workspace/ljj/target/ljj/upload/manager/bbb/20170929145354535.jpg", 50, 100);
    }

    /**
     * 图片等比例缩放
     * @param path
     * @return
     */
    public static String equalRatioScale(String path, int tw, int th) throws IOException {


        BufferedImage bi = ImageIO.read(new File(path));
        BufferedImage target = null;
        int type = bi.getType();
        double sx = tw / 1.0 / bi.getWidth();
        double sy = th / 1.0 / bi.getHeight();

        if (sx < sy){
            sx = sy;
            tw = (int) (sx * bi.getWidth());
        }else {
            sy = sx;
            th = (int) (sy * bi.getHeight());
        }

        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = bi.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(tw, th);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(tw, th, type);
            Graphics2D g = target.createGraphics();
            // smoother than exlax:
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.drawRenderedImage(bi, AffineTransform.getScaleInstance(sx, sy));
            g.dispose();
        }
        ImageIO.write(target, "jpg", new FileOutputStream(new File("E://dd.jpg")));
        return null;
    }

}
