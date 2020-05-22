package ca.fadeclient.mods.draggable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Predicate;

import ca.fadeclient.FadeClient;
import ca.fadeclient.gui.ClientScreen;
import javafx.scene.transform.Scale;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL12;

public class HUDConfigScreen extends GuiScreen {

    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private int prevX, prevY;

    public static int remainingTime = 0;

    public static int mouseX, mouseY;

    private GuiScreen prevScreen;
    public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    public int mouseClickedButton = -1;

    public LogoButton button;

    @Override
    public void initGui() {
        Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        this.buttonList.add(button = new LogoButton(0, this.width / 2 - 45, this.height / 2 - 70,90, 110, "Modules"));
    }

    public HUDConfigScreen(HUDManager api, GuiScreen screen) {

        this.prevScreen = screen;
        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();

        for(IRenderer ren : registeredRenderers) {
            if(!ren.isEnabled()) {
                continue;
            }

            ScreenPosition pos = ren.load();

            if(pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }

    }

    public float animation1 = 0;

    private void drawGuidelines() {
        ScaledResolution sr = new ScaledResolution(mc);
        //Vertical Lines
        GL11.glLineWidth(1F);
        this.drawVerticalLine(GL11.GL_LINE_LOOP,this.width / 2 - 92, -1, this.height, 0x30ffffff);
        this.drawVerticalLine(GL11.GL_LINE_LOOP, this.width / 2 - 90 + 181, -1, this.height, 0x30ffffff);

        //Horizontal Lines
        this.drawHorizontalLine(GL11.GL_LINE_LOOP, this.width, 0,this.height / 3.5, 0x30ffffff);
        this.drawHorizontalLine(GL11.GL_LINE_LOOP, this.width, 0,this.height / 1.5, 0x30ffffff);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        final float zBackup = this.zLevel;
        this.zLevel = 200;
        drawGuidelines();
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.8, 0.8, 0.8);
        if(button.isMouseOver() && mouseClickedButton != 0){
            if(animation1 < 20){
                animation1++;
            }
            GlStateManager.translate(0, -animation1, 0);
        }else{
            if(animation1 > 0){
                animation1--;
            }
            GlStateManager.translate(0, -animation1, 0);
        }
        ClientScreen.drawLogoPurple((this.width / 2) * (1 / 0.8F), (this.height / 2 - 10) * (1 / 0.8F), 0);
        GlStateManager.popMatrix();

        if(mouseClickedButton == 0){
            if (selectedRenderer.isPresent()) {
                moveSelectedRenderBy(mouseX - prevX, mouseY - prevY);
            }
            this.prevX = mouseX;
            this.prevY = mouseY;
        }
        for(IRenderer renderer : renderers.keySet()) {

            ScreenPosition pos = renderers.get(renderer);
            renderer.renderDummy(pos);
            if(pos.getAbsoluteX() + renderer.getWidth() / 2 + 3 > this.width / 2 && pos.getAbsoluteX() + renderer.getWidth() / 2 - 3 < this.width / 2 && mouseX > pos.getAbsoluteX() && mouseX < pos.getAbsoluteX() + renderer.getWidth() && mouseY > pos.getAbsoluteY() && mouseY < pos.getAbsoluteY() + renderer.getHeight()){
                Gui.drawVerticalLine(this.width / 2, 0, this.height, -1);
                this.drawCenteredString(this.fontRendererObj,"Mod Snapping still under development.",this.width / 2, this.height / 2 - 60, -1);
                pos.setAbsolute(this.width / 2 - renderer.getWidth() / 2, pos.getAbsoluteY());
            }

            if(pos.getAbsoluteY() + renderer.getHeight() / 2 + 3 > this.height / 2 && pos.getAbsoluteY() + renderer.getHeight() / 2 - 3 < this.height / 2 && mouseX > pos.getAbsoluteX() && mouseX < pos.getAbsoluteX() + renderer.getWidth() && mouseY > pos.getAbsoluteY() && mouseY < pos.getAbsoluteY() + renderer.getHeight()){
                Gui.drawHorizontalLine(0, this.width, this.height / 2, -1);
                this.drawCenteredString(this.fontRendererObj,"Mod Snapping still under development.",this.width / 2, this.height / 2 - 60, -1);
                pos.setAbsolute(pos.getAbsoluteX(), this.height / 2 - renderer.getHeight() / 2);
            }

            GL11.glLineWidth(1F);
            Gui.drawRectHollow(GL11.GL_LINE_LOOP, pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + renderer.getWidth(), pos.getAbsoluteY() + renderer.getHeight(), new Color(255, 255, 255).getRGB());
        }

        this.zLevel = zBackup;
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mc.font20.drawCenteredString(EnumChatFormatting.WHITE + "Modules", this.width / 2, this.height / 2 + 36, new Color(0).getRGB());
        if(button.isMouseOver() && mouseClickedButton != 0){
            this.mc.font20.drawCenteredString(EnumChatFormatting.WHITE + "Configuration", this.width / 2, this.height / 2 - 50 - animation1, new Color(0).getRGB());
        }

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if(button.id == 0){
            mc.displayGuiScreen(new ModulesConfigScreen(HUDManager.getInstance()));
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == Keyboard.KEY_ESCAPE || keyCode == this.mc.gameSettings.draggableScreen.getKeyCode()) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {

    }


    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);

        adjustBounds(renderer, pos);
    }

    @Override
    public void onGuiClosed() {

        Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);

        for(IRenderer renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
            ScreenPosition pos = renderers.get(renderer);
            if(pos.getAbsoluteX() > this.width / 2 - 45 && pos.getAbsoluteX() + renderer.getWidth() < this.width / 2 - 45 + 90 && pos.getAbsoluteY() > this.height / 2 - 70 && pos.getAbsoluteY() + renderer.getHeight() < this.height / 2 - 70 + 110){
                pos.setAbsolute(0, 0);
            }
        }

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));



        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int mobuttonuseButton) throws IOException {
        super.mouseClicked(x, y, mobuttonuseButton);
        this.prevX = x;
        this.prevY = y;
        mouseClickedButton = mobuttonuseButton;
        loadMouseOver(x, y);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        mouseClickedButton = -1;
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private int mouseX, mouseY;

        public MouseOverFinder(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {

            ScreenPosition pos = renderers.get(renderer);

            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth() && mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
                return true;
            }

            return false;
        }

    }


}
