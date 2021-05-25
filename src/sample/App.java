package sample;

public class App {
    private Message message;

    public App(MessageFactory factory){
        message = factory.createMessage();

    }
    public void Message() {
        message.Message();
    }
}
