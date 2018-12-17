package learn.epam.mlhh.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.math.BigDecimal;

//@Data
public class Search {

//    @NotBlank(message="ageMin is required")
    private Integer ageMin;

//    @NotBlank(message="ageMax is required")
    private Integer ageMax;

//    @NotBlank(message="gender is required")
//    @Size(max=1, message="You can select only one character")
    private String gender;

//    @NotBlank(message="region is required")
    private String region;

//    @NotBlank(message="salaryMin is required")
    private BigDecimal salaryMin;

//    @NotBlank(message="salaryMax is required")
    private BigDecimal salaryMax;

//    @NotBlank(message="developer is required")
    private String developer;

//    @NotBlank(message="experienceMin is required")
    private Integer experienceMin;

//    @NotBlank(message="experienceMax is required")
    private Integer experienceMax;

//    @NotBlank(message="keywords is required")
    private String keywords;

    public Search() {
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Integer getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(Integer experienceMin) {
        this.experienceMin = experienceMin;
    }

    public Integer getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(Integer experienceMax) {
        this.experienceMax = experienceMax;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }




}
