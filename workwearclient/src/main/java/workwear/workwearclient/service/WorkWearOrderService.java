package workwear.workwearclient.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import workwear.workwearclient.clientApi.WorkShoesOrderApiClient;
import workwear.workwearclient.clientApi.WorkWearOrderApiClient;
import workwear.workwearclient.model.WorkShoesOrder;
import workwear.workwearclient.model.WorkShoesTotal;
import workwear.workwearclient.model.WorkWearOrder;
import workwear.workwearclient.model.WorkWearTotal;
import workwear.workwearclient.model.modelEnum.WorkShoesType;
import workwear.workwearclient.model.modelEnum.WorkWearType;
import workwear.workwearclient.model.modelview.WorkShoesOrderView;
import workwear.workwearclient.model.modelview.WorkShoesTotalView;
import workwear.workwearclient.model.modelview.WorkWearOrderView;
import workwear.workwearclient.model.modelview.WorkWearTotalView;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkWearOrderService {

    private final WorkWearOrderApiClient workWearOrderApiClient;
    private final WorkShoesOrderApiClient workShoesOrderApiClient;
    private final WorkWearReserveService workWearReserveService;


    public List<WorkWearOrderView> createWorkWearOrderViewList(List<WorkWearOrder> workWearOrderList) {
        return workWearOrderList.stream()
                .map(WorkWearOrderView::new)
                .toList();
    }

    public List<WorkShoesOrderView> createWorkShoesOrderViewList(List<WorkShoesOrder> workShoesOrderList){
        return workShoesOrderList.stream()
                .map(WorkShoesOrderView::new)
                .toList();
    }

    public List<WorkWearTotalView> orderWearReplaceAll() {
        List<WorkWearTotal> workWearTotalList = workWearOrderApiClient.findWorkWearAllReplaced();
        return workWearReserveService.createWorWearTotalView(workWearTotalList);
    }

    public List<WorkShoesTotalView> orderShoesReplaceAll() {
        List<WorkShoesTotal> workShoesTotalList = workShoesOrderApiClient.findWorkShoesAllReplaced();
        return workWearReserveService.createWorkShoesTotalView(workShoesTotalList);
    }

    public List<WorkWearOrderView> orderWearDeficitAll() {
        List<WorkWearOrder> workWearOrderList = workWearOrderApiClient.searchForMissingDimensionsAll();
        return createWorkWearOrderViewList(workWearOrderList);
    }

    public List<WorkShoesOrderView> orderShoesDeficitAll() {
        List<WorkShoesOrder> workShoesOrderList = workShoesOrderApiClient.searchForMissingDimensionsAll();
        return createWorkShoesOrderViewList(workShoesOrderList);
    }

    public List<WorkWearTotalView> orderReplaceWearType(String workWearType) {
        WorkWearType workWearType1 = WorkWearType.getType(workWearType);
        List<WorkWearTotal> workWearTotalList = workWearOrderApiClient.findWorkWearReplacedByType(workWearType1);
        return workWearReserveService.createWorWearTotalView(workWearTotalList);
    }

    public List<WorkWearOrderView> orderDeficitWearType(String workWearType) {
        WorkWearType workWearType1 = WorkWearType.getType(workWearType);
        List<WorkWearOrder> workWearOrderList = workWearOrderApiClient.searchForMissingDimensionsByType(workWearType1);
        return createWorkWearOrderViewList(workWearOrderList);
    }

    public List<WorkShoesTotalView> orderShoesTypeReplace(String workShoesType) {
        WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
        List<WorkShoesTotal> workShoesTotalList = workShoesOrderApiClient.findWorShoesReplacedByType(workShoesType1);
        return workWearReserveService.createWorkShoesTotalView(workShoesTotalList);
    }

    public List<WorkShoesOrderView> orderDeficitShoesType(String workShoesType) {
        WorkShoesType workShoesType1 = WorkShoesType.getType(workShoesType);
        List<WorkShoesOrder> workShoesOrderList = workShoesOrderApiClient.searchForMissingDimensionsByType(workShoesType1);
        return createWorkShoesOrderViewList(workShoesOrderList);
    }
}
