package workwear.workshoes.service;

import workwear.workshoes.model.WorkShoesTotal;
import workwear.workshoes.model.enumerated.WorkShoesType;

import java.util.List;

public interface WorkShoesTotalService {


    List<WorkShoesTotal> findWorkShoesByTypeSortedNumber(WorkShoesType workShoesType);

    List<WorkShoesTotal> findWorkShoesBySizeSortedNumber(Integer size);

    List<WorkShoesTotal> findAllWorkShoesSortedNumber();

}