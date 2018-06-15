package spring.portlet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.portlet.converter.DtoToEntityConverter;
import spring.portlet.dao.VacancyDao;
import spring.portlet.model.Vacancy;
import spring.portlet.service.VacancyService;
import spring.portlet.util.MyDataSource;
import spring.portlet.util.PageUtil;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    private VacancyDao vacancyDao;
    private DtoToEntityConverter converter;
    private MyDataSource dataSource;

    @Autowired
    public VacancyServiceImpl(VacancyDao vacancyDao, DtoToEntityConverter converter, MyDataSource dataSource) {
        this.vacancyDao = vacancyDao;
        this.converter = converter;
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        dataSource.createAllTables();
        List<Vacancy> finalVacancyList = converter.getFinalVacancyList();
        vacancyDao.saveAllVacancies(finalVacancyList);
    }

    @Override
    public PageUtil getPageByNumber(int number) {
        PageUtil page = new PageUtil();
        page.initPage(number);
        int step = 30;
        List<Vacancy> vacanciesBeyond = vacancyDao.findAllVacanciesBeyond(page.getCurrentPage(), step);
        page.setVacancies(vacanciesBeyond);
        page.setFirstPage(1);
        page.setLastPage(vacancyDao.countVacancies() / step);
        return page;
    }
}


