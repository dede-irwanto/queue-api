package dedeirwanto.queue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class QueueApiApplicationTests {

    @Test
    void contextLoads() {
        LocalDate date = LocalDate.now();
        System.out.println(LocalDate.now().minusMonths(date.getMonthValue() - 1));
    }

}
