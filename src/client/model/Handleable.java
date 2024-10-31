package client.model;

import java.io.IOException;

public interface Handleable {
    void write(String s);

    String read() throws IOException;

    String getPath();

    void setPath(String path);
}
