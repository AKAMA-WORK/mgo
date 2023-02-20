package com.mgo.generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.FontMetrics;

public class Generator {
    private int width;
    private int height;

    private Graphics2D g2d;
    private Style defaultStyle;
    private int nextY;
    private BufferedImage resultBufferedImage;

    private Style style;
    private boolean enableAutoNewLine = true;

    private String BREAK_WORD_SEPARATOR = " ";
    private int availableWidth;

    public Generator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Generator start() {

        resultBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        g2d = resultBufferedImage.createGraphics();
        this.defaultStyle = new Style();
        this.defaultStyle.setFont(g2d.getFont())
                .setUnderlined(false).setColor(Color.BLACK)
                .setAlignment(Alignment.CENTER);
        this.style = this.defaultStyle;

        this.availableWidth = this.width - this.style.getMarginLeft() - this.style.getMarginRight();

        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        nextY = this.style.getMarginTop();

        return this;
    }

    public Generator verticalSpacing(int spacing) {
        nextY += spacing;

        return this;
    }

    public Generator imageFromUrl(String url) throws MalformedURLException, IOException {
        BufferedImage img = ImageIO.read(new URL(url));

        int imgX = (width - img.getWidth()) / 2;
        g2d.drawImage(img, imgX, nextY, null);

        this.nextLine(img.getHeight());

        return this;
    }

    public Generator text(String fullText) {

        this.applyStyle();

        FontMetrics fm = g2d.getFontMetrics();
        List<String> words = this.brokenWords(fullText, fm);

        int size = words.size();

        int x = this.style.getMarginLeft();
        int textHeight = fm.getHeight() * size;

        this.applyVerticalAlign(textHeight);
        System.out.println(fullText+" >>> "+size+" > "+words);

        for (int i = 0; i < size; i++) {
            String text = words.get(i);
            int textWidth = fm.stringWidth(text);
            if (this.style.getAlignment() == Alignment.CENTER) {
                x = ((width - textWidth) / 2);
            } else if (this.style.getAlignment() == Alignment.RIGHT) {
                x = width - textWidth - this.style.getMarginRight();
            }

            g2d.drawString(text, x, this.nextY);

            if (i < size - 1) {
                this.nextY += fm.getHeight();
            }

            if (this.style.isUnderlined()) {
                int underLineY = this.nextY + 2;
                g2d.drawLine(x, underLineY, x + textWidth, underLineY);

            }

        }

        this.nextLine(fm.getHeight());

        return this;
    }

    private List<String> brokenWords(String text, FontMetrics fm) {

        if (!text.contains(BREAK_WORD_SEPARATOR)) {
            return Collections.singletonList(text);
        }

        int textWidth = fm.stringWidth(text);

        if (this.availableWidth >= textWidth) {
            return Collections.singletonList(text);
        }

        String[] tokens = text.split(BREAK_WORD_SEPARATOR);
        int spaceWidth = fm.stringWidth(BREAK_WORD_SEPARATOR);

        List<Integer> wordsBreakIndices = new ArrayList<>();
        int currentWidth = 0;
        for (int i = 0; i < tokens.length; i++) {
            int currentTokenWidth = fm.stringWidth(tokens[i]);
            currentWidth += (currentTokenWidth + spaceWidth);
            if (currentWidth > this.availableWidth) {
                wordsBreakIndices.add(i == 0 ? 0 : i - 1);
                currentWidth = currentTokenWidth;
            }
        }

        if (wordsBreakIndices.isEmpty()) {
            return Collections.singletonList(text);
        }

        System.out.println(text + wordsBreakIndices+" # "+Arrays.toString(tokens));

        List<String> words = new ArrayList<>();
        int size = wordsBreakIndices.size();
        for (int i = 0; i < size; i++) {
            String[] subsTokens;
            if (i == 0) {
                subsTokens = Arrays.copyOfRange(tokens, 0, wordsBreakIndices.get(i));
            } else {
                subsTokens = Arrays.copyOfRange(tokens, wordsBreakIndices.get(i-1), wordsBreakIndices.get(i));
            }

            System.out.println("Subs : "+tokens.length+" "+Arrays.toString(subsTokens));

            words.add(String.join(BREAK_WORD_SEPARATOR, subsTokens));
        }

        words.add(String.join(BREAK_WORD_SEPARATOR,
                Arrays.copyOfRange(tokens, wordsBreakIndices.get(size - 1), tokens.length)));

        return words;

    }

    public BufferedImage end() {
        this.g2d.dispose();

        return resultBufferedImage;

    }

    public Generator enableNextNewLine() {
        this.enableAutoNewLine = true;
        return this;
    }

    public Generator disableNextNewLine() {
        this.enableAutoNewLine = false;
        return this;
    }

    public Generator resetStyle() {
        this.style(this.defaultStyle);
        return this;
    }

    public Generator style(Style style) {
        if (style.getFont() == null) {
            style.setFont(this.defaultStyle.getFont());
        }
        if (style.getColor() == null) {
            style.setColor(this.defaultStyle.getColor());
        }

        

        this.style = style;

        return this;
    }

    private void applyStyle() {
        g2d.setFont(this.style.getFont());
        g2d.setColor(this.style.getColor());

        this.availableWidth = this.width - this.style.getMarginLeft() - this.style.getMarginRight();

    }

    private void applyVerticalAlign(int elementHeight) {
        if (this.style.getVerticalAlignment() == VerticalAlignment.BOTTOM) {
            this.nextY = this.height - elementHeight - this.style.getMarginBottom();
        } else if (this.style.getVerticalAlignment() == VerticalAlignment.MIDDLE) {
            this.nextY = this.height / 2 - elementHeight / 2;
        } else if (this.style.getVerticalAlignment() == VerticalAlignment.TOP) {
            this.nextY = this.style.getMarginTop();
        }

    }

    private void nextLine(int elementHeight) {
        if (this.enableAutoNewLine) {
            this.nextY += elementHeight;
        }
    }

}
