package ca.fadeclient;

import ca.fadeclient.clickgui.ClickGui;
import ca.fadeclient.events.EventManager;
import ca.fadeclient.events.EventTarget;
import ca.fadeclient.events.impl.ClientTickEvent;
import ca.fadeclient.mods.draggable.HUDConfigScreen;
import ca.fadeclient.mods.draggable.HUDManager;
import ca.fadeclient.mods.draggable.ModInstances;
import ca.fadeclient.utils.FileManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class FadeClient extends GuiScreen {

    private HUDManager hudManager;
    private Minecraft mc = Minecraft.getMinecraft();
    public String clientName = "FadeClient";

    public void init(){
        FileManager.init();
        EventManager.register(this);
    }

    public void start(){
        this.hudManager = HUDManager.getInstance();
        ModInstances.register(this.hudManager);
    }

    public void shutdown(){

    }

    @EventTarget
    public void onTick(ClientTickEvent e){
        if (this.mc.gameSettings.draggableScreen.isPressed())
        {
            this.mc.displayGuiScreen(new HUDConfigScreen(HUDManager.getInstance(), this));
        }
    }

    public static FadeClient getInstance(){
        return new FadeClient();
    }

}
