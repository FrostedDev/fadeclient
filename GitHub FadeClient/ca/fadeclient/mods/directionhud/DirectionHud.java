package ca.fadeclient.mods.directionhud;

import ca.fadeclient.mods.draggable.HUDConfigScreen;
import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DirectionHud extends ModDraggable {

    GuiTextField field;

    ScreenPosition pos = ScreenPosition.fromRelativePosition(1, 1);

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 9;
    }

    @Override
    public void render(ScreenPosition pos) {
        drawCompass(pos.getAbsoluteX(), pos.getAbsoluteY());
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        ScaledResolution sr = new ScaledResolution(mc);
        drawCompass(pos.getAbsoluteX(), pos.getAbsoluteY());
    }

    public void drawCompass(int posX, int posY)
    {
        ScaledResolution sr = new ScaledResolution(this.mc);
        GlStateManager.pushMatrix();
        new Gui().drawVerticalLine(posX + this.getWidth() / 2, posY - 1, posY + 11, new Color(0xCB0202).getRGB());
        GlStateManager.color(1, 1, 1);
        GlStateManager.popMatrix();
        int j = sr.getScaledWidth() / 2;
        int k = (int) MathHelper.wrapAngleTo180_float(this.mc.thePlayer.rotationYaw) * -1 - 359;
        int k2 = (int)MathHelper.wrapAngleTo180_float(this.mc.thePlayer.rotationYaw) * -1 - 381;
        int l = 45;
        for (int i1 = 0; i1 <= 2; ++i1) {
            for (double d0 = 0.0D; d0 <= 3.5D; d0 += 0.5D) {
                if (j + k > j - 48 && j + k < j + 48) {
                    int j1 = 0;
                    j1 += 10;

                    String s = "S";
                    if (d0 == 0.5D) {
                        s = "S-W";
                    }
                    if (d0 == 1.0D) {
                        s = "W";
                    }
                    if (d0 == 1.5D) {
                        s = "N-W";
                    }
                    if (d0 == 2.0D) {
                        s = "N";
                    }
                    if (d0 == 2.5D) {
                        s = "N-E";
                    }
                    if (d0 == 3.0D) {
                        s = "E";
                    }
                    if (d0 == 3.5D) {
                        s = "S-E";
                    }
                    if ((d0 + "").endsWith(".5")) {
                        s = EnumChatFormatting.WHITE + s;
                    }
                    else if(s.equalsIgnoreCase("N")) {
                        s = "§cN";
                    }else{
                        s = "" + s;
                    }

                    new Gui().drawCenteredString(this.font, EnumChatFormatting.GREEN + s, posX + k + this.getWidth() / 2, posY + 1, -1);
                    //new Gui().drawVerticalLine(posX + k + this.getWidth() / 2, posY + 1, posY + 6, -1);
                }
                k += l;
            }
            for (double d0 = 0.0D; d0 <= 3.5D; d0 += 0.1D) {
                if (j + k2 > j - 48 && j + k2 < j + 48) {
                    //new Gui().drawVerticalLine(posX + k2 + this.getWidth() / 2, posY + 1, posY + 3, new Color(255, 255, 255).getRGB());
                }
                k2 += l;
            }
        }

    }
}
