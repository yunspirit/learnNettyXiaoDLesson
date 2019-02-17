package chapter7;


public class  Message {

    //可以指整个消息的长度，也可以指消息体的长度
    private int length;

    private String body;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
