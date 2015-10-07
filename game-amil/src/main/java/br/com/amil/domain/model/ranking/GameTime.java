package br.com.amil.domain.model.ranking;

import java.util.Date;

public class GameTime {
    private Date startTime;
    private Date endTime;

    private GameTime(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public static GameTime create(Date startTime, Date endTime) {
        return new GameTime(startTime, endTime);
    }
}
