package workwear.workwear;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearTotal;
import workwear.workwear.model.enumerated.WorkWearHeight;
import workwear.workwear.model.enumerated.WorkWearSize;
import workwear.workwear.model.enumerated.WorkWearType;
import workwear.workwear.service.WorkWearIssuedService;
import workwear.workwear.service.WorkWearService;
import workwear.workwear.service.WorkWearTotalService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WorkWearTotalServiceTest {

    @MockBean
    private  WorkWearService workWearService;
    @MockBean
    private  WorkWearIssuedService workWearIssuedService;
    @Autowired
    private  WorkWearTotalService workWearTotalService;

    @Test
    @DisplayName("typeSortedNumberTest")
    public void typeSortedNumberTest(){
        List<WorkWear> workWearList = filterWorkWearByType();
        List<WorkWearTotal> workWearTotalList = workWearTotalService.typeSortedNumber(workWearList, WorkWearType.WORKER_SUIT);
        assertEquals(workWearTotalList.get(0).getNumber(), 1);
        assertEquals(workWearTotalList.size(), 2);
    }
    @Test
    @DisplayName("sizeSortedNumberTest")
    public void sizeSortedNumberTest(){
        List<WorkWear> workWearList = filterWorkWearBySize();
        List<WorkWearTotal> workWearTotalList = workWearTotalService.sizeSortedNumber(workWearList, WorkWearSize.S_60_62);
        assertEquals(workWearTotalList.get(0).getNumber(), 1);
        assertEquals(workWearTotalList.size(), 1);
        assertEquals(workWearTotalList.get(0).getWorkWearType(), WorkWearType.WELDER_SUIT);
    }

    private List<WorkWear> createWorkWearList(){
        List<WorkWear> workWearList = new ArrayList<>();
        WorkWear workWear1 = new WorkWear();
        workWear1.setModelWorkWear("Delta");
        workWear1.setWorkWearType(WorkWearType.WORKER_SUIT);
        workWear1.setWorkWearSize(WorkWearSize.S_52_54);
        workWear1.setId(1L);
        workWear1.setWorkWearHeight(WorkWearHeight.H_158_164);
        workWearList.add(workWear1);

        WorkWear workWear2 = new WorkWear();
        workWear2.setModelWorkWear("Delta");
        workWear2.setWorkWearType(WorkWearType.WORK_COAT);
        workWear2.setWorkWearSize(WorkWearSize.S_44_46);
        workWear2.setId(2L);
        workWear2.setWorkWearHeight(WorkWearHeight.H_170_176);
        workWearList.add(workWear2);

        WorkWear workWear3 = new WorkWear();
        workWear3.setModelWorkWear("Delta");
        workWear3.setWorkWearType(WorkWearType.WELDER_SUIT);
        workWear3.setWorkWearSize(WorkWearSize.S_56_58);
        workWear3.setId(3L);
        workWear3.setWorkWearHeight(WorkWearHeight.H_182_188);
        workWearList.add(workWear3);

        WorkWear workWear4 = new WorkWear();
        workWear4.setModelWorkWear("Delta");
        workWear4.setWorkWearType(WorkWearType.WORKER_SUIT);
        workWear4.setWorkWearSize(WorkWearSize.S_32_34);
        workWear4.setId(1L);
        workWear4.setWorkWearHeight(WorkWearHeight.H_158_164);
        workWearList.add(workWear4);

        WorkWear workWear5 = new WorkWear();
        workWear5.setModelWorkWear("Delta");
        workWear5.setWorkWearType(WorkWearType.WORK_COAT);
        workWear5.setWorkWearSize(WorkWearSize.S_40_42);
        workWear5.setId(2L);
        workWear5.setWorkWearHeight(WorkWearHeight.H_170_176);
        workWearList.add(workWear5);

        WorkWear workWear6 = new WorkWear();
        workWear6.setModelWorkWear("Delta");
        workWear6.setWorkWearType(WorkWearType.WELDER_SUIT);
        workWear6.setWorkWearSize(WorkWearSize.S_60_62);
        workWear6.setId(3L);
        workWear6.setWorkWearHeight(WorkWearHeight.H_182_188);
        workWearList.add(workWear6);
        return workWearList;
    }

    private List<WorkWear> filterWorkWearBySize(){
        return createWorkWearList().stream()
                .filter(w-> WorkWearSize.S_60_62.equals(w.getWorkWearSize()))
                .toList();
    }

    private List<WorkWear> filterWorkWearByType(){
        return createWorkWearList().stream()
                .filter(w-> WorkWearType.WORKER_SUIT.equals(w.getWorkWearType()))
                .toList();
    }
}
