package com.shablovskiy91.dao;

import com.shablovskiy91.models.CncProgram;

import java.sql.SQLException;
import java.util.List;

public interface CncProgramDao {

    List<CncProgram> findAll();

    CncProgram save(CncProgram cncProgram);

    CncProgram getById(String programId);

    CncProgram update(CncProgram cncProgram);

    void delete(CncProgram cncProgram);

    void deleteAll();

}
