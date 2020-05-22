package ca.fadeclient.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ClientMainMenuButton extends GuiButton {

    private int baseColor;
    private int hoveredColor;
    private int currentColor;

    public ClientMainMenuButton(int buttonId, int x, int y, int widthIn, int heightIn, int baseColor, int hoveredColor, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.baseColor = baseColor;
        this.hoveredColor = hoveredColor;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {

        this.hovered = mouseX > this.xPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height && mouseY > this.yPosition;

        if(this.hovered){
            currentColor = hoveredColor;
        }else{
            currentColor = baseColor;
        }
        this.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, currentColor);
        this.drawRectHollow(GL11.GL_LINE_LOOP, this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(44, 0, 99).getRGB());
        GlStateManager.resetColor();
        GlStateManager.color(1, 1, 1, 1);
        if(this.hovered){
            mc.font20.drawCenteredString(EnumChatFormatting.DARK_PURPLE + this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 13) / 2, new Color(100, 100, 100).getRGB());
        }else{
            mc.font20.drawCenteredString(this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 13) / 2, new Color(255, 255, 255).getRGB());
        }
        GlStateManager.resetColor();
        GlStateManager.color(1, 1, 1, 1);
    }
}
