public class Manager extends Information_employees {
    public Manager(String Name,String Last_name , int Age,  String Position , String Special_skills) {
        setValues(Name,Last_name ,Age,Position ,Special_skills  );
        //System.out.println(getValues());
    }
    public Manager(String change_start,String Job_responsibilities,String Lunch,String change_finish) {
        setValues2(change_start,Job_responsibilities,Lunch,change_finish);

    }
}
