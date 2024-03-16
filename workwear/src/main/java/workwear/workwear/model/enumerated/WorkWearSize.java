package workwear.workwear.model.enumerated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WorkWearSize {

    S_48_50("48-50"),
    S_44_46("44-46"),
    S_40_42("40-42"),
    S_36_38("36-38"),
    S_32_34("32-34"),
    S_52_54("52-54"),
    S_56_58("56-58"),
    S_60_62("60-62"),
    S_64_66("64-66"),
    S_68_70("68-70"),
    UNKNOWN("Неизвестный размер");

    @JsonValue
    private final String value;

    WorkWearSize(String value) {
        this.value = value;
    }

    public static WorkWearSize getType(String value) {
        if (value == null) return UNKNOWN;
        for (WorkWearSize w : values()) {
            if (w.value.equalsIgnoreCase(value))
                return w;
        }
        return UNKNOWN;
    }

    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (WorkWearSize w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }
}
