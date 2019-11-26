package MaxPriorityQueue;

public class Node {
    private int key;
    private String subject;

    public int getKey() {
        return key;
    }

    public String getSubject() {
        return subject;
    }

    public Node(int key, String subject) {
        this.key = key;
        this.subject = subject;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
