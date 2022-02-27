package alzq;

import lombok.Data;

/**
 * @author zzz
 */
@Data
public class Asset {
    private String assetName;
    private String assetCode;
    private Long number;
    private Double size;

    public Asset(String assetName, String assetCode, Long number, Double size) {
        this.assetName = assetName;
        this.assetCode = assetCode;
        this.number = number;
        this.size = size;
    }

    public Asset(){

    }
}
