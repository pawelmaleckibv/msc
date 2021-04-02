package master.msc.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "attachments")
public class Attachment extends MscBaseEntity {

    private String attachmentName;

    private String attachmentType;

    public Attachment() {
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}

