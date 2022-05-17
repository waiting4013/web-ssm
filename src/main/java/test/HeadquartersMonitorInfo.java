package test;

import java.io.Serializable;

public class HeadquartersMonitorInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 摄像头编码
    *
    * 数据库字段信息:camera_index_code VARCHAR(128)
    */
    private String cameraIndexCode;

    /**
    * 摄像头名字
    *
    * 数据库字段信息:camera_name VARCHAR(128)
    */
    private String cameraName;

    /**
    * 来源编码
    *
    * 数据库字段信息:region_index_code VARCHAR(128)
    */
    private String regionIndexCode;

    /**
    * 摄像头类型名字
    *
    * 数据库字段信息:camera_type_name VARCHAR(255)
    */
    private String cameraTypeName;

    /**
    * 路由ID
    *
    * 数据库字段信息:trace_id VARCHAR(32)
    */
    private String traceId;

    public HeadquartersMonitorInfo() {
        super();
    }

    public String getCameraIndexCode() {
        return this.cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public String getCameraName() {
        return this.cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getRegionIndexCode() {
        return this.regionIndexCode;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public String getCameraTypeName() {
        return this.cameraTypeName;
    }

    public void setCameraTypeName(String cameraTypeName) {
        this.cameraTypeName = cameraTypeName;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}