package be.tdedobbeleer.sharing_is_caring.entities;

public class HaContext {
    private String id;
    private String parent_id = null;
    private String user_id = null;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getUser_id() {
        return user_id;
    }

    // Setter Methods

    public void setId( String id ) {
        this.id = id;
    }

    public void setParent_id( String parent_id ) {
        this.parent_id = parent_id;
    }

    public void setUser_id( String user_id ) {
        this.user_id = user_id;
    }
}
