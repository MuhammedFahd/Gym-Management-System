package program;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day,int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }

    //string representation of the date
    public String stringDate(){
        StringBuilder s=new StringBuilder();
        if(this.day<10){
            s.append("0");
        }
        s.append(String.valueOf(this.day));
        s.append("-");
        if(this.month<10){
            s.append("0");
        }
        s.append(String.valueOf(this.month));
        s.append("-");
        s.append(String.valueOf(this.year));

        return s.toString();
    }

    //method to check the validity of the date entered
    public boolean isValidDate(){
        boolean result=true;
        if(this.day<1 || this.day>31){
            result=false;
        }
        if(this.month<1 || this.month>12){
            result=false;
        }
        if(this.year<0){
            result=false;
        }
        return result;
    }

}
