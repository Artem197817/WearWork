package workwear.workwearclient.model.modelEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

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

    public static  List<WorkShoesType> getValues(){
        return List.of(values());
    }

    public static List<String> getValuesString(){
        return  Stream.of(values())
                .map(WorkShoesType::getValue)
                .toList();
    }
}
