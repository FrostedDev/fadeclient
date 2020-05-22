package ca.fadeclient.mods.draggable;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;

public class CheckButtons extends GuiButton {

    public String textButton = "";
    public boolean test;
    public int fade = 0;

    public CheckButtons(int buttonId, int x, int y, int widthIn, int heightIn, String s, boolean test)
    {
        super(buttonId, x, y, widthIn, heightIn, s);
        this.textButton = s;
        this.test = test;
    }

    public void drawButton(Minecraft mc, int i1, int i2)
    {
        this.hovered = i1 >= this.xPosition && i2 >= this.yPosition && i1 < this.xPosition + this.width && i2 < this.yPosition + this.height;
        this.mouseDragged(mc, i1, i2);

        ScaledResolution sr = new ScaledResolution(mc);

        if(test) {
            if(fade < 9) {
                fade++;
            }
            drawRegularPolygon(this.xPosition + 4, this.yPosition + this.height / 2, 5, 100, new Color(100, 200, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 7, this.yPosition + this.height / 2, 5, 100, new Color(100, 200, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 10, this.yPosition + this.height / 2, 5, 100, new Color(100, 200, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 13, this.yPosition + this.height / 2, 5, 100, new Color(100, 200, 100).getRGB(), 180);
            drawHalfCircle(this.xPosition + 4 + fade, this.yPosition + this.height / 2, 5, 20, new Color(0, 200, 0).getRGB(), 180);
            drawHalfCircle(this.xPosition + 4 + fade, this.yPosition + this.height / 2, 5, 20, new Color(0, 200, 0).getRGB(), 360);
        }else {
            if(fade > 0) {
                fade--;
            }
            drawRegularPolygon(this.xPosition + 4, this.yPosition + this.height / 2, 5, 100, new Color(200, 100, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 7, this.yPosition + this.height / 2, 5, 100, new Color(200, 100, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 10, this.yPosition + this.height / 2, 5, 100, new Color(200, 100, 100).getRGB(), 180);
            drawRegularPolygon(this.xPosition + 13, this.yPosition + this.height / 2, 5, 100, new Color(200, 100, 100).getRGB(), 180);
            drawHalfCircle(this.xPosition + 4 + fade, this.yPosition + this.height / 2, 5, 20, new Color(200, 0, 0).getRGB(), 180);
            drawHalfCircle(this.xPosition + 4 + fade, this.yPosition + this.height / 2, 5, 20, new Color(200, 0, 0).getRGB(), 360);
        }
        drawRegularPolygon(this.xPosition + 4 + fade, this.yPosition + this.height / 2, 5, 100, new Color(100, 100, 100).getRGB(), 180);
    }
}
