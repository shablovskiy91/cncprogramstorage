package com.shablovskiy91;

import com.shablovskiy91.models.CncProgram;
import com.shablovskiy91.models.CncProgramStorage;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

@SpringBootApplication
public class CncStorageApplication {
    @Bean
    public DataSource h2DataSource(@Value("${jdbcUrl}") String jdbcUrl) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(jdbcUrl);
        jdbcDataSource.setUser("user");
        jdbcDataSource.setPassword("password");
        return jdbcDataSource;
    }

    @Bean
    public CommandLineRunner cmd(DataSource dataSource) {
        return args -> {
            try(InputStream inputStream = this.getClass().getResourceAsStream("/templates/initial.sql")) {
                String sql = new String(inputStream.readAllBytes());
                try (
                        Connection connection = dataSource.getConnection();
                        Statement statement = connection.createStatement()
                ) {
                    statement.executeUpdate(sql);

                    String insertSql = "INSERT INTO cncprogram (machine_id, cnc_type, machine_dimensions, program_author, program_description) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
                        preparedStatement.setInt(1, 3);
                        preparedStatement.setString(2,"Bosch" );
                        preparedStatement.setInt(3, 2);
                        preparedStatement.setString(4, "Иван Фефелов");
                        preparedStatement.setString(5, "Корпус 200х300 мм" );
                        preparedStatement.executeUpdate();
                    }

                    System.out.println("Printing programs from db... " + "\n");
                    ResultSet rs = statement.executeQuery("SELECT program_id, machine_id, cnc_type, machine_dimensions, program_author, program_description FROM cncprogram");
                    while (rs.next()) {
                        CncProgram cncProgram = new CncProgram(rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                        cncProgram.setProgramId(rs.getString(1));
                        System.out.println(cncProgram);
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CncStorageApplication.class, args);
    }
}
