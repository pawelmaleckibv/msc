package master.msc.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "languages")
public class Language extends MscBaseEntity {

    private Locale locale;
    private Boolean isMaster;

    public List<StaticTranslation> getStaticTranslations() {
        return staticTranslations;
    }

    public void setStaticTranslations(List<StaticTranslation> staticTranslations) {
        this.staticTranslations = staticTranslations;
    }

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "language")
    private List<StaticTranslation> staticTranslations = new ArrayList<>();

    public void setLocale(String language) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setMaster(String language) {
        this.isMaster = isMaster;
    }

    public Boolean getLocaleMaster() {
        return isMaster;
    }
}
