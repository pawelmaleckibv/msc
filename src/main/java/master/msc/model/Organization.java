package master.msc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization extends MscBaseEntity {

    private String name;

    @Lob
    @Column(length=512)
    private String mission;

    @Lob
    @Column(length=512)
    private String vision;

    @Lob
    @Column(length=512)
    private String strategy;
    private String businessLine;

    @Embedded
    private WhiteLabelParam whiteLabelParam = new WhiteLabelParam();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "organization")
    private List<Employment> organizationAdmins = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "organization")
    private OrganizationLogo organizationLogo;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityLevel currentMaturityLevel;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityLevel targetMaturityLevel;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "organization_x_maturity_level",
            joinColumns = {@JoinColumn(name = "organization_id")},
            inverseJoinColumns = {@JoinColumn(name = "maturity_level_id")}
    )
    private List<MaturityLevel> historicalMaturityLevels = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "organization")
    private List<BusinessUnit> businessUnits = new ArrayList<>();

    private boolean isDeleted;

    public Organization() {
    }

    public List<Employment> getOrganizationAdmins() {
        return organizationAdmins;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public void setOrganizationAdmins(List<Employment> organizationAdmins) {
        this.organizationAdmins = organizationAdmins;
    }

    public OrganizationLogo getOrganizationLogo() {
        return organizationLogo;
    }

    public void setOrganizationLogo(OrganizationLogo organizationLogo) {
        this.organizationLogo = organizationLogo;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Organization(String name) {
        this.name = name;
    }

    public MaturityLevel getCurrentMaturityLevel() {
        return currentMaturityLevel;
    }

    public void setCurrentMaturityLevel(MaturityLevel currentMaturityLevel) {
        this.currentMaturityLevel = currentMaturityLevel;
    }

    public MaturityLevel getTargetMaturityLevel() {
        return targetMaturityLevel;
    }

    public void setTargetMaturityLevel(MaturityLevel targetMaturityLevel) {
        this.targetMaturityLevel = targetMaturityLevel;
    }

    public List<MaturityLevel> getHistoricalMaturityLevels() {
        return historicalMaturityLevels;
    }

    public void setHistoricalMaturityLevels(List<MaturityLevel> historicalMaturityLevels) {
        this.historicalMaturityLevels = historicalMaturityLevels;
    }

    public List<BusinessUnit> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<BusinessUnit> businessUnits) {
        this.businessUnits = businessUnits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WhiteLabelParam getWhiteLabelParam() {
        return whiteLabelParam;
    }

    public void setWhiteLabelParam(WhiteLabelParam whiteLabelParam) {
        this.whiteLabelParam = whiteLabelParam;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
