package serlvets;

import org.junit.Test;
import org.w3c.dom.Element;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class AuthCode {
    public static final int AUTHCODE_LENGTH = 4;        //验证码长度
    public static final int SINGLECODE_WIDTH = 15;    //单个验证码宽度
    public static final int SINGLECODE_HEIGHT = 24;    //单个验证码高度
    public static final int SINGLECODE_GAP = 4;        //单个验证码之间间隔
    public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
    public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;

    public static String getAuthCode() {
        String authCode = "";
        for (int i = 0; i < AUTHCODE_LENGTH; i++) {
            authCode += (new Random()).nextInt(10);
        }
        return authCode;
    }

    public static BufferedImage getAuthImg(String authCode) {
        //设置图片的高、宽、类型
        //RGB编码：red、green、blue
        BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        //得到图片上的一个画笔
        Graphics g = img.getGraphics();
        //设置画笔的颜色，用来做背景色
        g.setColor(Color.YELLOW);
        //用画笔来填充一个矩形，矩形的左上角坐标，宽，高
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
        //将画笔颜色设置为黑色，用来写字
        g.setColor(Color.BLACK);
        //设置字体：宋体、不带格式的、字号
        g.setFont(new Font("宋体", Font.PLAIN, SINGLECODE_HEIGHT + 5));

        //输出数字
        char c;
        for (int i = 0; i < authCode.toCharArray().length; i++) {
            //取到对应位置的字符
            c = authCode.charAt(i);
            //画出一个字符串：要画的内容，开始的位置，高度
            g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP / 2, IMG_HEIGHT);
        }
        Random random = new Random();
        //干扰素
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(IMG_WIDTH);
            int y = random.nextInt(IMG_HEIGHT);
            int x2 = random.nextInt(IMG_WIDTH);
            int y2 = random.nextInt(IMG_HEIGHT);
            g.drawLine(x, y, x + x2, y + y2);
        }
        return img;
    }

    public void getImg() {
        String code = "";
        int intCode = (new Random()).nextInt(9999);
        if (intCode < 1000) {
            intCode += 1000;
        }
        code += intCode;

        //设置图片的高、宽、类型
        //RGB编码：red、green、blue
        BufferedImage image = new BufferedImage(35, 14, BufferedImage.TYPE_INT_BGR);
        //得到图片上的一个画笔
        Graphics g = image.getGraphics();
        //设置画笔的颜色，用来做背景色
        g.setColor(Color.YELLOW);
        //用画笔来填充一个矩形，矩形的左上角坐标为（1,1），宽为33，高为12
        g.fillRect(1, 1, 33, 12);
        //将画笔颜色设置为黑色，用来写字
        g.setColor(Color.BLACK);
        //设置字体：宋体、不带格式的、字号为12
        g.setFont(new Font("宋体", Font.PLAIN, 12));

        //输出数字
        char c;
        for (int i = 0; i < code.toCharArray().length; i++) {
            //取到对应位置的字符
            c = code.charAt(i);
            //画出一个字符串：要画的内容，开始的位置，高度
            g.drawString(c + "", i * 7 + 4, 11);    //7为每个字的宽度，4为间隔
        }

        //显示或者保存
        //用输出流，文件流，文件名为“c:\\123.jpg”
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File("c:\\" + code + ".jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //JPG编码器
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        try {
//            //输送内容
//            encoder.encode(image);
//        } catch (ImageFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            saveAsJPEG(100,image, 100, (FileOutputStream) out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void saveAsJPEG(Integer dpi ,BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {
        ImageWriter imageWriter  =   ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios  =  ImageIO.createImageOutputStream(fos);
        imageWriter.setOutput(ios);
        IIOMetadata imageMetaData  =  imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);


        if(dpi !=  null && !dpi.equals("")){

            Element tree  =  (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
            Element jfif  =  (Element)tree.getElementsByTagName("app0JFIF").item(0);
            jfif.setAttribute("Xdensity", Integer.toString(dpi) );
            jfif.setAttribute("Ydensity", Integer.toString(dpi));

        }


        if(JPEGcompression >= 0 && JPEGcompression <= 1f){

            JPEGImageWriteParam jpegParams  =  (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            jpegParams.setCompressionQuality(JPEGcompression);

        }

        imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
        ios.close();
        imageWriter.dispose();

    }




@Test
public void test()
        {
        String code=this.getAuthCode();
        System.out.println(code);
        System.out.println(this.getAuthImg(code));

        }

        }