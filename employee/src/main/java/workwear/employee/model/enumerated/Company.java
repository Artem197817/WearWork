package workwear.employee.model.enumerated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Company {
    AO_KATOD("АО \"Катод\""),
    OOO_KATOD("ООО \"Катод\""),
    NONE("NONE");

    @JsonValue
    private final String value;

    Company(String value) {
        this.value = value;
    }

    public static Company getType(String value) {
        if (value == null) return NONE;
        for (Company c : values()) {
            if (c.value.equalsIgnoreCase(value))
                return c;
        }
        return NONE;
    }

    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (Company w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }
}