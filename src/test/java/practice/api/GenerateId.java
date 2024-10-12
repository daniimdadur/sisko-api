package practice.api;

import java.util.UUID;

public class GenerateId {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String id = UUID.randomUUID().toString();
            System.out.println(id);
        }
    }
}
