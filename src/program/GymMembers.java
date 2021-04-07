package program;

public class GymMembers {
    private String membershipNum;
    private String name;
    private String Date;
    private String membershipType;
    private String schoolName;
    private String age;

    public GymMembers(){
        super();
    }

    public GymMembers(String membershipNum,String name,String Date,String membershipType,String schoolName,String age){
        super();
        this.membershipNum=membershipNum;
        this.name=name;
        this.Date=Date;
        this.membershipType=membershipType;
        this.schoolName=schoolName;
        this.age=age;

    }


    public String getMembershipNum() {
        return membershipNum;
    }

    public void setMembershipNum(String membershipNum) {
        this.membershipNum = membershipNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
