package workwear.workwearclient.model.modelEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

    public static Company getType (String value){
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

    public static List<String> getValuesString(){
        List<String> stringList = new ArrayList<>();
        for (Company w : values()) {
            stringList.add(w.getValue());
        }
        return stringList;
    }

    public static List<Company> getValues(){
        return List.of(values());
    }
}
