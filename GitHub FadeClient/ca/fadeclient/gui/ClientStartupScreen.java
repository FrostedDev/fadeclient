package ca.fadeclient.gui;

import java.awt.Color;

import ca.fadeclient.utils.Resources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class ClientStartupScreen
{
    private static int MAX = 7;
    private static int PROGRESS = 0;
    private static String CURRENT1 = "";
    private static String CURRENT2 = "";
    private static String CURRENT3 = "";
    private static ResourceLocation splash;
    private static ResourceLocation splash2;
    public static int updateCounter = 0;

    public static void update()
    {
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getLanguageManager() != null)
        {
            drawSplash(Minecraft.getMinecraft().getTextureManager());
        }
    }

    public static void setProgress(int givenProgress, String givenText, String givenText2, String givenText3)
    {
        PROGRESS = givenProgress;
        CURRENT1 = givenText;
        CURRENT2 = givenText2;
        CURRENT3 = givenText3;
        update();
    }

    public static void drawSplash(TextureManager tm)
    {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledresolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();

        if (splash == null)
        {
            splash = Resources.background2;
        }

        if (splash2 == null)
        {
            splash2 = Resources.logo;
        }

        tm.bindTexture(splash);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, 1920, 1080, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 1920.0F, 1080.0F);
       // tm.bindTexture(splash2);
        int posX = scaledresolution.getScaledWidth() / 2 - 75;
        int posY = scaledresolution.getScaledHeight() / 4 - 50;
        int textureWidth = 150;
        int textureHeight = 150;
        int u = 0;
        int v = 0;

        //Gui.drawModalRectWithCustomSizedTexture(posX, posY, (float)u, (float)v, textureWidth, textureHeight, textureWidth, textureHeight);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);

        GlStateManager.alphaFunc(516, 0.1F);
        Minecraft.getMinecraft().updateDisplay();
    }

    public void updateScreen()
    {
        updateCounter += 2;
    }

    private static void drawProgress()
    {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledresolution.getScaleFactor();

        if (Minecraft.getMinecraft().gameSettings != null && Minecraft.getMinecraft().getTextureManager() != null)
        {
            GlStateManager.enableNormalize();
            ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
            double d0 = (double)PROGRESS;
            double d1 = d0 / MAX;
            int s32 = (int)d1 * 102;
            String s = PROGRESS + " / " + MAX;
            Gui.drawRect((sr.getScaledWidth() / 2 - 51), (sr.getScaledHeight() / 2 + 20), (sr.getScaledWidth() / 2 + 51), (sr.getScaledHeight() / 2 + 24), -1);
            Gui.drawRect((sr.getScaledWidth() / 2 - 51), (sr.getScaledHeight() / 2 + 20), (sr.getScaledWidth() / 2 - 51) + s32, (sr.getScaledHeight() / 2 + 24), new Color(0).getRGB());
        }
    }
}
