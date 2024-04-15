package workwear.workshoes.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workshoes.model.WorkShoes;
import workwear.workshoes.model.WorkShoesIssued;
import workwear.workshoes.model.WorkShoesIssuedView;
import workwear.workshoes.repository.WorkShoesIssuedRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkShoesIssuedServiceImpl implements WorkShoesIssuedService {

    private final WorkShoesIssuedRepository workShoesIssuedRepository;
    private final WorkShoesService workShoesService;

    /**
     метод возвращает список всех записей о выдаче рабочей обуви.
     */
    @Override
    public List<WorkShoesIssued> findAllWorkShoesIssued() {
        return workShoesIssuedRepository.findAll();
    }

    /**
     Метод сохраняет новую запись о выдаче рабочей обуви.
     Сначала он обновляет статус соответствующей рабочей обуви на "выдано", а затем сохраняет запись о выдаче рабочей обуви.
     */
    @Override
    @Transactional
    public String saveWorkShoesIssued(WorkShoesIssued workShoesIssued) {
        WorkShoes workShoes = workShoesService.findById((workShoesIssued.getWorkShoesId()));
        workShoes.setWorkShoesStatus(WorkShoes.ISSUE);
        workShoesService.updateWorkShoes(workShoes);
        workShoesIssuedRepository.save(workShoesIssued);
        return "WorkShoesIssued save";
    }

    /**
     метод возвращает запись о выдаче рабочей обуви по ее идентификатору.
     */
    @Override
    public WorkShoesIssued findById(Long id) {
        Optional<WorkShoesIssued> workShoesIssued = workShoesIssuedRepository.findById(id);
        return workShoesIssued.orElse(null);
    }

    /**
     метод обновляет запись о выдаче рабочей обуви.
     */
    @Override
    @Transactional
    public WorkShoesIssued updateWorkShoesIssued(WorkShoesIssued workShoesIssued) {
        return workShoesIssuedRepository.save(workShoesIssued);
    }

    /**
     метод удаляет запись о выдаче рабочей обуви и соответствующую запись о рабочей обуви.
     */
    @Override
    @Transactional
    public void deleteWorkShoesIssued(Long id) {
        workShoesService.deleteWorkShoes(findById(id).getWorkShoesId());
        workShoesIssuedRepository.deleteById(id);
    }

    /**
     метод возвращает список записей о выдаче рабочей обуви для конкретного сотрудника.
     */
    @Override
    public List<WorkShoesIssued> findWorkShoesIssuedByEmployeeId(Long id) {
        return workShoesIssuedRepository.findWorkShoesIssuedByEmployeeId(id);
    }

    /**
     метод возвращает список записей о выдаче рабочей обуви, которые необходимо заменить в ближайшее время.
     */
    @Override
    public List<WorkShoesIssued> findWorkShoesToBeReplaced() {
        LocalDate localDateControl = LocalDate.now().plusMonths(1);
        return workShoesIssuedRepository.findAll().stream().
                filter(workWearIssued -> workWearIssued.getReplacementDate().isBefore(localDateControl))
                .toList();
    }

    /**
     метод возвращает запись о выдаче рабочей обуви по идентификатору рабочей обуви.
     */
    @Override
    public WorkShoesIssued findWorkShoesIssuedByWorkShoesId(Long id) {
        return workShoesIssuedRepository.findWorkShoesIssuedByWorkShoesId(id);
    }

    /**
     метод возвращает список записей о выдаче рабочей обуви
     и соответствующую информацию о рабочей обуви для конкретного сотрудника.
     */
    @Override
    public List<WorkShoesIssuedView> findWorkShoesIssuedEmployee(Long id) {
        List<WorkShoesIssued> workShoesIssuedList = findWorkShoesIssuedByEmployeeId(id);
        List<WorkShoesIssuedView> workShoesIssuedViewList = new ArrayList<>();
        if (workShoesIssuedList.isEmpty()) return workShoesIssuedViewList;
        for (WorkShoesIssued wsi : workShoesIssuedList) {
            workShoesIssuedViewList.add(new WorkShoesIssuedView(wsi, workShoesService.findById(wsi.getWorkShoesId())));
        }
        return workShoesIssuedViewList;
    }

    /**
     метод возвращает рабочую обувь в хранилище и удаляет соответствующую запись о выдаче рабочей обуви.
     */
    @Override
    @Transactional
    public String returnWorkShoesOnStorage(@PathVariable Long workShoesIssuedId) {
        WorkShoesIssued workShoesIssued = findById(workShoesIssuedId);
        WorkShoes workShoes = workShoesService.findById(workShoesIssued.getWorkShoesId());
        workShoes.setWorkShoesStatus(WorkShoes.NOT_ISSUE);
        workShoesService.updateWorkShoes(workShoes);
        workShoesIssuedRepository.deleteById(workShoesIssued.getId());
        return workShoes.getWorkShoesType().getValue() + " размер "
                + workShoes.getWorkShoesSize() + " возвращен на склад";
    }
}