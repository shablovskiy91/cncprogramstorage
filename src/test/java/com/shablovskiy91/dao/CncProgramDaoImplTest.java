package com.shablovskiy91.dao;

import com.shablovskiy91.models.CncProgram;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(
        properties = {"jdbcUrl=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"}
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CncProgramDaoImplTest {

    @Autowired
    private CncProgramDao cncProgramDao;

    @BeforeEach
    public void beforeEach() {
        cncProgramDao.deleteAll();
    }

    @Test
    public void contextCreated() {

    }

    @Test
    public void saveSavesDataToDbAndReturnsEntityWithId() {
        CncProgram cncProgram = cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 1"));
        assertThat(cncProgram.getProgramId()).isNotBlank();
        assertThat(cncProgramDao.findAll())
                .extracting("programId")
                .containsExactly(cncProgram.getProgramId());
    }

    @Test
    void deleteAllDeletesAllData() {
        cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION" ));
        assertThat(cncProgramDao.findAll()).isNotEmpty();
        cncProgramDao.deleteAll();
        assertThat(cncProgramDao.findAll()).isEmpty();
    }

    @Test
    void findAllReturnsAllBooks() {
        assertThat(cncProgramDao.findAll()).isEmpty();
        cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION" ));
        assertThat(cncProgramDao.findAll()).isNotEmpty();
    }

    @Test
    void getByIdThrowsRuntimeExceptionIfNoProgramFound() {
        assertThatThrownBy(() -> cncProgramDao.getById("1")).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getByIdReturnsCorrectProgram() {
        CncProgram cncProgram1 = cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 1"));
        CncProgram cncProgram2 = cncProgramDao.save(new CncProgram(2, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 2"));

        assertThat(cncProgramDao.getById(cncProgram1.getProgramId()))
                .isNotNull()
                .extracting("programDescription")
                .isEqualTo(cncProgram1.getProgramDescription());
    }


    @Test
    void updateUpdatesDataInDb() {
        CncProgram cncProgram = cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION"));
        cncProgram.setProgramDescription("AFTER MACHINE REPAIRING");
        cncProgramDao.update(cncProgram);
        assertThat(cncProgramDao.getById(cncProgram.getProgramId()).getProgramDescription()).isEqualTo("AFTER MACHINE REPAIRING");
    }

    @Test
    void updateThrowsExceptionOnUpdatingNotSavedProgram() {
        assertThatThrownBy(() -> cncProgramDao.update(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 1")))
        .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteDeletesCorrectData() {
        CncProgram programToKeep = cncProgramDao.save(new CncProgram(1, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 1"));
        CncProgram programToDelete = cncProgramDao.save(new CncProgram(2, "BOSCH", 2, "Robert Sharayan", "MACHINE DESTRUCTION 2"));

        cncProgramDao.delete(programToDelete);
        assertThat(cncProgramDao.getById(programToKeep.getProgramId())).isNotNull();
        assertThatThrownBy(() -> cncProgramDao.getById(programToDelete.getProgramId())).isInstanceOf(RuntimeException.class);

    }

    //TODO:
    // 1. check your mind
    // 2. check your brain
    // 3. check your hardware
}