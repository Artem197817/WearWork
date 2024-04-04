package workwear.workwearclient.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkShoesIssued;
import workwear.workwearclient.model.modelview.ShoesIssuedView;


@Service
@AllArgsConstructor
public class WorkShoesIssuedService {


    public WorkShoesIssued createWorkShoesIssue(ShoesIssuedView shoesIssuedView){
        return new WorkShoesIssued(shoesIssuedView.getEmployeeId(),shoesIssuedView.getWorkShoesId(),shoesIssuedView.getMonthPeriod());
    }
}


