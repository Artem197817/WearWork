package workwear.workwearclient.model.modelEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Getter
public enum ProductionDivision {
    D_110_15("110-15"),
    D_110_16("110-16"),
    D_05_11("05-11"),
    D_104_01("104-01"),
    D_104_02("104-02"),
    D_104_03("104-04"),
    D_104_04("104-03"),
    D_104_07("104-07"),
    D_104_08("104-08"),
    D_110_12("110-12"),
    D_10_08("10-08"),
    D_110_10("110-10"),
    D_110_19("110-19"),
    D_10_15("10-15"),
    D_10_16("10-16"),
    D_09_01("09-01"),
    D_04_01("04-01"),
    D_04_02("04-02"),
    D_04_03("04-04"),
    D_04_04("04-03"),
    D_04_07("04-07"),
    D_04_08("04-08"),
    D_10_12("10-12"),
    D_109_01("109-01"),
    D_09_02("09-02"),
    D_109_02("09-02"),
    D_10_20("10-20"),
    D_21_00("21-00"),
    NONE("NONE"),
    D_121_00("121-00");

    @JsonValue
    private final String value;

    public String getValue() {
        if (value == null) return "NONE";
        return value;
    }

    ProductionDivision(String value) {
        this.value = value;
    }

    public static ProductionDivision getType(String value) {
        if (value == null) return NONE;
        for (ProductionDivision c : values()) {
            if (c.value.equalsIgnoreCase(value))
                return c;
        }
        return NONE;
    }
    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (ProductionDivision w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }
    public static List<String> getValuesString() {
            return  Stream.of(values())
                    .map(ProductionDivision::getValue)
                    .toList();
        }

    public static List<ProductionDivision> getValues (){
        return List.of(values());
    }

}
