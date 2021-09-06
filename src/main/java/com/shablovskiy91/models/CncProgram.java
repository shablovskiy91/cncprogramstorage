package com.shablovskiy91.models;

public class CncProgram {

    private String programId;
    private final int machineId;
    private final String cncType;
    private final int machineDimensions;
    private final String programAuthor;
    private String programDescription;

    public CncProgram(int machineId, String cncType, int machineDimensions, String programAuthor, String programDescription) {
        this.machineId = machineId;
        this.cncType = cncType;
        this.machineDimensions = machineDimensions;
        this.programAuthor = programAuthor;
        this.programDescription = programDescription;
    }


    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramId() {
        return programId;
    }

    public int getMachineId() {
        return machineId;
    }

    public String getCncType() {
        return cncType;
    }

    public int getMachineDimensions() {
        return machineDimensions;
    }

    public String getProgramAuthor() {
        return programAuthor;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    @Override
    public String toString() {
        return "CncProgram{" +
                "programId='" + programId + '\'' +
                ", machineId=" + machineId +
                ", cncType='" + cncType + '\'' +
                ", machineDimensions=" + machineDimensions +
                ", programAuthor='" + programAuthor + '\'' +
                ", programDescription='" + programDescription + '\'' +
                '}';
    }
}
