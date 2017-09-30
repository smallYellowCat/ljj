package com.hzdz.ls.common;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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
     * 图片添加边框
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

}
