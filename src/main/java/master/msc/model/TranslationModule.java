package master.msc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "translation_modules")
public class TranslationModule extends MscBaseEntity {

    private String name;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "translationModule")
    private List<StaticTranslation> staticTranslations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StaticTranslation> getStaticTranslations() {
        return staticTranslations;
    }

    public void setStaticTranslations(List<StaticTranslation> answers) {
        this.staticTranslations = answers;
    }

}