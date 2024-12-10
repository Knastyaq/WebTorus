public  class Information_employees {
    public String Name,Last_name, Position , Special_skills;
    public  int Age ;
    public String change_start , Job_responsibilities , Lunch , change_finish ;


    public  Information_employees(){}



    public Information_employees(String Name,String Last_name , int Age,  String Position , String Special_skills) {
    setValues(Name,Last_name ,Age,Position ,Special_skills  );
    //System.out.println(getValues());
    }

    public Information_employees(String change_start,String Job_responsibilities,String Lunch,String change_finish) {
        setValues2(change_start,Job_responsibilities,Lunch,change_finish);

    }



    public void setValues(String Name,String Last_name , int Age,  String Position , String Special_skills  ) {
        this.Name = Name ;
        this.Last_name = Last_name ;
        this.Position = Position;
        this.Special_skills = Special_skills;
        this.Age  = Age ;
    }
    public String getValues() {
        String info =  " Имя : " + Name + ".\n"+ " Фамилия: " + Last_name + ".\n" + " Возраст: " + Age + ".\n"+ " Должность: " + Position  + ".\n" + " Особые навыки: " + Special_skills + ".\n" ;
        return info;
    }




    public void setValues2(String change_start,String Job_responsibilities,String Lunch,String change_finish){
        this.change_start = change_start;
        this.Job_responsibilities = Job_responsibilities;
        this.Lunch = Lunch;
        this.change_finish = change_finish;
    }
    public String getValues2() {
        String info2 = " Начало работы: " + change_start + ".\n" +  " Должностные обязанности: " + Job_responsibilities + ".\n"+  " Обед: " + Lunch + ".\n"+   " Окончание работы: " + change_finish + ".\n";
        return info2;
        }
    }




