package lt.vu.psk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PskLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(PskLab2Application.class, args);
    }

}
