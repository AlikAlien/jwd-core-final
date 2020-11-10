package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import static java.lang.Integer.parseInt;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public class ApplicationProperties {
    //todo
    public static final ApplicationProperties APP_PROPERTIES = new ApplicationProperties();
    private ApplicationProperties(){}

        final String inputRootDir = PropertyReaderUtil.loadProperties("inputRootDir");
        final String outputRootDir = PropertyReaderUtil.loadProperties("outputRootDir");
        final String crewFileName = PropertyReaderUtil.loadProperties("crewFileName");
        final String missionsFileName = PropertyReaderUtil.loadProperties("missionsFileName");
        final String spaceshipsFileName = PropertyReaderUtil.loadProperties("spaceshipsFileName");
        final String fileRefreshRate = PropertyReaderUtil.loadProperties("fileRefreshRate");
        final String dateTimeFormat = PropertyReaderUtil.loadProperties("dateTimeFormat");
        final String dateTimeFileFormat = PropertyReaderUtil.loadProperties("dateTimeFileFormat");
        final String routeFileName = PropertyReaderUtil.loadProperties("routeFileName");
        final String missionsRefreshRate = PropertyReaderUtil.loadProperties("missionsRefreshRate");
        final float capacityCrew = Float.parseFloat(PropertyReaderUtil.loadProperties("capacityCrew"));

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public String getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getRouteFileName() {
        return routeFileName;
    }

    public String getMissionsRefresh() {
        return missionsRefreshRate;
    }

    public String getDateTimeFileFormat() {
        return dateTimeFileFormat;
    }

    public float getCapacityCrew() {
        return capacityCrew;
    }


}
