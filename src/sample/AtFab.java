package sample;

public class AtFab implements MessageFactory{
    @Override
    public Message createMessage() {
        return new Attention();
    }
}
