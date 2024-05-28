package co.edu.uptc.pojos;

import java.io.Serializable;

public class InfoClient implements Serializable {

    private char id;

    public InfoClient(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }
}
