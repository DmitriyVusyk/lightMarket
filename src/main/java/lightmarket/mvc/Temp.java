package lightmarket.mvc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Temp {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        System.out.println(password);
    }
}
