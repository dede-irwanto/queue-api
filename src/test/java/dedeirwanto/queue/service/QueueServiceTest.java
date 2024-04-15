package dedeirwanto.queue.service;

import dedeirwanto.queue.repository.QueueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class QueueServiceTest {

    @Autowired
    private QueueRepository queueRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    void testQueuePrint() {
        String code = "001";

        System.out.println(Integer.parseInt(code));
    }

    @Test
    void testGetChart() {
        queueRepository.findAll();
    }
}
