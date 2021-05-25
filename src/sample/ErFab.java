package sample;

public class ErFab implements MessageFactory{
    @Override
    public Message createMessage() {
        return new Errore();
    }


}
