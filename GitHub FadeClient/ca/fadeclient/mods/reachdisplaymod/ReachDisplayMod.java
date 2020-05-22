package ca.fadeclient.mods.reachdisplaymod;

import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.text.DecimalFormat;

public class ReachDisplayMod extends ModDraggable {

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
        DecimalFormat df = new DecimalFormat("#.00 Blocks");
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
        new Gui().drawCenteredString(this.font, df.format(mc.thePlayer.reach), pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + this.getWidth(), pos.getAbsoluteY() + this.getHeight(), new Color(0, 0, 0, 100).getRGB());
        new Gui().drawCenteredString(this.font, "0.0", pos.getAbsoluteX() + this.getWidth() / 2, pos.getAbsoluteY() + 3, -1);
    }
}
