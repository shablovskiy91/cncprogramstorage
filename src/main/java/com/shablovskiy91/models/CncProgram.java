package com.shablovskiy91.models;

public class CncProgram {

    private final long programId;
    private final int cncId;
    private final String cncType;
    private final int cncDimensions;
    private final String programAuthor;

    public CncProgram(long programId, int cncId, String cncType, int cncDimensions, String programAuthor) {
        this.programId = programId;
        this.cncId = cncId;
        this.cncType = cncType;
        this.cncDimensions = cncDimensions;
        this.programAuthor = programAuthor;
    }

    public long getProgramId() {
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
}
