package ca.fadeclient.mods.fpsmod;

import ca.fadeclient.events.impl.ClientTickEvent;
import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import org.newdawn.slick.Game;

import java.awt.*;

public class ModFPS extends ModDraggable
{
    public int getWidth()
    {
        return 60;
    }

    public int getHeight()
    {
        return 14;
    }

    public void render(ScreenPosition pos)
    {
        int i = Minecraft.getDebugFPS();
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
        new Gui().drawCenteredString(this.font,i + " FPS", pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, 16777215);
    }

    public void renderDummy(ScreenPosition pos)
    {
        int i = Minecraft.getDebugFPS();
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
        new Gui().drawCenteredString(this.font,i + " FPS", pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, 16777215);
    }

    @Override
    public boolean isEnabled() {
        if(GameSettings.showFps) {
            if (this.mc.gameSettings.showDebugInfo) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public boolean isEnable()
    {
        if(GameSettings.showFps) {
            if (this.mc.gameSettings.showDebugInfo) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}
