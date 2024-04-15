package workwear.workwear.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import workwear.workwear.model.WorkWear;
import workwear.workwear.model.WorkWearIssued;
import workwear.workwear.model.WorkWearIssuedView;
import workwear.workwear.repository.WorkWearIssuedRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class WorkWearIssuedServiceImpl implements WorkWearIssuedService {

    private final WorkWearIssuedRepository workWearIssuedRepository;
    private final WorkWearService workWearService;

    /**
     метод, который возвращает список всех выданных рабочих костюмов.
     */
    @Override
    public List<WorkWearIssued> findAllWorkWearIssued() {
        return workWearIssuedRepository.findAll();
    }

    /**
     метод, который возвращает выданную рабочую одежду по указанному идентификатору.
     */
    @Override
    public WorkWearIssued findWorkWearIssuedById(Long id) {
        Optional<WorkWearIssued> workWearIssued = workWearIssuedRepository.findById(id);
        return workWearIssued.orElse(null);
    }

    /**
     метод, который сохраняет выданную рабочую одежду в базе данных.
     */
    @Override
    @Transactional
    public String saveWorkWearIssued(WorkWearIssued workWearIssued) {
        WorkWear workWear = workWearService.findById(workWearIssued.getWorkWearId());
        workWear.setWorkWearStatus(WorkWear.ISSUE);
        workWearService.updateWorkWear(workWear);
        workWearIssuedRepository.save(workWearIssued);
        return "WorkWearIssued save";
    }

    /**
     метод, который обновляет информацию  о выданной рабочей одежде в базе данных.
     */
    @Override
    @Transactional
    public String updateWorkWearIssued(WorkWearIssued workWearIssued) {
        workWearIssuedRepository.save(workWearIssued);
        return "WorkWearIssued update";
    }

    /**
     метод, который удаляет выданную рабочую одежду из базы данных по указанному идентификатору.
     */
    @Override
    @Transactional
    public void deleteWorkWearIssuedById(Long id) {
        try {
            workWearService.deleteWorkWearById(findWorkWearIssuedById(id).getWorkWearId());
            workWearIssuedRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Error id");
        }
    }

    /**
     * Метод для поиска всей выданной рабочей одежды по идентификатору сотрудника
     * @param id идентификатор сотрудника
     * @return список выданной рабочей одежды
     */
    @Override
    public List<WorkWearIssued> findWorkWearIssuedByEmployeeId(Long id) {
        return workWearIssuedRepository.findWorkWearIssuedByEmployeeId(id);
    }

    /**
     * Метод для поиска всей выданной рабочей одежды, которую необходимо заменить
     * @return список выданной рабочей одежды, которую необходимо заменить
     */
    @Override
    public List<WorkWearIssued> findWorkWearIssuedToBeReplaced() {
        LocalDate localDateControl = LocalDate.now().plusMonths(1);
        return workWearIssuedRepository.findAll().stream().
                filter(workWearIssued -> workWearIssued.getReplacementDate().isBefore(localDateControl))
                .toList();
    }

    /**
     * Метод для поиска всей выданной рабочей одежды и связанных с ней объектов WorkWear для сотрудника по идентификатору
     * @param id идентификатор сотрудника
     * @return список выданных рабочих одежд и связанных с ними объектов WorkWear
     */
    @Override
    public List<WorkWearIssuedView> findWorkWearIssuedEmployee(Long id) {
        List<WorkWearIssued> workWearIssuedList = findWorkWearIssuedByEmployeeId(id);
        if (workWearIssuedList.isEmpty()) return new ArrayList<>();
        List<WorkWearIssuedView> workWearIssuedViewList = new ArrayList<>();
        for (WorkWearIssued wwi: workWearIssuedList){
            workWearIssuedViewList.add(new WorkWearIssuedView(wwi,workWearService.findById(wwi.getWorkWearId())));
        }
        return workWearIssuedViewList;
    }

    /**
     * Метод для поиска выданной рабочей одежды по идентификатору рабочей одежды
     * @param id идентификатор рабочей одежды
     * @return объект выданной рабочей одежды
     */
    @Override
    public WorkWearIssued findWorkWearIssuedByWorkWearId(Long id) {
        return workWearIssuedRepository.findWorkWearIssuedByWorkWearId(id);
    }

    /**
     * Метод для возврата рабочей одежды на склад
     * @param id идентификатор выданной рабочей одежды
     * @return сообщение о возврате рабочей одежды на склад
     */
    @Override
    @Transactional
    public String returnWorkWearOnStorage(@PathVariable Long id){
        WorkWearIssued workWearIssued = findWorkWearIssuedById(id);
        WorkWear workWear = workWearService.findById(workWearIssued.getWorkWearId());
        workWear.setWorkWearStatus(WorkWear.NOT_ISSUE);
        workWearService.updateWorkWear(workWear);
        workWearIssuedRepository.deleteById(id);
        return  workWear.getWorkWearType().getValue() + " размер "
                + workWear.getWorkWearSize().getValue() + " возвращен на склад";
    }
}

