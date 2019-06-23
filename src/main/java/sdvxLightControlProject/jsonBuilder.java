package sdvxLightControlProject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class HeartBeatPackage{
    String game = "SDVXLC";
}

class BindingPackage {
    String game = "SDVXLC";
    String event;
    List<HandlerPackage> handlers = new ArrayList<HandlerPackage>();

    public BindingPackage(String mode1, String mode2, int red, int green, int blue) {
        HandlerPackage mouseHandlerPackage = new HandlerPackage();
        HandlerPackage headsetHandlerPackage = new HandlerPackage();
        mouseHandlerPackage.devicetype = "mouse";
        headsetHandlerPackage.devicetype = "headset";
        mouseHandlerPackage.mode = mode1;
        headsetHandlerPackage.mode = mode2;
        handlers.add(mouseHandlerPackage);
        handlers.add(headsetHandlerPackage);
        mouseHandlerPackage.color.red = red;
        headsetHandlerPackage.color.red = red;
        mouseHandlerPackage.color.green = green;
        headsetHandlerPackage.color.green = green;
        mouseHandlerPackage.color.blue = blue;
        headsetHandlerPackage.color.blue = blue;
    }
}

class IdleBindingPackage extends BindingPackage{
    int min_value = 0;
    int max_value = 100;
    public IdleBindingPackage(String mode1, String mode2, int red, int green, int blue) {
        super(mode1, mode2, red, green, blue);
    }
}

class HandlerPackage {
    String mode;
    @SerializedName("device-type")
    String devicetype;
    Color color = new Color();
}


class Color{
    int red;
    int green;
    int blue;
}



public class jsonBuilder {


    public String getButtonBindingPackage(){
        BindingPackage buttonBindingPackage = new BindingPackage("color", "color", 255,255,255);
        Gson converter = new Gson();
        buttonBindingPackage.event = "ButtonPressed";
        return converter.toJson(buttonBindingPackage);
    }

    public String getFXBindingPackage(){
        BindingPackage FXBindingPackage = new BindingPackage("color","color",240,93,24);
        Gson converter = new Gson();
        FXBindingPackage.event = "FXPressed";
        return converter.toJson(FXBindingPackage);
    }

    public String getIdleBindingPackage(){
        IdleBindingPackage idleBindingPackage = new IdleBindingPackage("percent","percent",0,0,0);
        Gson converter = new Gson();
        idleBindingPackage.event = "Idle";
        return converter.toJson(idleBindingPackage);
    }

}
