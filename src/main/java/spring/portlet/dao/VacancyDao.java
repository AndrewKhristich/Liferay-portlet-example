package spring.portlet.dao;

import spring.portlet.model.Vacancy;

import java.util.List;

public interface VacancyDao {

    void saveAllVacancies(List<Vacancy> list);

    List<Vacancy> findAllVacanciesBeyond(int index, int step);

    int countVacancies();

}
