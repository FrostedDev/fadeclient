package ca.fadeclient.mods.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class LogoButton extends GuiButton {

    public LogoButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }
}
