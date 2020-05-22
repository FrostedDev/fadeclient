package ca.fadeclient.clickgui;

import ca.fadeclient.events.impl.RenderEvent;
import net.minecraft.client.gui.Gui;

public class testMod extends NewMods {

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 100;
    }

    @Override
    public void render() {
        Gui.drawRect(0, 0, 20, 20, -1);
    }

}
