package spring.portlet.converter.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import spring.portlet.converter.DtoToEntityConverter;
import spring.portlet.dto.HeadHunterDto;
import spring.portlet.dto.object.Items;
import spring.portlet.model.Vacancy;
import spring.portlet.util.Path;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToEntityConverterImpl implements DtoToEntityConverter {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    }

    @Override
    public URL appendUrl(int index, URL oldUrl) throws MalformedURLException {
        return new URL(oldUrl.getProtocol(), oldUrl.getHost(), oldUrl.getFile() + index);
    }

    @Override
    public List<Items> getAllNodesFromHeadHunterApi() throws IOException {
        List<Items> list = new ArrayList<>();
        HeadHunterDto headHunterDto;
        URL url1 = new URL(Path.HH_API);
//        int pageCounter = mapper.readValue(url1, HeadHunterDto.class).getPages();
        int pageCount = mapper.readTree(appendUrl(0, url1)).get("pages").asInt();
        for (int i = 0; i < pageCount; i++) {
            headHunterDto = mapper.readValue(appendUrl(i, url1), HeadHunterDto.class);
            list.addAll(headHunterDto.getItems());
        }
        return list;
    }

    @Override
    public List<Vacancy> getFinalVacancyList() {
        List<Vacancy> vacancyList = new ArrayList<>();
        List<Items> itemsList = null;
        try {
            itemsList = getAllNodesFromHeadHunterApi();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (itemsList != null) {
            for (Items items : itemsList) {
                Vacancy vacancy = new Vacancy();
                vacancy.setId(items.getId());
                vacancy.setCompanyName(items.getEmployer().getName());
                vacancy.setVacancyName(items.getName());
                if (items.getSalary() == null) {
                    vacancy.setCurrency(null);
                    vacancy.setPaymentFrom(null);


                    vacancy.setPaymentTo(null);
                } else {
                    vacancy.setCurrency(items.getSalary().getCurrency());
                    vacancy.setPaymentFrom(items.getSalary().getFrom());
                    vacancy.setPaymentTo(items.getSalary().getTo());
                }
                vacancy.setPublishedAt(items.getPublished_at());
                vacancy.setUrl(items.getAlternate_url());
                vacancyList.add(vacancy);
            }
        } else throw new NullPointerException("Vacancy List is NULL");
        return vacancyList;
    }
}
