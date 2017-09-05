package ems.model;

import java.util.Date;

import javax.validation.constraints.Size;

public class Todo {
    private int id;

    @Size( min = 10, message = "Enter at least 10 characters." )
    private String desc;

    private String user;
    private Date targetDate;
    private boolean isDone;

    public Todo() {
    }

    public Todo( int id, String user, String desc, Date targetDate, boolean isDone ) {
        super();
        this.id = id;
        this.desc = desc;
        this.user = user;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc( String desc ) {
        this.desc = desc;
    }

    public String getUser() {
        return user;
    }

    public void setUser( String user ) {
        this.user = user;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate( Date targetDate ) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone( boolean isDone ) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Todo other = (Todo) obj;
        if ( id != other.id )
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", desc=" + desc + ", user=" + user + ", targetDate=" + targetDate + ", isDone=" + isDone + "]";
    }

}
