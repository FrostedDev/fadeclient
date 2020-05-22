package ca.fadeclient.mods.cps;

import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

public class ModCps extends ModDraggable {

    private ArrayList<Long> clicks = new ArrayList();
    private boolean wasPressed;
    private long lastPressed;

    @Override
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void render(ScreenPosition pos) {
        boolean flag1 = Mouse.isButtonDown(0);
        if (flag1 != this.wasPressed)
        {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = flag1;

            if (flag1)
            {
                this.clicks.add(Long.valueOf(this.lastPressed));
            }
        }
        if(!mc.gameSettings.showDebugInfo) {
            Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
            new Gui().drawCenteredString(this.font,"" + getCPS() + " CPS", pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, -1);
        }

    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        boolean flag1 = Mouse.isButtonDown(0);
        if (flag1 != this.wasPressed)
        {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = flag1;

            if (flag1)
            {
                this.clicks.add(Long.valueOf(this.lastPressed));
            }
        }
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
        new Gui().drawCenteredString(this.mc.fontRendererObj, "" + getCPS() + " CPS", pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, -1);
    }

    private int getCPS()
    {
        long times = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < times);
        return this.clicks.size();
    }
}
