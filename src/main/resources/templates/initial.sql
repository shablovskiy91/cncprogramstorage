CREATE TABLE IF NOT EXISTS cncprogram (
    program_id serial primary key,
    machine_id int,
    cnc_type varchar(255),
    machine_dimensions int,
    program_author varchar(255),
    program_description varchar(255)
);