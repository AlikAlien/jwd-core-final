package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

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

    private ApplicationProperties() {
    }

    final String inputRootDir = PropertyReaderUtil.READER_UTIL.loadProperties("inputRootDir");
    final String outputRootDir = PropertyReaderUtil.READER_UTIL.loadProperties("outputRootDir");
    final String crewFileName = PropertyReaderUtil.READER_UTIL.loadProperties("crewFileName");
    final String missionsFileName = PropertyReaderUtil.READER_UTIL.loadProperties("missionsFileName");
    final String spaceshipsFileName = PropertyReaderUtil.READER_UTIL.loadProperties("spaceshipsFileName");
    final Long fileRefreshRate = Long.parseLong(PropertyReaderUtil.READER_UTIL.loadProperties("fileRefreshRate"));
    final String dateTimeFormat = PropertyReaderUtil.READER_UTIL.loadProperties("dateTimeFormat");
    final String dateTimeFileFormat = PropertyReaderUtil.READER_UTIL.loadProperties("dateTimeFileFormat");
    final String routeFileName = PropertyReaderUtil.READER_UTIL.loadProperties("routeFileName");
    final Long missionsRefreshRate = Long.parseLong(PropertyReaderUtil.READER_UTIL.loadProperties("missionsRefreshRate"));
    final float capacityCrew = Float.parseFloat(PropertyReaderUtil.READER_UTIL.loadProperties("capacityCrew"));
    final Long missionsRndFailRate = Long.parseLong(PropertyReaderUtil.READER_UTIL.loadProperties("missionsRndFailRate"));

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

    public Long getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public String getRouteFileName() {
        return routeFileName;
    }

    public Long getMissionsRefresh() {
        return missionsRefreshRate;
    }

    public String getDateTimeFileFormat() {
        return dateTimeFileFormat;
    }

    public float getCapacityCrew() {
        return capacityCrew;
    }

    public Long getMissionsRndFailRate() {
        return missionsRndFailRate;
    }
}
