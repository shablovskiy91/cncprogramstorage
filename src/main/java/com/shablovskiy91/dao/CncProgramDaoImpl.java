package com.shablovskiy91.dao;

import com.shablovskiy91.models.CncProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class CncProgramDaoImpl implements CncProgramDao {
    private final DataSource dataSource;

    @Autowired
    public CncProgramDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CncProgram> findAll() {
        final String selectSql = "SELECT " +
                "program_id, " +
                "machine_id, " +
                "cnc_type, " +
                "machine_dimensions, " +
                "program_author, " +
                "program_description " +
                "FROM cncprogram";
        List<CncProgram> cncPrograms = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectSql)
        ) {
            while (rs.next()) {
                CncProgram cncProgram = new CncProgram(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                cncProgram.setProgramId(rs.getString(1));
                cncPrograms.add(cncProgram);
            }

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return cncPrograms;
    }

    @Override
    public CncProgram save(CncProgram cncProgram) {
        String insertSql = "INSERT INTO cncprogram " +
                "(" +
                    "machine_id, " +
                    "cnc_type, " +
                    "machine_dimensions, " +
                    "program_author, " +
                    "program_description" +
                ") " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setInt(1, cncProgram.getMachineId());
            preparedStatement.setString(2,cncProgram.getCncType());
            preparedStatement.setInt(3, cncProgram.getMachineDimensions());
            preparedStatement.setString(4, cncProgram.getProgramAuthor());
            preparedStatement.setString(5, cncProgram.getProgramDescription());
            preparedStatement.executeUpdate();

            try(ResultSet rs = preparedStatement.getGeneratedKeys()) {
                rs.next();
                cncProgram.setProgramId(rs.getString(1));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return cncProgram;
    }

    @Override
    public CncProgram getById(String programId) {
        String getByIdSql = "SELECT " +
                "program_id, " +
                "machine_id, " +
                "cnc_type, " +
                "machine_dimensions, " +
                "program_author, " +
                "program_description " +
                "FROM cncprogram " +
                "WHERE program_id=? ";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByIdSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setInt(1, Integer.parseInt(programId));
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (!rs.next()) {
                    throw new RuntimeException(String.format("cnc program with id %s was not found", programId));
                }
                CncProgram cncProgram = new CncProgram(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                cncProgram.setProgramId(rs.getString(1));
                return cncProgram;
            }

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public CncProgram update(CncProgram cncProgram) {
        if (Objects.isNull((cncProgram.getProgramId()))) {
            throw new RuntimeException("Can't update unsaved program");
        }
        String updateSql = "UPDATE cncprogram " +
                "SET " +
                "machine_id = ?, " +
                "cnc_type = ?, " +
                "machine_dimensions = ?, " +
                "program_author = ?, " +
                "program_description = ? " +
                "WHERE program_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql))
        {
            preparedStatement.setInt(1, cncProgram.getMachineId());
            preparedStatement.setString(2,cncProgram.getCncType());
            preparedStatement.setInt(3, cncProgram.getMachineDimensions());
            preparedStatement.setString(4, cncProgram.getProgramAuthor());
            preparedStatement.setString(5, cncProgram.getProgramDescription());
            preparedStatement.setString(6, cncProgram.getProgramId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return cncProgram;
    }

    @Override
    public void delete(CncProgram cncProgram) {
        String deleteByIdSql = "DELETE FROM cncprogram WHERE program_id = ?";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteByIdSql)
        ) {
            statement.setString(1, cncProgram.getProgramId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void deleteAll() {
        String deleteSql = "TRUNCATE TABLE cncprogram";
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(deleteSql);

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
