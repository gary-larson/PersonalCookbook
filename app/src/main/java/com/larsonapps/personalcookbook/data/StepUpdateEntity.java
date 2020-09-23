package com.larsonapps.personalcookbook.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "step_updates")
public class StepUpdateEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "step_update_id")
    private int StepUpdateId;
    @ColumnInfo(name = "step_id")
    private int StepId;
    @ColumnInfo(name = "update")
    private String update;

    /**
     * Constructor for all variables
     * @param stepUpdateId to set
     * @param stepId to set
     * @param update to set
     */
    public StepUpdateEntity(int stepUpdateId, int stepId, String update) {
        StepUpdateId = stepUpdateId;
        StepId = stepId;
        this.update = update;
    }

    /**
     * Getter for step update id
     * @return step update id
     */
    public int getStepUpdateId() {
        return StepUpdateId;
    }

    /**
     * Getter for step id
     * @return step id
     */
    public int getStepId() {
        return StepId;
    }

    /**
     * Getter for update
     * @return update
     */
    public String getUpdate() {
        return update;
    }

    /**
     * Setter for update
     * @param update to set
     */
    public void setUpdate(String update) {
        this.update = update;
    }
}
