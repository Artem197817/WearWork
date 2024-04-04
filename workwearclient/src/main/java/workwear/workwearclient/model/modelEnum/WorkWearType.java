package workwear.workwearclient.model.modelEnum;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

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

    public static List<WorkWearType> getValues(){
       return List.of(values());
    }

    public static List<String> getValuesString(){
        return  Stream.of(values())
                .map(WorkWearType::getValue)
                .toList();
    }
}
