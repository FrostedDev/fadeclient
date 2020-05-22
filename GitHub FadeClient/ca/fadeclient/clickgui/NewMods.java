package ca.fadeclient.clickgui;

import ca.fadeclient.events.EventTarget;
import ca.fadeclient.events.impl.RenderEvent;
import javafx.scene.transform.Scale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;

public class NewMods {

    public Minecraft mc = Minecraft.getMinecraft();
    public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public int screenwidth = sr.getScaledWidth();
    public int screenheight = sr.getScaledHeight();
    public int mouseX = Mouse.getX() * sr.getScaledWidth() / this.mc.displayWidth;
    public int mouseY = sr.getScaledHeight() - Mouse.getY() * sr.getScaledHeight() / this.mc.displayHeight - 1;
    public ClickGui click = new ClickGui();

    public int x, y, posX, posY;
    public boolean bool = false;

    public int getWidth(){
        return x;
    }
    public int getHeight(){
        return y;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    public NewMods(){
    }

    public void render(){
        /*if(!bool){
            this.setWidth(20);
            this.setHeight(20);
            this.setPosX(this.screenwidth / 2);
            this.setPosY(this.screenheight / 2);
            bool = true;
            System.out.println("2");
        }

        if(this.hovered()){
            Gui.drawRect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight(), -1);
        }else{
            Gui.drawRect(getPosX(), getPosY(), getPosX() + getWidth(), getPosY() + getHeight(), new Color(0).getRGB());
        }


        setWidth(200);
        //if(this.hovered() && click.mouseClickedButton == 0){

        //}*/

    }

    public boolean hovered(){
        if(mouseX > getPosX() && mouseX < getPosX() + getWidth() && mouseY > getPosY() && mouseY < getPosY() + getHeight()){
            return true;
        }else{
            return false;
        }
    }

    public int setWidth(int x){
        return this.x = x;
    }
    public int setHeight(int y){
        return this.y = y;
    }
    public int setPosX(int x){
        return this.posX = x;
    }
    public int setPosY(int y){
        return this.posY = y;
    }
}
