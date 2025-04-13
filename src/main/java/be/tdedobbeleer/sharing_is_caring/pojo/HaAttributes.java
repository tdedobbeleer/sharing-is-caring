package be.tdedobbeleer.sharing_is_caring.pojo;

public class HaAttributes {
    private String state_class;
    private String unit_of_measurement;
    private String device_class;
    private String friendly_name;


    // Getter Methods

    public String getState_class() {
        return state_class;
    }

    public String getUnit_of_measurement() {
        return unit_of_measurement;
    }

    public String getDevice_class() {
        return device_class;
    }

    public String getFriendly_name() {
        return friendly_name;
    }

    // Setter Methods

    public void setState_class( String state_class ) {
        this.state_class = state_class;
    }

    public void setUnit_of_measurement( String unit_of_measurement ) {
        this.unit_of_measurement = unit_of_measurement;
    }

    public void setDevice_class( String device_class ) {
        this.device_class = device_class;
    }

    public void setFriendly_name( String friendly_name ) {
        this.friendly_name = friendly_name;
    }

}
