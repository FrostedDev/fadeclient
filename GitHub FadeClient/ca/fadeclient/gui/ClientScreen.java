package ca.fadeclient.gui;

import ca.fadeclient.utils.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ClientScreen extends Gui {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static void drawLogoMainMenu(float width, float f, double zLevel) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(Resources.logo2);
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer wR = tess.getWorldRenderer();
        wR.begin(7, DefaultVertexFormats.POSITION_TEX);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glBlendFunc(770, 771);
        GlStateManager.color(1, 1, 1, 1f);
        wR.pos(width - 70, f - 70, zLevel).tex(0, 0).endVertex();
        wR.pos(width - 70, f + 70, zLevel).tex(0, 1).endVertex();
        wR.pos(width + 70, f + 70, zLevel).tex(1, 1).endVertex();
        wR.pos(width + 70, f - 70, zLevel).tex(1, 0).endVertex();
        tess.draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawLogoPurple(float width, float f, double zLevel) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        mc.getTextureManager().bindTexture(Resources.logo);
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer wR = tess.getWorldRenderer();
        wR.begin(7, DefaultVertexFormats.POSITION_TEX);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glBlendFunc(770, 771);
        GlStateManager.color(1, 1, 1, 1f);
        wR.pos(width - 70, f - 70, zLevel).tex(0, 0).endVertex();
        wR.pos(width - 70, f + 70, zLevel).tex(0, 1).endVertex();
        wR.pos(width + 70, f + 70, zLevel).tex(1, 1).endVertex();
        wR.pos(width + 70, f - 70, zLevel).tex(1, 0).endVertex();
        tess.draw();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public void hotTutorialsThingy(int minX, int minY, int maxX, int maxY) {
        float[][] colors = new float[4][3];
        for (int i = 0; i < 200; i+=50) {
            double rainbowState = Math.ceil((System.currentTimeMillis() + i*5) / 20.0);
            rainbowState %= 360.0;
            final Color color = Color.getHSBColor((float) (rainbowState / 360.0), 1.0f, 1.0f);
            colors[i/50][0] = color.getRed() / 255.0f;
            colors[i/50][1] = color.getGreen() / 255.0f;
            colors[i/50][2] = color.getBlue() / 255.0f;
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glBegin(GL11.GL_POLYGON);
        {
            GL11.glColor4f(colors[0][0], colors[0][1], colors[0][2], 1.0f);
            GL11.glVertex2f(minX, minY);
            GL11.glColor4f(colors[1][0], colors[1][1], colors[1][2], 1.0f);
            GL11.glVertex2f(minX, maxY);
            GL11.glColor4f(colors[2][0], colors[2][1], colors[2][2], 1.0f);
            GL11.glVertex2f(maxX, maxY);
            GL11.glColor4f(colors[3][0], colors[3][1], colors[3][2], 1.0f);
            GL11.glVertex2f(maxX, minY);
        }
        GL11.glEnd();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void drawBackgroundMainMenu(int width, int height, double zLevel) {
        mc.getTextureManager().bindTexture(Resources.background);
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer wR = tess.getWorldRenderer();
        GlStateManager.pushMatrix();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GlStateManager.enableBlend();
        wR.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        GlStateManager.disableAlpha();
        int i = 3;
        for (int j = 0; j < i; ++j)
        {
            wR.pos(width - width, height - height, zLevel).tex(0, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
            wR.pos(width - width, height, zLevel).tex(0, 1).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
            wR.pos(width, height, zLevel).tex(1, 1).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
            wR.pos(width, height - height, zLevel).tex(1, 0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
        }
        tess.draw();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

}
