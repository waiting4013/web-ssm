/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2017 KYE Company                                         */
/* 跨越速运集团有限公司版权所有                                             */
/*                                                                        */
/* PROPRIETARY RIGHTS of KYE Company are involved in the                  */
/* subject matter of this material. All manufacturing, reproduction, use, */
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */
/* the terms of the license.                                              */
/* 本软件文档资料是跨越速运集团有限公司的资产，任何人士阅读和                 */
/* 使用本资料必须获得相应的书面授权，承担保密责任和接受相应的法律约束。        */
/*                                                                         */
/**************************************************************************/
package test;

import java.io.Serializable;

/**
 * 总部摄像头信息表实体类
 * 数据库表名称：headquarters_monitor_info
 * 
 * 作   者：597059
 * 创建日期：2022年1月12日
 */
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