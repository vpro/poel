package nl.vpro.poel.domain;

import javax.persistence.*;


@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String text;

    public Message() {}

    public Message(String key, String text) {
        this.key = key;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) { this.id = id; }

    public String getKey() {
        return key;
    }

    public void setKey ( String key ) { this.key = key; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (key != null ? !key.equals(message.key) : message.key != null) return false;
        return text != null ? text.equals(message.text) : message.text == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
