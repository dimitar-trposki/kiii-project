package mk.ukim.finki.emc.bookeshop.dto;

import lombok.Data;

@Data
public class CountryDto {

    private String name;

    private String continent;

    public CountryDto() {
    }

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
