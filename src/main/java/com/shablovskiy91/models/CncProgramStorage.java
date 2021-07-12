package com.shablovskiy91.models;

import java.util.ArrayList;
import java.util.List;

public class CncProgramStorage {
    private static List<CncProgram> cncProgramList = new ArrayList<>();

    public static List<CncProgram> getCncProgramList() {
        return cncProgramList;
    }
}
