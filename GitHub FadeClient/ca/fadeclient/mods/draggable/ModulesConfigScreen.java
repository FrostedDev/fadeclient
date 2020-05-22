package ca.fadeclient.mods.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Game;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class ModulesConfigScreen extends GuiScreen {

    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    public ModulesConfigScreen(HUDManager api){
        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();
        for(IRenderer ren : registeredRenderers) {
            if (!ren.isEnabled()) {
                continue;
            }
            ScreenPosition pos = ren.load();
            if(pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }
            this.renderers.put(ren, pos);
        }
    }

    @Override
    public void initGui() {
        Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 199, this.height / 2 - 119, 80, 20, "Keystroke"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 199, this.height / 2 - 99, 80, 20, "ArmorHUD"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 199, this.height / 2 - 79, 80, 20, "PotionHUD"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 199, this.height / 2 - 59, 80, 20, "FPSMod"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 199, this.height / 2 - 39, 80, 20, "CPSMod"));

        if(this.mc.currentClickedButton == 0){
            /*this.buttonList.add(new GuiButton(100, this.width / 2 - 105, this.height / 2 - 109, 100, 20, "Show Color"));
            this.buttonList.add(new GuiButton(101, this.width / 2 - 105, this.height / 2 - 84, 100, 20, "Keystroke Speed"));
            this.buttonList.add(new GuiButton(102, this.width / 2 - 105, this.height / 2 - 59, 100, 20, "Space Bar"));
            this.buttonList.add(new GuiButton(103, this.width / 2 - 105, this.height / 2 - 34, 100, 20, "Keystroke CPS"));
            this.buttonList.add(new GuiButton(104, this.width / 2 - 105, this.height / 2 - 9, 100, 20, "Color"));
            this.buttonList.add(new GuiButton(105, this.width / 2 - 105, this.height / 2 + 16, 100, 20, "Color"));*/
        }
        if(this.mc.currentClickedButton == 1){
            //this.buttonList.add(new GuiButton(200, this.width / 2 - 105, this.height / 2 - 109, 100, 20, "Text Color"));
            //this.buttonList.add(new GuiButton(201, this.width / 2 - 105, this.height / 2 - 84, 100, 20, "Values or Percents"));
            /*this.buttonList.add(new GuiButton(202, this.width / 2 - 105, this.height / 2 - 59, 80, 20, "Space Bar"));
            this.buttonList.add(new GuiButton(203, this.width / 2 - 105, this.height / 2 - 34, 80, 20, "Keystroke CPS"));
            this.buttonList.add(new GuiButton(204, this.width / 2 - 105, this.height / 2 - 9, 80, 20, "Color"));
            this.buttonList.add(new GuiButton(205, this.width / 2 - 105, this.height / 2 + 16, 80, 20, "Color"));*/
        }
        if(this.mc.currentClickedButton == 2){

        }
        if(this.mc.currentClickedButton == 3){
            this.buttonList.add(new CheckButtons(400, this.width / 2 - 30, this.height / 2 - 109, 19, 20, "s", GameSettings.showFps));
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawRect(this.width / 2 - 200, this.height / 2 - 120, this.width / 2 + 200, this.height / 2 + 120, new Color(0, 0, 0, 150).getRGB());

        for(IRenderer renderer : renderers.keySet()) {
            ScreenPosition pos = renderers.get(renderer);
            renderer.renderDummy(pos);
            GL11.glLineWidth(1F);
            Gui.drawRectHollow(GL11.GL_LINE_LOOP, pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + renderer.getWidth(), pos.getAbsoluteY() + renderer.getHeight(), new Color(50, 50, 50).getRGB());
            GL11.glLineWidth(1);
        }

        if(this.mc.currentClickedButton == 0){
            this.drawString(this.fontRendererObj,"Show Keystroke: ",this.width / 2 - 105, this.height / 2 - 102, -1);
            this.drawString(this.fontRendererObj,"Show Keystroke CPS: ",this.width / 2 - 105, this.height / 2 - 82, -1);
            this.drawString(this.fontRendererObj,"Keystroke Color: ",this.width / 2 - 105, this.height / 2 - 62, -1);
        }
        if(this.mc.currentClickedButton == 1){
            this.drawString(this.fontRendererObj,"Text Color: ",this.width / 2 - 105, this.height / 2 - 102, -1);
            this.drawString(this.fontRendererObj,"Armor Durability: ",this.width / 2 - 105, this.height / 2 - 82, -1);
            this.drawString(this.fontRendererObj,"Show Durability: ",this.width / 2 - 105, this.height / 2 - 62, -1);
        }
        if(this.mc.currentClickedButton == 3){
            this.drawString(this.fontRendererObj,"Show FPS: ",this.width / 2 - 105, this.height / 2 - 102, -1);
            this.drawString(this.fontRendererObj,"Text Color: ",this.width / 2 - 105, this.height / 2 - 82, -1);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(this.mc.currentClickedButton != button.id && button.id < 100){
            this.mc.currentClickedButton = button.id;
            this.mc.currentClickedButton2 = 0;
            mc.displayGuiScreen(new ModulesConfigScreen(HUDManager.getInstance()));
        }

        if(button.id >= 100){
            if(button.id == 400){
                GameSettings.showFps = !GameSettings.showFps;
            }
            mc.displayGuiScreen(new ModulesConfigScreen(HUDManager.getInstance()));
        }
    }

    @Override
    public void onGuiClosed() {
        Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
    }
}
