package master.msc.model;

import javax.persistence.*;

@Entity
@Table(name = "static_translations")
public class StaticTranslation extends MscBaseEntity {

    private String name;
    private String status;
    private String uiKey;

    @JoinColumn(name = "language")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Language language;

    @JoinColumn(name = "translation_module")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private TranslationModule translationModule;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUiKey() {
        return uiKey;
    }

    public void setUiKey(String uiKey) {
        this.uiKey = uiKey;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language){
        this.language = language;
    }

    public TranslationModule getTranslationModule() {
        return translationModule;
    }

    public void setTranslationModule(TranslationModule translationModule) {
        this.translationModule = translationModule;
    }
}