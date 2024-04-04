package workwear.workwearclient.model.modelEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

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

    public static List<Company> getValues(){
        return List.of(values());
    }

    public static List<String> getValuesString(){
        return  Stream.of(values())
                .map(Company::getValue)
                .toList();
    }
}
