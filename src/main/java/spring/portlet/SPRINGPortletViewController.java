package spring.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import spring.portlet.service.VacancyService;
import spring.portlet.util.PageUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping(value = "VIEW")
public class SPRINGPortletViewController {

    private VacancyService service;

    @Autowired
    public SPRINGPortletViewController(VacancyService service) {
        this.service = service;
    }

    @RenderMapping
    public String view(RenderRequest renderRequest, RenderResponse response, Model model) {
        int page = 1;
        PageUtil pageByNumber;

        String param = ParamUtil.getString(renderRequest, "page");

        if (param.length() > 0) page = Integer.parseInt(param);
        pageByNumber = service.getPageByNumber(page);

        int currentPage = pageByNumber.getCurrentPage();
        int firstPage = pageByNumber.getFirstPage();
        int lastPage = pageByNumber.getLastPage();
        int nextPage = pageByNumber.getNextPage();
        int previousPage = pageByNumber.getPreviousPage();
        model.addAttribute("url", renderRequest);
        model.addAttribute("vacs", pageByNumber.getVacancies());
        model.addAttribute("current_page", currentPage);
        model.addAttribute("first_page", firstPage);
        model.addAttribute("last_page", lastPage);
        model.addAttribute("next_page", nextPage);
        model.addAttribute("previous_page", previousPage);
        model.addAttribute("name", "FUUUCK");
        return "view";
    }
}