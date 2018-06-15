package spring.portlet.dto.object;


public class Salary {
    private Integer to;
    private boolean gross;
    private Integer from;
    private String currency;

    public Salary() {
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getFrom() {
        return from;
    }
}
