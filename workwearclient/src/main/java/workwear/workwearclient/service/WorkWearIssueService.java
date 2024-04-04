package workwear.workwearclient.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.model.WorkWearIssued;
import workwear.workwearclient.model.modelview.WearIssuedView;


@Service
@AllArgsConstructor
public class WorkWearIssueService {


    public WorkWearIssued createWorkWearIssued(WearIssuedView wearIssuedView){
        return new WorkWearIssued(wearIssuedView.getEmployeeId(),wearIssuedView.getWorkWearId(),wearIssuedView.getMonthPeriod());
    }

}
