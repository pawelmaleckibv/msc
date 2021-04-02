package master.msc.model;

import master.msc.model.base.BaseEmbeddable;

import javax.persistence.Embeddable;

@Embeddable
public class WhiteLabelParam extends BaseEmbeddable {

    private String primaryColor;

    private String secondaryColor;

    public WhiteLabelParam() {
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }
}
