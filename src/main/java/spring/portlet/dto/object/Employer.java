package spring.portlet.dto.object;

public class Employer {
    private Logo_urls logo_urls;
    private String vacancies_url;
    private String name;
    private String url;
    private String alternate_url;
    private int id;
    private boolean trusted;

    public Employer() {
    }

    public String getName() {
        return name;
    }
}
