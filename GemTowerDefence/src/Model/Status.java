package Model;

/**
 * Created by Sebastian on 2015-11-13.
 */
public class Status {
    private String status;
    private int durationLeft;
    private int damagePerSec;
    private int slowPercent;
    private Tower inflictedBy;
    private Enemy inflictedTo;

    public Status(String status, int durationLeft, int damagePerSec, int slowPercent, Tower inflictedBy, Enemy inflictedTo) {
        this.status = status.toLowerCase();
        this.durationLeft = durationLeft;
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
            case "stun":
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
}
