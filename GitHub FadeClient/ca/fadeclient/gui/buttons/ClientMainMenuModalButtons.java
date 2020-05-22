package ca.fadeclient.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ClientMainMenuModalButtons extends GuiButton {

    private ResourceLocation rl;

    public ClientMainMenuModalButtons(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, ResourceLocation rl) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.rl = rl;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {

        this.hovered = mouseX > this.xPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height && mouseY > this.yPosition;

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        mc.getTextureManager().bindTexture(rl);
        this.drawModalRectWithCustomSizedTexture(this.xPosition, this.yPosition, 0, 0, this.width, this.height, this.width, this.height);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

    }
}
