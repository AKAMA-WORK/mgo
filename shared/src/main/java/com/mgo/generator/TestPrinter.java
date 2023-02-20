package com.mgo.generator;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mgo.util.BarCodes;

import java.awt.image.BufferedImage;

public class TestPrinter {
    public static byte[] test() throws IOException {

        PrinterOptions p = new PrinterOptions();

        p.resetAll();
        p.initialize();
        p.feedBack((byte) 2);
        p.color(0);
        p.alignCenter();
        p.setText("The Dum Dum Name");
        p.newLine();
        p.setText("Restaurant Dining");
        p.newLine();
        p.addLineSeperator();
        p.setText("Bling Bling");
        p.newLine();
        p.addLineSeperator();
        p.newLine();

        p.alignLeft();
        p.setText("POD No \t: 2001 \tTable \t: E511");
        p.newLine();

        p.setText("Res Date \t: " + "01/01/1801 22:59");

        p.newLine();
        p.setText("Session \t: Evening Session");
        p.newLine();
        p.setText("Staff \t: Bum Dale");
        p.newLine();
        p.addLineSeperator();
        p.newLine();
        p.alignCenter();
        p.setText(" - Some Items - ");
        p.newLine();
        p.alignLeft();
        p.addLineSeperator();

        p.newLine();

        p.setText("No \tItem\t\tUnit\tQty");
        p.newLine();
        p.addLineSeperator();
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");
        p.setText("1" + "\t" + "Aliens Everywhere" + "\t" + "Rats" + "\t" + "500");

        p.addLineSeperator();
        p.feed((byte) 3);
        p.finit();

        ByteArrayInputStream bais = new ByteArrayInputStream(p.finalCommandSet().getBytes());
        BufferedImage image = ImageIO.read(bais);

        return BarCodes.bufferedImage2ByteArray(image, "png");

    }
}
