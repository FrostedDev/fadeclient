package ca.fadeclient.utils;

import net.minecraft.util.ResourceLocation;

public enum Resources {

    LOGO(new ResourceLocation("fadeclient/gui/mainmenu/FadeClientLogo.png")),
    LOGO2(new ResourceLocation("fadeclient/gui/mainmenu/logo2.png")),
    BACKGROUND(new ResourceLocation("fadeclient/gui/mainmenu/background.png")),
    BACKGROUND2(new ResourceLocation("fadeclient/gui/mainmenu/background2.png")),
    EXIT(new ResourceLocation("fadeclient/gui/mainmenu/quit.png")),
    OPTIONS(new ResourceLocation("fadeclient/gui/mainmenu/options.png")),
    ;

    public ResourceLocation rl;

    private Resources(ResourceLocation rl)
    {
        this.rl = rl;
    }

    public ResourceLocation getResourceLocation(){
        return rl;
    }

}
