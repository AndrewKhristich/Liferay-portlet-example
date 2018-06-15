package spring.portlet.util;

import lombok.Data;
import spring.portlet.model.Vacancy;

import java.util.List;

@Data
public class PageUtil {

    private List<Vacancy> vacancies;
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int firstPage;
    private int lastPage;

    public PageUtil() {
    }

    public PageUtil initPage(int current) {
        currentPage = current;
        if (current == 1) {
            previousPage = 1;
        } else previousPage = current - 1;
        nextPage = current + 1;
        return this;
    }
}
