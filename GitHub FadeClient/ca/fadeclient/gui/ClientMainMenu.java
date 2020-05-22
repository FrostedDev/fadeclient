package ca.fadeclient.gui;

import ca.fadeclient.utils.Resources;
import ca.fadeclient.gui.buttons.ClientMainMenuButton;
import ca.fadeclient.gui.buttons.ClientMainMenuModalButtons;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClientMainMenu extends GuiScreen {

    public GuiTextField field1;
    public File file = new File("FadeClient");

    @Override
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 4 + 120, 100, 20, "Play Solo"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 4 + 90, 100, 20,"Play Online"));
        this.buttonList.add(new ClientMainMenuModalButtons(2, 3, 3, 25, 25, "options", Resources.options));
        this.buttonList.add(new ClientMainMenuModalButtons(3, this.width - 25, 5, 20, 20, "exit", Resources.exit));
        field1 = new GuiTextField(0, this.fontRendererObj, this.width / 2 + 75, this.height / 4 + 16, 109, 20);
        field1.setText("");
        field1.setFocused(false);
        field1.setMaxStringLength(20);
        field1.setVisible(true);
        field1.setEnableBackgroundDrawing(true);
        field1.setEnabled(true);
    }

    @Override
    public void updateScreen() {
        field1.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ClientScreen.drawBackgroundMainMenu(this.width, this.height, this.zLevel);
        ClientScreen.drawLogoMainMenu(this.width / 2, this.height / 4 + 10, this.zLevel);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mc.font20.drawStringWithShadow("§4FadeClient §8- 1.0", 1, this.height - 13, -1);
        this.mc.font20.drawStringWithShadow("Copyright Frosted/Mojang AB - 2020©", this.width - this.mc.font20.getWidth("Copyright Frosted/Mojang AB - 2020©") - 2, this.height - 13, new Color(255, 255, 255).getRGB());
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
        } else {
            super.keyTyped(typedChar, keyCode);
            field1.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        switch(button.id){
            case 0:
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                this.mc.displayGuiScreen(new ClientMultiplayer(this));
                break;
            case 2:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            case 3:
                this.mc.shutdown();
                this.mc.shutdownMinecraftApplet();
                break;
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if(mouseX > this.width / 2 + 75 && mouseX < this.width / 2 + 75 + 109 && mouseY > this.height / 4 + 16 && mouseY < this.height / 4 + 16 + 20){
            field1.setFocused(true);
        }else{
            field1.setFocused(false);
        }
    }
}
