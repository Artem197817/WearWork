package workwear.workshoes.model.enumerated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WorkShoesType {

    CLOGS("Сабо"),
    BOOTS("Ботинки/Сапоги"),
    HALF_BOOTS("Полуботинки"),
    WINTER_SHOES("Зимняя обувь"),
    OTHER("Другое");


    @JsonValue
    private final String value;

    WorkShoesType(String value) {
        this.value = value;
    }

    public static WorkShoesType getType(String value) {
        if (value == null) return OTHER;
        for (WorkShoesType w : values()) {
            if (w.value.equalsIgnoreCase(value))
                return w;
        }
        return OTHER;
    }

    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (WorkShoesType w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }

}