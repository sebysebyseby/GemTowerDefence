package Model;

/**
 * Created by Sebastian on 2015-11-13.
 */
public class Status {
    private String status;
    private int durationLeft;
    private int maxDuration;
    private int damagePerSec;
    private int slowPercent;
    private Tower inflictedBy;
    private Enemy inflictedTo;

    public Status(String status, int durationLeft, int damagePerSec, int slowPercent, Tower inflictedBy, Enemy inflictedTo) {
        this.status = status.toLowerCase();
        this.durationLeft = durationLeft;
        this.maxDuration = durationLeft;
        this.damagePerSec = damagePerSec;
        this.slowPercent = slowPercent;
        this.inflictedBy = inflictedBy;
        this.inflictedTo = inflictedTo;
    }

    public String getStatus() {
        return status;
    }

    public int getDurationLeft() {
        return durationLeft;
    }

    public int getDamagePerSec() {
        return damagePerSec;
    }

    public Tower getInflictedBy() {
        return inflictedBy;
    }

    public void tickStatus(){
        switch (status){
            case "stun": // This is implemented in moveEnemy, in the Enemy class (If the enemy has a stun status, it will not move)
                break;
            case "poison":
                break;
            case "slow":
                int maxSpeed = inflictedTo.getMaxSpeed();
                int newSpeed = maxSpeed + maxSpeed*slowPercent/100;
                if (inflictedTo.getCurrentSpeed() < newSpeed){
                    inflictedTo.setCurrentSpeed(newSpeed);
                }
                break;
        }
        durationLeft--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status1 = (Status) o;

        return !(status != null ? !status.equals(status1.status) : status1.status != null);

    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }

}

