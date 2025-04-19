package be.tdedobbeleer.sharing_is_caring.entities;

public class HaEntity {
    private String entity_id;
    private String state;
    HaAttributes AttributesObject;
    private String last_changed;
    private String last_reported;
    private String last_updated;
    HaContext ContextObject;


    // Getter Methods

    public String getEntity_id() {
        return entity_id;
    }

    public String getState() {
        return state;
    }

    public HaAttributes getAttributes() {
        return AttributesObject;
    }

    public String getLast_changed() {
        return last_changed;
    }

    public String getLast_reported() {
        return last_reported;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public HaContext getContext() {
        return ContextObject;
    }

    // Setter Methods

    public void setEntity_id( String entity_id ) {
        this.entity_id = entity_id;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public void setAttributes( HaAttributes attributesObject ) {
        this.AttributesObject = attributesObject;
    }

    public void setLast_changed( String last_changed ) {
        this.last_changed = last_changed;
    }

    public void setLast_reported( String last_reported ) {
        this.last_reported = last_reported;
    }

    public void setLast_updated( String last_updated ) {
        this.last_updated = last_updated;
    }

    public void setContext( HaContext contextObject ) {
        this.ContextObject = contextObject;
    }
}
