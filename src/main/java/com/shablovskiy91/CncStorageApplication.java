package com.shablovskiy91;

import com.shablovskiy91.models.CncProgram;
import com.shablovskiy91.models.CncProgramStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CncStorageApplication {
    public static void main(String[] args) {
        long programId = 1;

        CncProgramStorage.getCncProgramList().add(
                new CncProgram(programId, 7, "PHILIPS", 2, "Михаил Староходов")
        );

        programId++;
        CncProgramStorage.getCncProgramList().add(
                new CncProgram(programId, 9, "SIEMENS", 2, "Андрей Михайлов")
        );

        programId++;
        CncProgramStorage.getCncProgramList().add(
                new CncProgram(programId, 1, "BOSCH", 1, "Эдуард Грибоход")
        );


        SpringApplication.run(CncStorageApplication.class, args);
    }
}
