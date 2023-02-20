package com.mgo.generator;

import java.awt.Font;
import java.awt.Color;

public class Style {
    private Font font;
    private boolean underlined = false;
    private Color color = Color.BLACK;
    private Alignment alignment = Alignment.CENTER;
    private VerticalAlignment verticalAlignment = VerticalAlignment.AUTO;
    private int marginLeft = 8;
    private int marginRight = 8;
    private int marginTop = 8;
    private int marginBottom = 8;

    public Font getFont() {
        return font;
    }

    public Style setFont(Font font) {
        this.font = font;

        return this;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public Style setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Style setColor(Color color) {
        this.color = color;
        return this;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Style setAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public Style setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public Style setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public Style setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;

        return this;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public Style setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public Style setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }


    
}
