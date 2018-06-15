package spring.portlet.converter;

import spring.portlet.dto.object.Items;
import spring.portlet.model.Vacancy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public interface DtoToEntityConverter {
    URL appendUrl(int index, URL oldUrl) throws MalformedURLException;

    List<Items> getAllNodesFromHeadHunterApi() throws IOException;

    List<Vacancy> getFinalVacancyList();
}
