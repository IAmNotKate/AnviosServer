package database.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "device_bound", schema = "anvios")
public class DeviceBoundEntity {
    private String deviceToken;
    private int userId;

    @Id
    @Column(name = "device_token", nullable = false, length = 255)
    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceBoundEntity that = (DeviceBoundEntity) o;
        return Objects.equals(deviceToken, that.deviceToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceToken);
    }
}
