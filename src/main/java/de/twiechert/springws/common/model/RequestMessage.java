package de.twiechert.springws.common.model;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */
public class RequestMessage {

    private String ref;

    public RequestMessage() {
    }

    public RequestMessage(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "ref='" + ref + '\'' +
                '}';
    }
}
