package workwear.workwear.model.enumerated;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum WorkWearType {

    TECHNOLOGICAL_CLOTHING("Технологическая одежда"),
    WORKER_SUIT("Костюм рабочий"),
    WELDER_SUIT("Костюм сварщика"),
    WORK_COAT("Халат"),
    WINTER_SUIT("Костюм зимний"),
    OTHER("Другое"),
    SUIT_CLEAN_ROOMS("Костюм чистых комнат");

    @JsonValue
    private final String value;

    WorkWearType(String value){
        this.value=value;
    }

    public static WorkWearType  getType(String value) {
        if (value == null) return OTHER;
        for (WorkWearType w : values()) {
            if (w.value.equalsIgnoreCase(value))
                return w;
        }
        return OTHER;
    }
    public static String[] getTypeArray() {

        String[] valuesShoes = new String[values().length];
        int i = 0;
        for (WorkWearType w : values()) {
            valuesShoes[i++] = w.getValue();
        }
        return valuesShoes;
    }
}
