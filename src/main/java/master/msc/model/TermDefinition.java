package master.msc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "term_definitions")
public class TermDefinition extends MscBaseEntity {
    private String term;

    @Lob
    @Column(length=1024)
    private String definition;
    private UUID languageID;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public UUID getLanguageID() {
        return languageID;
    }

    public void setLanguageID(UUID languageName) {
        this.languageID = languageName;
    }
}
