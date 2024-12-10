public  class Driver extends Information_employees {
    public Driver(String Name,String Last_name , int Age,  String Position , String Special_skills) {
        super(Name,Last_name ,Age,Position ,Special_skills  );
        //System.out.println(getValues());
        String values = getValues();
    }
    public Driver(String change_start,String Job_responsibilities,String Lunch,String change_finish) {
        super(change_start,Job_responsibilities,Lunch,change_finish);

    }
}
