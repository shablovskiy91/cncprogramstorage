package com.shablovskiy91.models;

public class CncProgram {

    private final String programId;
    private final int cncId;
    private final String cncType;
    private final int cncDimensions;
    private final String programAuthor;
    private String programDescription;

    public CncProgram(String programId, int cncId, String cncType, int cncDimensions, String programAuthor, String programDescription) {
        this.programId = programId;
        this.cncId = cncId;
        this.cncType = cncType;
        this.cncDimensions = cncDimensions;
        this.programAuthor = programAuthor;
        this.programDescription = programDescription;
    }

    public String getProgramId() {
        return programId;
    }

    public int getCncId() {
        return cncId;
    }

    public String getCncType() {
        return cncType;
    }

    public int getCncDimensions() {
        return cncDimensions;
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
}
