package ca.fadeclient.mods.keystroke;

import java.awt.Color;
import java.util.ArrayList;

import ca.fadeclient.mods.draggable.HUDConfigScreen;
import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class KeyStrokes1 extends ModDraggable
{
    private ScreenPosition pos;
    private KeyStrokes1.KeyStrokesMode wasdSprintMouse = KeyStrokes1.KeyStrokesMode.WASD_MOUSE;
    private KeyStrokes1.KeyStrokesMode zqsdSprintMouse = KeyStrokes1.KeyStrokesMode.ZQSD_MOUSE;
    private KeyStrokes1.KeyStrokesMode spaceBarKey = KeyStrokes1.KeyStrokesMode.WASD_SPACE;
    private KeyStrokes1.KeyStrokesMode spaceBarKeyZQSD = KeyStrokes1.KeyStrokesMode.ZQSD_SPACE;
    private ArrayList<Long> clicks = new ArrayList();
    private ArrayList<Long> clicks2 = new ArrayList();
    private boolean wasPressed;
    private boolean wasPressed2;
    private long lastPressed;
    private long lastPressed2;

    public void setMode(KeyStrokes1.KeyStrokesMode mode)
    {
        this.wasdSprintMouse = mode;
        this.zqsdSprintMouse = mode;
    }

    public int getWidth()
    {
        return this.wasdSprintMouse.getWidth();
    }

    public int getHeight()
    {
        return GameSettings.spaceBarKey ? this.spaceBarKey.getHeight() : this.wasdSprintMouse.getHeight();
    }

    public static int rainbowEffect()
    {
        /*
         * Plus le chiffre est grand plus c'est vite '-'
         */
        if (GameSettings.rgbSpeed == 1) {
            return Color.HSBtoRGB((float)(System.currentTimeMillis() % 3000L) / 3000.0F, 0.8F, 0.8F);
        }
        else if (GameSettings.rgbSpeed == 2) {
            return Color.HSBtoRGB((float)(System.currentTimeMillis() % 2000L) / 2000.0F, 0.8F, 0.8F);
        }
        else if(GameSettings.rgbSpeed == 3) {
            return Color.HSBtoRGB((float)(System.currentTimeMillis() % 1000L) / 1000.0F, 0.8F, 0.8F);
        }
        else {
            return 0;
        }

    }

    static int getRainbow(final int speed, final int offset) {
        float hue = (float)((System.currentTimeMillis() + offset) % speed);
        hue /= speed;
        return Color.HSBtoRGB(System.currentTimeMillis() % 1000L / 1000.0f, 0.8f, 0.8f);
    }

    public void drawString(FontRenderer fontRendererIn, String text, int x, int y, float size, int color)
    {
        GL11.glScalef(size, size, size);
        float f = (float)Math.pow((double)size, -1.0D);
        this.font.drawString(text, Math.round((float)x / size), Math.round((float)y / size), color);
        GL11.glScalef(f, f, f);
    }

    public void render(ScreenPosition pos)
    {
        int k3 = (int)((float) HUDConfigScreen.remainingTime * 256.0F / 10.0F);
        if(k3 > 255) {
            k3 = 255;
        }

        if (!(this.mc.currentScreen instanceof GuiChat) && !this.mc.gameSettings.showDebugInfo)
        {
            if (GameSettings.keyStrokesOnOff)
            {
                GL11.glPushMatrix();
                //boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
                //GL11.glDisable(GL11.GL_BLEND);
                boolean flag1 = Mouse.isButtonDown(0);
                boolean flag2 = Mouse.isButtonDown(1);

                if (flag1 != this.wasPressed)
                {
                    this.lastPressed = System.currentTimeMillis();
                    this.wasPressed = flag1;

                    if (flag1)
                    {
                        this.clicks.add(Long.valueOf(this.lastPressed));
                    }
                }

                if (flag2 != this.wasPressed2)
                {
                    this.lastPressed2 = System.currentTimeMillis();
                    this.wasPressed2 = flag2;

                    if (flag2)
                    {
                        this.clicks2.add(Long.valueOf(this.lastPressed2));
                    }
                }

                if (GameSettings.WASD)
                {
                    if (!GameSettings.spaceBarKey)
                    {
                        for (KeyStrokes1.Key keystrokes1$key3 : this.wasdSprintMouse.getKeys())
                        {
                            int l = this.font.getStringWidth(keystrokes1$key3.getName()) + 1;

                            if (!GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key3.getX(), pos.getAbsoluteY() + keystrokes1$key3.getY(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth(), pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight(), keystrokes1$key3.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key3.getX(), pos.getAbsoluteY() + keystrokes1$key3.getY(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth(), pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight(), keystrokes1$key3.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, keystrokes1$key3.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, keystrokes1$key3.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }
                        }
                    }
                    else
                    {
                        for (KeyStrokes1.Key keystrokes1$key2 : this.spaceBarKey.getKeys())
                        {
                            int k = this.font.getStringWidth(keystrokes1$key2.getName()) + 1;

                            if (!GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key2.getX(), pos.getAbsoluteY() + keystrokes1$key2.getY(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth(), pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight(), keystrokes1$key2.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key2.getX(), pos.getAbsoluteY() + keystrokes1$key2.getY(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth(), pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight(), keystrokes1$key2.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key2.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key2.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, keystrokes1$key2.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, keystrokes1$key2.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }
                        }
                    }
                }
                else if (!GameSettings.spaceBarKey)
                {
                    for (KeyStrokes1.Key keystrokes1$key1 : this.zqsdSprintMouse.getKeys())
                    {
                        int j = this.font.getStringWidth(keystrokes1$key1.getName());

                        if (!GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key1.getX(), pos.getAbsoluteY() + keystrokes1$key1.getY(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth(), pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight(), keystrokes1$key1.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key1.getX(), pos.getAbsoluteY() + keystrokes1$key1.getY(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth(), pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight(), keystrokes1$key1.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, keystrokes1$key1.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, keystrokes1$key1.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }
                    }
                }
                else
                {
                    for (KeyStrokes1.Key keystrokes1$key : this.spaceBarKeyZQSD.getKeys())
                    {
                        int i = this.font.getStringWidth(keystrokes1$key.getName());

                        if (!GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key.getX(), pos.getAbsoluteY() + keystrokes1$key.getY(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth(), pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight(), keystrokes1$key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key.getX(), pos.getAbsoluteY() + keystrokes1$key.getY(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth(), pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight(), keystrokes1$key.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, keystrokes1$key.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, keystrokes1$key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }
                    }
                }

                this.drawString(this.font, this.getCPS() + " CPS", pos.getAbsoluteX() + 7, pos.getAbsoluteY() + 56, 0.5F, -1);
                this.drawString(this.font, this.getCPS2() + " CPS", pos.getAbsoluteX() + 38, pos.getAbsoluteY() + 56, 0.5F, -1);

                GL11.glPopMatrix();
            }
        }
    }

    private int getCPS()
    {
        long times = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < times);
        return this.clicks.size();
    }

    private int getCPS2()
    {
        long times = System.currentTimeMillis();
        this.clicks2.removeIf(aLong -> aLong + 1000 < times);
        return this.clicks2.size();
    }

    private void drawHollowRect(int x, int y, int w, int h, int color) {
        Gui.drawHorizontalLine(x, x + w, y, color);
        Gui.drawHorizontalLine(x, x + w, y + h, color);

        Gui.drawVerticalLine(x, y + h, y, color);
        Gui.drawVerticalLine(x + w, y + h, y, color);
    }

    public void renderDummy(ScreenPosition pos)
    {
        int k3 = (int)((float)HUDConfigScreen.remainingTime * 256.0F / 10.0F);
        if(k3 > 255) {
            k3 = 255;
        }

        if (!(this.mc.currentScreen instanceof GuiChat) && !this.mc.gameSettings.showDebugInfo)
        {
            if (GameSettings.keyStrokesOnOff)
            {
                GL11.glPushMatrix();
                //boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
                //GL11.glDisable(GL11.GL_BLEND);
                boolean flag1 = Mouse.isButtonDown(0);
                boolean flag2 = Mouse.isButtonDown(1);

                if (flag1 != this.wasPressed)
                {
                    this.lastPressed = System.currentTimeMillis();
                    this.wasPressed = flag1;

                    if (flag1)
                    {
                        this.clicks.add(Long.valueOf(this.lastPressed));
                    }
                }

                if (flag2 != this.wasPressed2)
                {
                    this.lastPressed2 = System.currentTimeMillis();
                    this.wasPressed2 = flag2;

                    if (flag2)
                    {
                        this.clicks2.add(Long.valueOf(this.lastPressed2));
                    }
                }

                if (GameSettings.WASD)
                {
                    if (!GameSettings.spaceBarKey)
                    {
                        for (KeyStrokes1.Key keystrokes1$key3 : this.wasdSprintMouse.getKeys())
                        {
                            int l = this.font.getStringWidth(keystrokes1$key3.getName());

                            if (!GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key3.getX(), pos.getAbsoluteY() + keystrokes1$key3.getY(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth(), pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight(), keystrokes1$key3.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key3.getX(), pos.getAbsoluteY() + keystrokes1$key3.getY(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth(), pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight(), keystrokes1$key3.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, keystrokes1$key3.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                this.font.drawString(keystrokes1$key3.getName(), pos.getAbsoluteX() + keystrokes1$key3.getX() + keystrokes1$key3.getWidth() / 2 - l / 2, pos.getAbsoluteY() + keystrokes1$key3.getY() + keystrokes1$key3.getHeight() / 2 - 4, keystrokes1$key3.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }
                        }
                    }
                    else
                    {
                        for (KeyStrokes1.Key keystrokes1$key2 : this.spaceBarKey.getKeys())
                        {
                            int k = this.font.getStringWidth(keystrokes1$key2.getName());

                            if (!GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key2.getX(), pos.getAbsoluteY() + keystrokes1$key2.getY(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth(), pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight(), keystrokes1$key2.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key2.getX(), pos.getAbsoluteY() + keystrokes1$key2.getY(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth(), pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight(), keystrokes1$key2.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key2.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key2.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }

                            if (GameSettings.rgbKeys)
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, rainbowEffect());
                            }
                            else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, keystrokes1$key2.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                            }
                            else
                            {
                                this.font.drawString(keystrokes1$key2.getName(), pos.getAbsoluteX() + keystrokes1$key2.getX() + keystrokes1$key2.getWidth() / 2 - k / 2, pos.getAbsoluteY() + keystrokes1$key2.getY() + keystrokes1$key2.getHeight() / 2 - 4, keystrokes1$key2.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                            }
                        }
                    }
                }
                else if (!GameSettings.spaceBarKey)
                {
                    for (KeyStrokes1.Key keystrokes1$key1 : this.zqsdSprintMouse.getKeys())
                    {
                        int j = this.font.getStringWidth(keystrokes1$key1.getName());

                        if (!GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key1.getX(), pos.getAbsoluteY() + keystrokes1$key1.getY(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth(), pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight(), keystrokes1$key1.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key1.getX(), pos.getAbsoluteY() + keystrokes1$key1.getY(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth(), pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight(), keystrokes1$key1.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, keystrokes1$key1.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            this.font.drawString(keystrokes1$key1.getName(), pos.getAbsoluteX() + keystrokes1$key1.getX() + keystrokes1$key1.getWidth() / 2 - j / 2, pos.getAbsoluteY() + keystrokes1$key1.getY() + keystrokes1$key1.getHeight() / 2 - 4, keystrokes1$key1.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }
                    }
                }
                else
                {
                    for (KeyStrokes1.Key keystrokes1$key : this.spaceBarKeyZQSD.getKeys())
                    {
                        int i = this.font.getStringWidth(keystrokes1$key.getName());

                        if (!GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key.getX(), pos.getAbsoluteY() + keystrokes1$key.getY(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth(), pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight(), keystrokes1$key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 102)).getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + keystrokes1$key.getX(), pos.getAbsoluteY() + keystrokes1$key.getY(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth(), pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight(), keystrokes1$key.isDown() ? (new Color(0, 0, 0, 102)).getRGB() : (new Color(255, 255, 255, 102)).getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            Gui.drawRect(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 72, pos.getAbsoluteX() + 49, pos.getAbsoluteY() + 73, keystrokes1$key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }

                        if (GameSettings.rgbKeys)
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, rainbowEffect());
                        }
                        else if (!GameSettings.rgbKeys && GameSettings.whiteKeyStroke)
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, keystrokes1$key.isDown() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                        }
                        else
                        {
                            this.font.drawString(keystrokes1$key.getName(), pos.getAbsoluteX() + keystrokes1$key.getX() + keystrokes1$key.getWidth() / 2 - i / 2, pos.getAbsoluteY() + keystrokes1$key.getY() + keystrokes1$key.getHeight() / 2 - 4, keystrokes1$key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                        }
                    }
                }

                this.drawString(this.font, this.getCPS() + " CPS", pos.getAbsoluteX() + 7, pos.getAbsoluteY() + 56, 0.5F, -1);
                this.drawString(this.font, this.getCPS2() + " CPS", pos.getAbsoluteX() + 38, pos.getAbsoluteY() + 56, 0.5F, -1);

                GL11.glPopMatrix();
            }
        }
    }

    public boolean isEnable()
    {
        return true;
    }

    private static class Key
    {
        private static final KeyStrokes1.Key W;
        private static final KeyStrokes1.Key A;
        private static final KeyStrokes1.Key S;
        private static final KeyStrokes1.Key D;
        private static final KeyStrokes1.Key ZA;
        private static final KeyStrokes1.Key QA;
        private static final KeyStrokes1.Key SA;
        private static final KeyStrokes1.Key DA;
        private static final KeyStrokes1.Key LMB;
        private static final KeyStrokes1.Key RMB;
        private static final KeyStrokes1.Key SPACE;
        private final String name;
        private final KeyBinding keyBind;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Key(String name, KeyBinding keyBind, int x, int y, int width, int height)
        {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean isDown()
        {
            return this.keyBind.isKeyDown();
        }

        public int getHeight()
        {
            return this.height;
        }

        public String getName()
        {
            return this.name;
        }

        public int getWidth()
        {
            return this.width;
        }

        public int getX()
        {
            return this.x;
        }

        public int getY()
        {
            return this.y;
        }

        static
        {
            W = new KeyStrokes1.Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
            A = new KeyStrokes1.Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
            S = new KeyStrokes1.Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
            D = new KeyStrokes1.Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
            ZA = new KeyStrokes1.Key("Z", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
            QA = new KeyStrokes1.Key("Q", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
            SA = new KeyStrokes1.Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
            DA = new KeyStrokes1.Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
            LMB = new KeyStrokes1.Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 21);
            RMB = new KeyStrokes1.Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 21);
            SPACE = new KeyStrokes1.Key("", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 64, 58, 18);
        }
    }

    public static enum KeyStrokesMode
    {
        WASD(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D}),
        WASD_SPACE(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB, KeyStrokes1.Key.SPACE}),
        WASD_MOUSE(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB}),
        WASD_SPRINT(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB}),
        WASD_SPRINT_MOUSE(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB}),
        ZQSD(new KeyStrokes1.Key[]{KeyStrokes1.Key.ZA, KeyStrokes1.Key.QA, KeyStrokes1.Key.SA, KeyStrokes1.Key.DA, KeyStrokes1.Key.SPACE}),
        ZQSD_SPACE(new KeyStrokes1.Key[]{KeyStrokes1.Key.ZA, KeyStrokes1.Key.QA, KeyStrokes1.Key.SA, KeyStrokes1.Key.DA, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB, KeyStrokes1.Key.SPACE}),
        ZQSD_MOUSE(new KeyStrokes1.Key[]{KeyStrokes1.Key.ZA, KeyStrokes1.Key.QA, KeyStrokes1.Key.SA, KeyStrokes1.Key.DA, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB}),
        ZQSD_SPRINT(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB}),
        ZQSD_SPRINT_MOUSE(new KeyStrokes1.Key[]{KeyStrokes1.Key.W, KeyStrokes1.Key.A, KeyStrokes1.Key.S, KeyStrokes1.Key.D, KeyStrokes1.Key.LMB, KeyStrokes1.Key.RMB});

        private final KeyStrokes1.Key[] keys;
        private int width = 0;
        private int height = 0;

        private KeyStrokesMode(KeyStrokes1.Key[] keysIn)
        {
            this.keys = keysIn;

            for (KeyStrokes1.Key keystrokes1$key : this.keys)
            {
                this.width = Math.max(this.width, keystrokes1$key.getX() + keystrokes1$key.getWidth());
                this.height = Math.max(this.height, keystrokes1$key.getY() + keystrokes1$key.getHeight());
            }
        }

        public int getWidth()
        {
            return this.width;
        }

        public int getHeight()
        {
            return this.height;
        }

        public KeyStrokes1.Key[] getKeys()
        {
            return this.keys;
        }
    }
}
