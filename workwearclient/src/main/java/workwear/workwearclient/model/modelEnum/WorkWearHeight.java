package workwear.workwearclient.model.modelEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WorkWearHeight {
    H_158_164("158-164"),
    H_170_176("170-176"),
    H_182_188("182-188"),
    H_194_200("194-200"),
    UNKNOWN("Неизвестный рост");

    @JsonValue
    private final String value;

    WorkWearHeight(String value) {
        this.value = value;
    }

    public static WorkWearHeight getType(String value) {
        if (value == null) return UNKNOWN;
        for (WorkWearHeight w : values()) {
            if (w.value.equalsIgnoreCase(value))
                return w;
        }
        return UNKNOWN;
    }
    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (WorkWearHeight w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }
}

