package test;

import java.math.BigDecimal;

public class PlatCommissionModel {
    private Integer levelId;

    private BigDecimal oilPrice92;

    private BigDecimal oilPrice95;

    private BigDecimal oilPrice98;

    private BigDecimal oilPrice0;

    private String vip_level;

    private String levelName;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public BigDecimal getOilPrice92() {
        return oilPrice92;
    }

    public void setOilPrice92(BigDecimal oilPrice92) {
        this.oilPrice92 = oilPrice92;
    }

    public BigDecimal getOilPrice95() {
        return oilPrice95;
    }

    public void setOilPrice95(BigDecimal oilPrice95) {
        this.oilPrice95 = oilPrice95;
    }

    public BigDecimal getOilPrice98() {
        return oilPrice98;
    }

    public void setOilPrice98(BigDecimal oilPrice98) {
        this.oilPrice98 = oilPrice98;
    }

    public BigDecimal getOilPrice0() {
        return oilPrice0;
    }

    public void setOilPrice0(BigDecimal oilPrice0) {
        this.oilPrice0 = oilPrice0;
    }

    public String getVip_level() {
        return vip_level;
    }

    public void setVip_level(String vip_level) {
        this.vip_level = vip_level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
