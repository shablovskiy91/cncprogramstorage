package com.shablovskiy91;

import com.shablovskiy91.models.CncProgram;
import com.shablovskiy91.models.CncProgramStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class CncStorageApplication {
    public static void main(String[] args) {

        CncProgramStorage.getCncProgramList().add(
                new CncProgram(
                        7,
                        "PHILIPS",
                        2,
                        "Михаил Староходов",
                        "Корпус 200х400"
                        )
        );

        CncProgramStorage.getCncProgramList().add(
                new CncProgram(
                        9,
                        "SIEMENS",
                        2,
                        "Андрей Михайлов",
                        "Радиатор 50х50")
        );

        CncProgramStorage.getCncProgramList().add(
                new CncProgram(
                        1,
                        "BOSCH",
                        1,
                        "Эдуард Грибоход",
                        "Панель управления 300х50")
        );

        SpringApplication.run(CncStorageApplication.class, args);
    }
}
