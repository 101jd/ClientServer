package client.model;

import java.io.*;

public class FileHandler implements Handleable {
    private String path;

//    public FileHandler(String path){
//        this.path = path;
//    }

    @Override
    public void write(String s) {
        try(FileWriter writer = new FileWriter(new File(path), true)) {
            writer.append(s + System.lineSeparator());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String read() throws IOException {

        File file = new File(path);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String current;
                while ((current = reader.readLine()) != null) {
                    sb.append(current).append(System.lineSeparator());
                }
                return sb.toString();
            } catch (IOException e) {
                throw e;
            } catch (NullPointerException e){
                return "";
            }
    }

    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
    //
}
