import java.util.*;

public class TurnOnDevices{
    public static void main(String[] args) {
        turnOnDevice(new Lamp());
        turnOnDevice(new Computer());
        turnOnDevice(new CoffeeMaker());
    }
    private static void turnOnDevice(Connectable device) {
        device.turnOn();
        System.out.println(device.isOn());
    }
}

interface Connectable {
    public void turnOn();
    public void turnOff();
    public boolean isOn();
}

class CAdapter implements Connectable{

    private boolean status;
    @Override public void turnOn(){
        status = true;
    }
    
    @Override public void turnOff(){
        status = false;
    }

    @Override public boolean isOn(){
        return status;
    }
}

class Computer implements Connectable{

    private boolean status;
    @Override public void turnOn(){
        status = true;
    }
    
    @Override public void turnOff(){
        status = false;
    }

    @Override public boolean isOn(){
        return status;
    }
}

class Lamp implements Connectable{

    private boolean status;
    @Override public void turnOn(){
        status = true;
    }
    
    @Override public void turnOff(){
        status = false;
    }

    @Override public boolean isOn(){
        return status;
    }
}
class CoffeeMaker extends CAdapter{
    boolean status;
}