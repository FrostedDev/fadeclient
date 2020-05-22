package ca.fadeclient.mods.draggable;

import ca.fadeclient.FadeClient;
import ca.fadeclient.events.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod
{
    private boolean isEnabled = true;
    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final FontRenderer font;
    protected final FadeClient client;

    public Mod()
    {
        this.font = this.mc.fontRendererObj;
        this.client = FadeClient.getInstance();
        this.setEnabled(this.isEnabled);
    }

    private void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;

        if (isEnabled)
        {
            EventManager.register(this);
        }
        else
        {
            EventManager.unregister(this);
        }
    }

    public boolean isEnabled()
    {
        return this.isEnabled;
    }
}
